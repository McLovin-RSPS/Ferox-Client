package net.runelite.api;

import com.ferox.draw.Rasterizer3D;
import com.ferox.scene.SceneGraph;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.model.Jarvis;
import net.runelite.api.model.Triangle;
import net.runelite.api.model.Vertex;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.runelite.api.Constants.TILE_FLAG_BRIDGE;

public class Perspective {
	public static final double UNIT = 0.0030679615757712823D;

	public static final int LOCAL_COORD_BITS = 7;

	public static final int LOCAL_TILE_SIZE = 128;

	public static final int SCENE_SIZE = 104;

	public static final int[] SINE = new int[2048];

	public static final int[] COSINE = new int[2048];
	public static final int LOCAL_HALF_TILE_SIZE = LOCAL_TILE_SIZE / 2;

	static {
		for (int i = 0; i < 2048; i++) {
			SINE[i] = (int)(65536.0D * Math.sin(i * 0.0030679615757712823D));
			COSINE[i] = (int)(65536.0D * Math.cos(i * 0.0030679615757712823D));
		}
	}

	public static Point worldToCanvas(@Nonnull Client client, int x, int y, int plane) {
		return worldToCanvas(client, x, y, plane, 0);
	}

	public static Point worldToCanvas(@Nonnull Client client, int x, int y, int plane, int zOffset) {
		return worldToCanvas(client, x, y, plane, x, y, zOffset);
	}

	public static Point worldToCanvas(@Nonnull Client client, int x, int y, int plane, int tileX, int tileY) {
		return worldToCanvas(client, x, y, plane, tileX, tileY, 0);
	}

	public static Point worldToCanvas(@Nonnull Client client, int x, int y, int plane, int tileX, int tileY, int zOffset) {
		if (x >= 128 && y >= 128 && x <= 13056 && y <= 13056) {
			int z = getTileHeight(client, tileX, tileY, client.getPlane()) - plane;
			x -= client.getAnInt1278();
			y -= client.getAnInt1131();
			z -= client.getCameraZ();
			z -= zOffset;
			int cameraPitch = client.getCameraPitch();
			int cameraYaw = client.getCameraYaw();
			int pitchSin = SINE[cameraPitch];
			int pitchCos = COSINE[cameraPitch];
			int yawSin = SINE[cameraYaw];
			int yawCos = COSINE[cameraYaw];
			int var8 = yawCos * x + y * yawSin >> 16;
			y = yawCos * y - yawSin * x >> 16;
			x = var8;
			var8 = pitchCos * z - y * pitchSin >> 16;
			y = z * pitchSin + y * pitchCos >> 16;
			if (y >= 50) {
				int pointX = client.getViewportWidth() / 2 + x * client.getScale() / y;
				int pointY = client.getViewportHeight() / 2 + var8 * client.getScale() / y;
				return new Point(pointX, pointY);
			}
		}
		return null;
	}
	/**
	 * Translates a model's vertices into 2d space
	 */
	public static void modelToCanvas(Client client, int end, int x3dCenter, int y3dCenter, int z3dCenter, int rotate, int[] x3d, int[] y3d, int[] z3d, int[] x2d, int[] y2d)
	{
		final int
				cameraPitch = client.getCameraPitch(),
				cameraYaw = client.getCameraYaw(),

				pitchSin = SINE[cameraPitch],
				pitchCos = COSINE[cameraPitch],
				yawSin = SINE[cameraYaw],
				yawCos = COSINE[cameraYaw],
				rotateSin = SINE[rotate],
				rotateCos = COSINE[rotate],

				cx = x3dCenter - client.getAnInt1278(),
				cy = y3dCenter - client.getAnInt1131(),
				cz = z3dCenter - client.getCameraZ(),

				viewportXMiddle = client.getViewportWidth() / 2,
				viewportYMiddle = client.getViewportHeight() / 2,
				viewportXOffset = 0,
				viewportYOffset = 0,

				zoom3d = client.getScale();

		for (int i = 0; i < end; i++)
		{
			int x = x3d[i];
			int y = y3d[i];
			int z = z3d[i];

			if (rotate != 0)
			{
				int x0 = x;
				x = x0 * rotateCos + y * rotateSin >> 16;
				y = y * rotateCos - x0 * rotateSin >> 16;
			}

			x += cx;
			y += cy;
			z += cz;

			final int
					x1 = x * yawCos + y * yawSin >> 16,
					y1 = y * yawCos - x * yawSin >> 16,
					y2 = z * pitchCos - y1 * pitchSin >> 16,
					z1 = y1 * pitchCos + z * pitchSin >> 16;

			int viewX, viewY;

			if (z1 < 50)
			{
				viewX = Integer.MIN_VALUE;
				viewY = Integer.MIN_VALUE;
			}
			else
			{
				viewX = (viewportXMiddle + x1 * zoom3d / z1) + viewportXOffset;
				viewY = (viewportYMiddle + y2 * zoom3d / z1) + viewportYOffset;
			}

			x2d[i] = viewX;
			y2d[i] = viewY;
		}
	}

