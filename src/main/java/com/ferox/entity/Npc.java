package com.ferox.entity;

import com.ferox.Client;
import com.ferox.cache.anim.Animation;
import com.ferox.cache.anim.Sequence;
import com.ferox.cache.anim.SpotAnimation;
import com.ferox.cache.def.NpcDefinition;
import com.ferox.entity.model.Model;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Npc extends Entity implements NPC {

    public NpcDefinition desc;
    public int headIcon = -1;
    public int ownerIndex = -1;

    public boolean showActions() {
        if (ownerIndex == -1) {
            return true;
        }
        return (Client.instance.localPlayerIndex == ownerIndex);
    }

    public int getHeadIcon() {
        if (headIcon == -1) {
            if (desc != null) {
                return desc.headIcon;
            }
        }
        return headIcon;
    }

    private Model get_animated_model() {
        int current_frame = -1;
        int animation = -1;
        if(super.animation >= 0 && super.animation_delay == 0) {
            Sequence seq = Sequence.cache[super.animation];
            current_frame = seq.primary_frame[super.current_animation_frame];
            if (super.queued_animation_id >= 0 && super.queued_animation_id != super.idle_animation_id)
                animation = Sequence.cache[super.queued_animation_id].primary_frame[super.queued_animation_frame];

            return desc.get_animated_model(animation, current_frame, Sequence.cache[super.animation].flow_control);
        } else if(super.queued_animation_id >= 0) {
            Sequence seq = Sequence.cache[super.queued_animation_id];
            current_frame = seq.primary_frame[super.queued_animation_frame];
        }
        return desc.get_animated_model(animation, current_frame, null);
    }

    public Model get_rotated_model() {
        if (desc == null)
            return null;

        Model animated = get_animated_model();
        if (animated == null)
            return null;

        super.height = animated.model_height;
        if (super.graphic_id != -1 && super.current_animation_id != -1) {
            SpotAnimation anim = SpotAnimation.cache[super.graphic_id];
            Model model = anim.get_model();
            if (model != null) {
                int frame = anim.seq.primary_frame[super.current_animation_id];
                Model graphic = new Model(true, Animation.validate(frame), false, model);
                graphic.translate(0, -super.graphic_height, 0);
                graphic.skin();
                graphic.interpolate(frame);
                graphic.face_skin = null;
                graphic.vertex_skin = null;
                if (anim.model_scale_x != 128 || anim.model_scale_y != 128)
                    graphic.scale(anim.model_scale_x, anim.model_scale_x, anim.model_scale_y);
                graphic.light(64 + anim.ambient, 850 + anim.contrast, -30, -50, -30);
                Model[] build = {
                    animated, graphic
                };
                animated = new Model(build);
            }
        }
        if (desc.occupied_tiles == 1)
            animated.within_tile = true;

        return animated;
    }

    public boolean visible() {
        return desc != null;
    }

    @Override
    public Actor getInteracting() {
        return null;
    }

    @Override
    public String getInteractingName() {
        return null;
    }

    @Override
    public int getHealthRatio() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    public WorldPoint getWorldLocation() {
        return WorldPoint.fromLocal(Client.instance,
            this.waypoint_x[0] * Perspective.LOCAL_TILE_SIZE + Perspective.LOCAL_TILE_SIZE / 2,
            this.waypoint_y[0] * Perspective.LOCAL_TILE_SIZE + Perspective.LOCAL_TILE_SIZE / 2,
            Client.instance.getPlane());
    }

    public LocalPoint getLocalLocation() {
        return new LocalPoint(this.world_x, this.world_y);
    }

    @Override
    public void setIdlePoseAnimation(int animation) {

    }

    @Override
    public void setWalkAnimation(int animation) {

    }

    @Override
    public int getIdlePoseAnimation() {
        return 0;
    }

    @Override
    public int getWalkAnimation() {
        return 0;
    }

    @Override
    public void setRunAnimation(int animation) {

    }

    @Override
    public int getRunAnimation() {
        return 0;
    }

    @Override
    public int getOrientation() {
        return 0;
    }

    @Override
    public int getAnimation() {
        return 0;
    }

    @Override
    public void setAnimation(int animation) {

    }

    @Override
    public void setActionFrame(int actionFrame) {

    }

    @Override
    public int getGraphic() {
        return 0;
    }

    @Override
    public void setGraphic(int graphic) {

    }

    @Override
    public void setSpotAnimFrame(int spotAnimFrame) {

    }

    @Override
    public int getModelHeight() {
        return 0;
    }

    @Override
    public Polygon getCanvasTilePoly() {
        return null;
    }

    @Override
    public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) {
        return null;
    }

    @Override
    public Point getCanvasImageLocation(Graphics2D graphics, BufferedImage image, int zOffset) {
        return null;
    }

    @Override
    public Point getCanvasSpriteLocation(Graphics2D graphics, SpritePixels sprite, int zOffset) {
        return null;
    }

    @Override
    public Point getMinimapLocation() {
        return null;
    }

    @Override
    public int getLogicalHeight() {
        return 0;
    }

    @Override
    public Polygon getConvexHull() {
        return null;
    }

    @Override
    public WorldArea getWorldArea() {
        return null;
    }

    @Override
    public String getOverhead() {
        return null;
    }

    @Override
    public void setOverheadText(String dialogue) {

    }

    @Override
    public String getOverheadText() {
        return null;
    }

    @Override
    public int getId() {
        return this.desc.id;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getCombatLevel() {
        return 0;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public NPCComposition getComposition() {
        return null;
    }

    @Override
    public NPCComposition getTransformedComposition() {
        return null;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
