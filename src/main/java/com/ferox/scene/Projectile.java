package com.ferox.scene;
import com.ferox.Client;
import com.ferox.cache.anim.Animation;
import com.ferox.cache.anim.SpotAnimation;
import com.ferox.entity.Renderable;
import com.ferox.entity.model.Model;
import net.runelite.api.Actor;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ProjectileMoved;

public final class Projectile extends Renderable implements net.runelite.api.Projectile {

    public final int delay;
    public final int cycle_limit;
    private double vector_speed_x;
    private double vector_speed_y;
    private double momentum_scalar;
    private double vector_speed_z;
    private double height_offset;
    private boolean traveling;
    private final int start_x;
    private final int start_y;
    private final int start_z;
    public final int end_z;
    public double current_x;
    public double current_y;
    public double current_height;
    private final int slope_start;
    private final int distance;
    public final int target_id;
    private final SpotAnimation graphics;
    private int flow;
    private int duration;
    public int jaw;
    private int pitch;
    public final int plane;

    public void track(int cycle, int target_y, int k, int target_x) {
        this.projectileMoved(target_x, target_y, k);
        if(!traveling) {
            double vector_x = target_x - start_x;
            double vector_y = target_y - start_y;
            double scalar = Math.sqrt(vector_x * vector_x + vector_y * vector_y);
            current_x = (double)start_x + (vector_x * (double)distance) / scalar;
            current_y = (double)start_y + (vector_y * (double)distance) / scalar;
            current_height = start_z;
        }
        double remaining = (cycle_limit + 1) - cycle;
        vector_speed_x = ((double)target_x - current_x) / remaining;
        vector_speed_y = ((double)target_y - current_y) / remaining;
        momentum_scalar = Math.sqrt(vector_speed_x * vector_speed_x + vector_speed_y * vector_speed_y);
        if(!traveling) {
            vector_speed_z = -momentum_scalar * Math.tan((double)slope_start * 0.02454369D);
        }
        height_offset = (2D * ((double)k - current_height - vector_speed_z * remaining)) / (remaining * remaining);
    }
    public void projectileMoved(int var1, int var2, int var3) {
        LocalPoint var5 = new LocalPoint(var1, var2);
        ProjectileMoved var6 = new ProjectileMoved();
        var6.setProjectile(this);
        var6.setPosition(var5);
        var6.setZ(var3);
        Client.instance.getCallbacks().post(var6);
    }
    public Model get_rotated_model() {
        Model model = graphics.get_model();
        if(model == null) {
            return null;
        }
        int frame = -1;
        if(graphics.seq != null) {
            frame = graphics.seq.primary_frame[flow];
        }
        Model animated_model = new Model(true, Animation.validate(frame), false, model);
        if(frame != -1) {
            animated_model.skin();
            animated_model.interpolate(frame);
            animated_model.face_skin = null;
            animated_model.vertex_skin = null;
        }
        if(graphics.model_scale_x != 128 || graphics.model_scale_y != 128) {
            animated_model.scale(graphics.model_scale_x, graphics.model_scale_x, graphics.model_scale_y);
        }
        animated_model.leanOverX(pitch);
        animated_model.light(64 + graphics.ambient, 850 + graphics.contrast, -30, -50, -30);
        return animated_model;
    }

    public Projectile(int slope_start, int end_z, int delay, int cycle_limit, int distance, int plane,  int start_z, int start_y, int start_x, int target_id, int id) {
        graphics = SpotAnimation.cache[id];
        this.plane = plane;
        this.start_x = start_x; //pos / 128 - 64
        this.start_y = start_y;
        this.start_z = start_z;
        this.delay = delay;
        this.cycle_limit = cycle_limit;
        this.slope_start = slope_start;
        this.distance = distance;
        this.target_id = target_id;
        this.end_z = end_z;
        traveling = false;
    }
    public static void main(String[] args) {
        int x = 7203;
        x /= 128;
        System.out.println("x = " + x);
    }
    public void travel(int step) {
        traveling = true;
        current_x += vector_speed_x * (double)step;
        current_y += vector_speed_y * (double)step;
        current_height += vector_speed_z * (double)step + 0.5D * height_offset * (double)step * step;
        vector_speed_z += height_offset * (double)step;
        jaw = (int)(Math.atan2(vector_speed_x, vector_speed_y) * 325.94900000000001D) + 1024 & 0x7ff;
        pitch = (int)(Math.atan2(vector_speed_z, momentum_scalar) * 325.94900000000001D) & 0x7ff;
        if(graphics.seq != null) {
            for(duration += step; duration > graphics.seq.get_length(flow);) {
                duration -= graphics.seq.get_length(flow) + 1;
                flow++;
                if(flow >= graphics.seq.frames) {
                    flow = 0;
                }
            }
        }
    }

    @Override
    public int getId() {
        return this.graphics.id;
    }

    @Override
    public Actor getInteracting() {
        if(target_id < 0) {
           // return Client.instance.players[-target_id - 1];
        } else if(target_id > 0) {
            return Client.instance.getNpcs().get(target_id - 1);
        }
        return null;
    }

    @Override
    public int getX1() {
        if(getInteracting() != null)
            return getInteracting().getWorldLocation().getX();
        return 0;
    }

    @Override
    public int getY1() {
        if(getInteracting() != null)
            return getInteracting().getWorldLocation().getY();
        return 0;
    }

    @Override
    public int getFloor() {
        return (int) current_height;
    }

    @Override
    public int getHeight() {
        return start_z;
    }

    @Override
    public int getEndHeight() {
        return this.end_z;
    }

    @Override
    public int getStartMovementCycle() {
        return delay;
    }

    @Override
    public int getEndCycle() {
        return cycle_limit;
    }

    @Override
    public int getRemainingCycles() {
        return getEndCycle() - Client.instance.getGameCycle();
    }

    @Override
    public int getSlope() {
        return slope_start;
    }

    @Override
    public int getStartHeight() {
        return model_height;
    }

    @Override
    public double getX() {
        double x = current_x;
        x /= 128;
        double y = current_y;
        y /= 128;
        LocalPoint lp = LocalPoint.fromScene((int) x, (int) y);
        WorldPoint wp = WorldPoint.fromLocal(Client.instance, lp);
        return lp.getX();
    }

    @Override
    public double getY() {
        double x = current_x;
        x /= 128;
        double y = current_y;
        y /= 128;
        LocalPoint lp = LocalPoint.fromScene((int) x, (int) y);
        WorldPoint wp = WorldPoint.fromLocal(Client.instance, lp);
        return lp.getY();
    }

    @Override
    public double getZ() {
        return current_height;
    }

    @Override
    public double getScalar() {
        return 0;
    }

    @Override
    public double getVelocityX() {
        return 0;
    }

    @Override
    public double getVelocityY() {
        return 0;
    }

    @Override
    public double getVelocityZ() {
        return 0;
    }
}