	/**
	 * Calculates the above ground height of a tile point.
	 *
	 * @param client the game client
	 * @param point the local ground coordinate
	 * @param plane the client plane/ground level
	 * @return the offset from the ground of the tile
	 */
	public static int getTileHeight(@Nonnull Client client, @Nonnull LocalPoint point, int plane)
	{
		int sceneX = point.getSceneX();
		int sceneY = point.getSceneY();
		if (sceneX >= 0 && sceneY >= 0 && sceneX < SCENE_SIZE && sceneY < SCENE_SIZE)
		{
			byte[][][] tileSettings = client.getTileSettings();
			int[][][] tileHeights = client.getTileHeights();

			int z1 = plane;
			if (plane < Constants.MAX_Z - 1 && (tileSettings[1][sceneX][sceneY] & TILE_FLAG_BRIDGE) == TILE_FLAG_BRIDGE)
			{
				z1 = plane + 1;
			}

			int x = point.getX() & (LOCAL_TILE_SIZE - 1);
			int y = point.getY() & (LOCAL_TILE_SIZE - 1);
			int var8 = x * tileHeights[z1][sceneX + 1][sceneY] + (LOCAL_TILE_SIZE - x) * tileHeights[z1][sceneX][sceneY] >> LOCAL_COORD_BITS;
			int var9 = tileHeights[z1][sceneX][sceneY + 1] * (LOCAL_TILE_SIZE - x) + x * tileHeights[z1][sceneX + 1][sceneY + 1] >> LOCAL_COORD_BITS;
			return (LOCAL_TILE_SIZE - y) * var8 + y * var9 >> LOCAL_COORD_BITS;
		}

		return 0;
	}
	@Nullable
	public static Point worldToMiniMap(@Nonnull Client client, int x, int y) {
		return worldToMiniMap(client, x, y, 6400);
	}

