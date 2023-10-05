package net.runelite.api;


import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.hooks.Callbacks;
import net.runelite.api.hooks.DrawCallbacks;
import net.runelite.api.vars.AccountType;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public interface Client extends GameEngine {
	Callbacks getCallbacks();

	Logger getLogger();

	List<Player> getPlayers();

	List<NPC> getNpcs();

	NPC[] getCachedNPCs();

	void setLoadingStage(int stage);
	void renderWorld();
	TextureProvider getTextureProvider();
	Player[] getCachedPlayers();

	void setGpu(boolean gpu);
	int getCenterX();
	int getCenterY();

	int getBoostedSkillLevel(Skill paramSkill);

	int getRealSkillLevel(Skill paramSkill);

	void addChatMessage(ChatMessageType paramChatMessageType, String paramString1, String paramString2, String paramString3);

	GameState getGameState();

	String getUsername();

	void setUsername(String paramString);

	String getPassword();
	void setPassword(String password);

	void sendLogin();

	AccountType getAccountType();

	Component getCanvas();

	int getFPS();

	int getAnInt1278();

	int getAnInt1131();

	int getCameraZ();

	int getCameraPitch();

	int getCameraYaw();

	int getWorld();

	int getViewportHeight();

	int getViewportWidth();

	int getViewportXOffset();

	int getViewportYOffset();

	int getScale();

	Point getMouseCanvasPosition();

	Point getMouseAbsolutePosition();

	int[][][] getTileHeights();

	byte[][][] getTileSettings();

	int getPlane();

	Scene getScene();

	Player getLocalPlayer();

	ItemComposition getItemDefinition(int paramInt);
	ItemComposition[] getItemDefinitions();

	SpritePixels createItemSprite(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6);

	SpritePixels getSprite(IndexDataBase paramIndexDataBase, int paramInt1, int paramInt2);

	IndexDataBase getIndexSprites();

	SpritePixels[] getSideIconSprites();

	SpritePixels getSprite(String sprite);
	SpritePixels getSprite(int sprite);

	int getBaseX();

	int getBaseY();

	int getMouseCurrentButton();

	Tile getSelectedSceneTile();

	boolean isDraggingWidget();

	Widget getDraggedWidget();

	Widget getDraggedOnWidget();

	void setDraggedOnWidget(Widget paramWidget);

	Widget[] getWidgetRoots();

	Widget getWidget(WidgetInfo paramWidgetInfo);

	Widget getWidget(int paramInt);

	Widget getWidget(int paramInt1, int paramInt2);

	int[] getWidgetPositionsX();

	int[] getWidgetPositionsY();

	int getEnergy();

	String[] getPlayerOptions();

	boolean[] getPlayerOptionsPriorities();

	int[] getPlayerMenuTypes();

	World[] getWorldList();

	MenuEntry[] getMenuEntries();

	int getMenuOptionAmount();

	void setMenuEntries(MenuEntry[] paramArrayOfMenuEntry);

	boolean isMenuOpen();

	int getMapAngle();

	boolean isResized();

	void setResized(boolean resized);

	int getRevision();

	int[] getMapRegions();

	int getCurrentRegionId();

	int[][][] getInstanceTemplateChunks();

	int[][] getXteaKeys();

	int[] getVarps();

	int[] getIntVarcs();

	String[] getStrVarcs();

	int getVar(VarPlayer paramVarPlayer);

	int getVar(Varbits paramVarbits);

	int getVar(VarClientInt paramVarClientInt);

	String getVar(VarClientStr paramVarClientStr);

	void setVar(VarClientStr paramVarClientStr, String paramString);

	void setSetting(Varbits paramVarbits, int paramInt);

	int getVarbitValue(int[] paramArrayOfint, int paramInt);

	int getVarpValue(int[] paramArrayOfint, int paramInt);

	void setVarpValue(int[] paramArrayOfint, int paramInt1, int paramInt2);

	void setVarbitValue(int[] paramArrayOfint, int paramInt1, int paramInt2);

	HashTable getWidgetFlags();

	HashTable<WidgetNode> getComponentTable();

	GrandExchangeOffer[] getGrandExchangeOffers();

	boolean isPrayerActive(Prayer paramPrayer);

	int getSkillExperience(Skill paramSkill);

	long getOverallExperience();

	int getGameDrawingMode();

	void setGameDrawingMode(int paramInt);

	void refreshChat();

	Map<Integer, String[][]> getChatLineMap();

	Rectangle getViewportBounds();

	Rectangle getMinimapBounds();

	Point getMinimapCanvasLocation();

	Rectangle getChatboxBounds();

	int[] getSidebarIds();

	ObjectComposition getObjectDefinition(int paramInt);

	NPCComposition getNpcDefinition(int paramInt);

	Area[] getMapAreas();

	IndexedSprite[] getMapScene();

	SpritePixels[] getMapDots();

	int getGameCycle();

	SpritePixels[] getMapIcons();

	IndexedSprite[] getModIcons();

	void setModIcons(IndexedSprite[] paramArrayOfIndexedSprite);

	IndexedSprite createIndexedSprite();

	SpritePixels createSpritePixels(int[] paramArrayOfint, int paramInt1, int paramInt2);
	SpritePixels createItemSprite(int itemId, int quantity, int outlineColor);

	BufferedImage createItemImage(int id);

	void resizeCanvas();

	@Nullable
	LocalPoint getLocalDestinationLocation();

	List<Projectile> getProjectiles();

	List<GraphicsObject> getGraphicsObjects();

	void playSoundEffect(int paramInt);

	void playSoundEffect(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

	BufferProvider getMainBufferProvider();

	BufferProvider getGameAreaBufferProvider();

	BufferProvider getTabAreaBufferProvider();

	BufferProvider getMinimapBufferProvider();

	int getMouseIdleTicks();

	int getKeyboardIdleTicks();

	void changeMemoryMode(boolean paramBoolean);

	@Nullable
	int[] getWidgetItemIds(int paramInt);

	@Nullable
	int[] getWidgetItemAmounts(int paramInt);

	int getWidgetItemIdBySlot(int paramInt1, int paramInt2);

	int getWidgetItemAmountBySlot(int paramInt1, int paramInt2);

	Item[] getWidgetItems(int paramInt);

	Widget getWidgetChild(int paramInt1, int paramInt2);

	int[] getWidgetChildrenIds(int paramInt);

	int getIntStackSize();

	void setIntStackSize(int paramInt);

	int[] getIntStack();

	int getStringStackSize();

	void setStringStackSize(int paramInt);

	String[] getStringStack();

	boolean isFriended(String paramString, boolean paramBoolean);

	int getClanChatCount();

	ClanMember[] getClanMembers();

	Friend[] getFriends();

	boolean isClanMember(String paramString);

	Preferences getPreferences();

	void setCameraPitchRelaxerEnabled(boolean paramBoolean);

	RenderOverview getRenderOverview();

	boolean isStretchedEnabled();

	void setStretchedEnabled(boolean paramBoolean);

	boolean isStretchedFast();

	void setStretchedFast(boolean paramBoolean);

	void setStretchedIntegerScaling(boolean paramBoolean);

	void setStretchedKeepAspectRatio(boolean paramBoolean);

	Dimension getStretchedDimensions();

	Dimension getRealDimensions();

	void changeWorld(World paramWorld);

	World createWorld();

	SpritePixels drawInstanceMap(int paramInt);

	boolean hasHintArrow();

	HintArrowType getHintArrowType();

	void clearHintArrow();

	void setHintArrow(WorldPoint paramWorldPoint);

	void setHintArrow(Player paramPlayer);

	void setHintArrow(NPC paramNPC);

	WorldPoint getHintArrowPoint();

	Player getHintArrowPlayer();

	NPC getHintArrowNpc();

	void setPetsHidden(boolean hide);
	boolean isInterpolatePlayerAnimations();

	void setInterpolatePlayerAnimations(boolean paramBoolean);

	boolean isInterpolateNpcAnimations();

	void setInterpolateNpcAnimations(boolean paramBoolean);

	boolean isInterpolateObjectAnimations();

	void setInterpolateObjectAnimations(boolean paramBoolean);

	boolean isInInstancedRegion();

	void setIsHidingEntities(boolean paramBoolean);

	void setOthersHidden(boolean paramBoolean);

	void setFriendsHidden(boolean paramBoolean);

	void setClanMatesHidden(boolean paramBoolean);

	void setLocalPlayerHidden(boolean paramBoolean);

	void setLocalPlayerHidden2D(boolean paramBoolean);

	void setNPCsHidden(boolean paramBoolean);

	void setNPCsHidden2D(boolean paramBoolean);

	void setAttackersHidden(boolean paramBoolean);

	void setProjectilesHidden(boolean paramBoolean);

	CollisionData[] getCollisionMaps();

	int[] getBoostedSkillLevels();

	int[] getRealSkillLevels();

	int[] getSkillExperiences();

	int[] getChangedSkills();

	int getChangedSkillsCount();

	void setChangedSkillsCount(int paramInt);

	void setSpriteOverrides(Map<Integer, SpritePixels> paramMap);

	void setWidgetSpriteOverrides(Map<Integer, SpritePixels> paramMap);

	void setCompass(SpritePixels paramSpritePixels);

	int getTickCount();

	void setTickCount(int paramInt);

	void setInventoryDragDelay(int paramInt);

	EnumSet<WorldType> getWorldType();

	void setOculusOrbState(int paramInt);

	void setOculusOrbNormalSpeed(int paramInt);

	boolean isFogEnabled();

	void setFogEnabled(boolean enabled);

	DrawCallbacks getDrawCallbacks();

	void setDrawCallbacks(DrawCallbacks drawCallbacks);

    int getCanvasHeight();

	int getCanvasWidth();

	int getRasterizer3D_clipMidX2();

	int getRasterizer3D_clipNegativeMidX();

	int getRasterizer3D_clipNegativeMidY();

	int getRasterizer3D_clipMidY2();

    int getSkyboxColor();

	int getCameraX2();

	int getCameraY2();

	int getCameraZ2();

	int get3dZoom();

	int getCameraX();

	int getCameraY();

	void checkClickbox(Model model, int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z, long hash);

	void setGameState(GameState loading);

	int getOculusOrbFocalPointX();

	int getOculusOrbFocalPointY();

	int getMouseX2();

	int getMouseY2();

}