	@Nullable
	public static Point worldToMiniMap(@Nonnull Client client, int x, int y, int distance) {
		LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();
		int sceneX = x >>> 7;
		int sceneY = y >>> 7;
		x = sceneX * 4 + 2 - localLocation.getX() / 32;
		y = sceneY * 4 + 2 - localLocation.getY() / 32;
		int dist = x * x + y * y;
		if (dist < distance) {
			Widget minimapDrawWidget;
			if (client.isResized()) {
				if (client.getVar(Varbits.SIDE_PANELS) == 1) {
					minimapDrawWidget = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_DRAW_AREA);
				} else {
					minimapDrawWidget = client.getWidget(WidgetInfo.RESIZABLE_MINIMAP_STONES_DRAW_AREA);
				}
			} else {
				minimapDrawWidget = client.getWidget(WidgetInfo.FIXED_VIEWPORT_MINIMAP_DRAW_AREA);
			}
			if (minimapDrawWidget == null || minimapDrawWidget.isHidden())
				return null;
			int angle = client.getMapAngle() & 0x7FF;
			int sin = SINE[angle];
			int cos = COSINE[angle];
			int xx = y * sin + cos * x >> 16;
			int yy = sin * x - y * cos >> 16;
			Point loc = minimapDrawWidget.getCanvasLocation();
			int miniMapX = loc.getX() + xx + minimapDrawWidget.getWidth() / 2;
			int miniMapY = minimapDrawWidget.getHeight() / 2 + loc.getY() + yy;
			return new Point(miniMapX, miniMapY);
		}
		return null;
	}

	public static int getTileHeight(@Nonnull Client client, int localX, int localY, int plane) {
		int sceneX = localX >> 7;
		int sceneY = localY >> 7;
		if (sceneX >= 0 && sceneY >= 0 && sceneX <= 103 && sceneY <= 103) {
			byte[][][] tileSettings = client.getTileSettings();
			int[][][] tileHeights = client.getTileHeights();
			int var5 = plane;
			if (plane < 3 && (tileSettings[1][sceneX][sceneY] & 0x2) == 2)
				var5 = plane + 1;
			int var6 = localX & 0x7F;
			int var7 = localY & 0x7F;
			int var8 = var6 * tileHeights[var5][sceneX + 1][sceneY] + (128 - var6) * tileHeights[var5][sceneX][sceneY] >> 7;
			int var9 = tileHeights[var5][sceneX][sceneY + 1] * (128 - var6) + var6 * tileHeights[var5][sceneX + 1][sceneY + 1] >> 7;
			return (128 - var7) * var8 + var7 * var9 >> 7;
		}
		return 0;
	}

	public static Polygon getCanvasTilePoly(@Nonnull Client client, @Nonnull LocalPoint localLocation) {
		return getCanvasTileAreaPoly(client, localLocation, 1);
	}

	public static Polygon getCanvasTileAreaPoly(@Nonnull Client client, @Nonnull LocalPoint localLocation, int size) {
		int plane = client.getPlane();
		Point southWestCorner = new Point(localLocation.getX() - size * 128 / 2 + 1, localLocation.getY() - size * 128 / 2 + 1);
		Point northEastCorner = new Point(southWestCorner.getX() + size * 128 - 1, southWestCorner.getY() + size * 128 - 1);
		Point bottomLeft = new Point(southWestCorner.getX(), northEastCorner.getY());
		Point topRight = new Point(northEastCorner.getX(), southWestCorner.getY());
		Point p1 = worldToCanvas(client, southWestCorner.getX(), southWestCorner.getY(), plane);
		Point p2 = worldToCanvas(client, topRight.getX(), topRight.getY(), plane);
		Point p3 = worldToCanvas(client, northEastCorner.getX(), northEastCorner.getY(), plane);
		Point p4 = worldToCanvas(client, bottomLeft.getX(), bottomLeft.getY(), plane);
		if (p1 == null || p2 == null || p3 == null || p4 == null)
			return null;
		Polygon poly = new Polygon();
		poly.addPoint(p1.getX(), p1.getY());
		poly.addPoint(p2.getX(), p2.getY());
		poly.addPoint(p3.getX(), p3.getY());
		poly.addPoint(p4.getX(), p4.getY());
		return poly;
	}

	public static Point getCanvasTextLocation(@Nonnull Client client, @Nonnull Graphics2D graphics, @Nonnull LocalPoint localLocation, @Nullable String text, int zOffset) {
		if (text == null)
			return null;
		int plane = client.getPlane();
		Point p = localToCanvas(client, localLocation, plane, zOffset);
		if (p == null)
			return null;
		FontMetrics fm = graphics.getFontMetrics();
		Rectangle2D bounds = fm.getStringBounds(text, graphics);
		int xOffset = p.getX() - (int)(bounds.getWidth() / 2.0D);
		return new Point(xOffset, p.getY());
	}

	public static Point getCanvasImageLocation(@Nonnull Client client, @Nonnull Graphics2D graphics, @Nonnull LocalPoint localLocation, @Nonnull BufferedImage image, int zOffset) {
		int plane = client.getPlane();
		Point p = worldToCanvas(client, localLocation.getX(), localLocation.getY(), plane, zOffset);
		if (p == null)
			return null;
		int xOffset = p.getX() - image.getWidth() / 2;
		int yOffset = p.getY() - image.getHeight() / 2;
		return new Point(xOffset, yOffset);
	}

	public static Point getMiniMapImageLocation(@Nonnull Client client, @Nonnull LocalPoint localLocation, @Nonnull BufferedImage image) {
		Point p = worldToMiniMap(client, localLocation.getX(), localLocation.getY());
		if (p == null)
			return null;
		int xOffset = p.getX() - image.getWidth() / 2;
		int yOffset = p.getY() - image.getHeight() / 2;
		return new Point(xOffset, yOffset);
	}

	public static Point getCanvasSpriteLocation(@Nonnull Client client, @Nonnull Graphics2D graphics, @Nonnull LocalPoint localLocation, @Nonnull SpritePixels sprite, int zOffset) {
		int plane = client.getPlane();
		Point p = worldToCanvas(client, localLocation.getX(), localLocation.getY(), plane, zOffset);
		if (p == null)
			return null;
		int xOffset = p.getX() - sprite.getWidth() / 2;
		int yOffset = p.getY() - sprite.getHeight() / 2;
		return new Point(xOffset, yOffset);
	}

	public static Area getClickbox(@Nonnull Client client, Model model, int orientation, int tileX, int tileY) {
		if (model == null)
			return null;
		List<Triangle> triangles = (List<Triangle>)model.getTriangles().stream().map(triangle -> triangle.rotate(orientation)).collect(Collectors.toList());
		List<Vertex> vertices = (List<Vertex>)model.getVertices().stream().map(v -> v.rotate(orientation)).collect(Collectors.toList());
		Area clickBox = get2DGeometry(client, triangles, orientation, tileX, tileY);
		Area visibleAABB = getAABB(client, vertices, orientation, tileX, tileY);
		if (visibleAABB == null || clickBox == null)
			return null;
		clickBox.intersect(visibleAABB);
		return clickBox;
	}

	private static boolean isOffscreen(@Nonnull Client client, @Nonnull Point point) {
		return ((point.getX() < 0 || point.getX() >= client.getViewportWidth()) && (point
				.getY() < 0 || point.getY() >= client.getViewportHeight()));
	}

	private static Area get2DGeometry(@Nonnull Client client, @Nonnull List<Triangle> triangles, int orientation, int tileX, int tileY) {
		int radius = 5;
		Area geometry = new Area();
		for (Triangle triangle : triangles) {
			Vertex _a = triangle.getA();
			Point a = worldToCanvas(client, tileX - _a
							.getX(), tileY - _a
							.getZ(),
					-_a.getY(), tileX, tileY);
			if (a == null)
				continue;
			Vertex _b = triangle.getB();
			Point b = worldToCanvas(client, tileX - _b
							.getX(), tileY - _b
							.getZ(),
					-_b.getY(), tileX, tileY);
			if (b == null)
				continue;
			Vertex _c = triangle.getC();
			Point c = worldToCanvas(client, tileX - _c
							.getX(), tileY - _c
							.getZ(),
					-_c.getY(), tileX, tileY);
			if (c == null)
				continue;
			if (isOffscreen(client, a) && isOffscreen(client, b) && isOffscreen(client, c))
				continue;
			int minX = Math.min(Math.min(a.getX(), b.getX()), c.getX());
			int minY = Math.min(Math.min(a.getY(), b.getY()), c.getY());
			int maxX = Math.max(Math.max(a.getX(), b.getX()), c.getX()) + 4;
			int maxY = Math.max(Math.max(a.getY(), b.getY()), c.getY()) + 4;
			Rectangle clickableRect = new Rectangle(minX - radius, minY - radius, maxX - minX + radius, maxY - minY + radius);
			if (geometry.contains(clickableRect))
				continue;
			geometry.add(new Area(clickableRect));
		}
		return geometry;
	}

	private static Area getAABB(@Nonnull Client client, @Nonnull List<Vertex> vertices, int orientation, int tileX, int tileY) {
		int maxX = 0;
		int minX = 0;
		int maxY = 0;
		int minY = 0;
		int maxZ = 0;
		int minZ = 0;
		for (Vertex vertex : vertices) {
			int x = vertex.getX();
			int y = vertex.getY();
			int z = vertex.getZ();
			if (x > maxX)
				maxX = x;
			if (x < minX)
				minX = x;
			if (y > maxY)
				maxY = y;
			if (y < minY)
				minY = y;
			if (z > maxZ)
				maxZ = z;
			if (z < minZ)
				minZ = z;
		}
		int centerX = (minX + maxX) / 2;
		int centerY = (minY + maxY) / 2;
		int centerZ = (minZ + maxZ) / 2;
		int extremeX = (maxX - minX + 1) / 2;
		int extremeY = (maxY - minY + 1) / 2;
		int extremeZ = (maxZ - minZ + 1) / 2;
		if (extremeX < 32)
			extremeX = 32;
		if (extremeZ < 32)
			extremeZ = 32;
		int x1 = tileX - centerX - extremeX;
		int y1 = centerY - extremeY;
		int z1 = tileY - centerZ - extremeZ;
		int x2 = tileX - centerX + extremeX;
		int y2 = centerY + extremeY;
		int z2 = tileY - centerZ + extremeZ;
		Point p1 = worldToCanvas(client, x1, z1, -y1, tileX, tileY);
		Point p2 = worldToCanvas(client, x1, z2, -y1, tileX, tileY);
		Point p3 = worldToCanvas(client, x2, z2, -y1, tileX, tileY);
		Point p4 = worldToCanvas(client, x2, z1, -y1, tileX, tileY);
		Point p5 = worldToCanvas(client, x1, z1, -y2, tileX, tileY);
		Point p6 = worldToCanvas(client, x1, z2, -y2, tileX, tileY);
		Point p7 = worldToCanvas(client, x2, z2, -y2, tileX, tileY);
		Point p8 = worldToCanvas(client, x2, z1, -y2, tileX, tileY);
		List<Point> points = new ArrayList<>(8);
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		points.add(p8);
		try {
			points = Jarvis.convexHull(points);
		} catch (NullPointerException e) {
			return null;
		}
		if (points == null)
			return null;
		Polygon hull = new Polygon();
		for (Point p : points) {
			if (p != null)
				hull.addPoint(p.getX(), p.getY());
		}
		return new Area(hull);
	}

	public static Point getCanvasTextMiniMapLocation(@Nonnull Client client, @Nonnull Graphics2D graphics, @Nonnull LocalPoint localLocation, @Nonnull String text) {
		Point p = worldToMiniMap(client, localLocation.getX(), localLocation.getY());
		if (p == null)
			return null;
		FontMetrics fm = graphics.getFontMetrics();
		Rectangle2D bounds = fm.getStringBounds(text, graphics);
		int xOffset = p.getX() - (int)(bounds.getWidth() / 2.0D);
		int yOffset = p.getY() - (int)(bounds.getHeight() / 2.0D) + fm.getAscent();
		return new Point(xOffset, yOffset);
	}

	@Nullable
	public static Point localToCanvas(@Nonnull Client client, @Nonnull LocalPoint point, int plane) {
		return localToCanvas(client, point, plane, 0);
	}

	@Nullable
	public static Point localToCanvas(@Nonnull Client client, @Nonnull LocalPoint point, int plane, int zOffset) {
		int tileHeight = getTileHeight(client, point.getX(), point.getY(), plane);
		return localToCanvas(client, point.getX(), point.getY(), tileHeight - zOffset);
	}

	public static Point localToCanvas(@Nonnull Client client, int x, int y, int z) {
		if (x >= 128 && y >= 128 && x <= 13056 && y <= 13056) {
			x -= client.getAnInt1278();
			y -= client.getAnInt1131();
			z -= client.getCameraZ();
			int cameraPitch = client.getCameraPitch();
			int cameraYaw = client.getCameraYaw();
			int pitchSin = SINE[cameraPitch];
			int pitchCos = COSINE[cameraPitch];
			int yawSin = SINE[cameraYaw];
			int yawCos = COSINE[cameraYaw];
			int var8 = yawCos * x + y * yawSin >> 16;
			y = yawCos * y - yawSin * x >> 16;
			x = var8;
			var8 = pitchCos * z - y * pitchSin >> 16;
			y = z * pitchSin + y * pitchCos >> 16;
			if (y >= 50) {
				int pointX = Rasterizer3D.center_x + (x << SceneGraph.view_dist) / y;
				int pointY = Rasterizer3D.center_y + (var8 << SceneGraph.view_dist) / y;
				return new Point(pointX, pointY);
			}
		}
		return null;
	}

	@Nullable
	public static Point localToMinimap(@Nonnull Client client, @Nonnull LocalPoint point) {
		return localToMinimap(client, point, 6400);
	}

	@Nullable
	public static Point localToMinimap(@Nonnull Client client, @Nonnull LocalPoint point, int distance) {
		LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();
		int x = point.getX() / 32 - localLocation.getX() / 32;
		int y = point.getY() / 32 - localLocation.getY() / 32;
		int dist = x * x + y * y;
		if (dist < distance) {
			Rectangle minimapRect = client.getMinimapBounds();
			int angle = client.getMapAngle() & 0x7FF;
			int sin = SINE[angle];
			int cos = COSINE[angle];
			int xx = y * sin + cos * x >> 16;
			int yy = sin * x - y * cos >> 16;
			int miniMapX = (int)minimapRect.getX() + xx + (int)minimapRect.getWidth() / 2;
			int miniMapY = (int)minimapRect.getHeight() / 2 + (int)minimapRect.getY() + yy;
			return new Point(miniMapX, miniMapY);
		}
		return null;
	}
}
