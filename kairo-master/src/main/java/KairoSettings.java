/*
 * @(#)KairoSettings.java  2.0.1  2019-02-02
 *
 * Copyright (c) 2019 Jerry Reno
 * This is public domain software, under the terms of the UNLICENSE
 * http://unlicense.org 
 */


public final class KairoSettings {

	// Settings keys:
	private static final String HELLO = "hello";
	private static final String TITLE = "windowTitle";

	private static final String TRIGGER_DIST = "triggerDistance";
	private static final String CATCH_DIST = "catchDistance";
	private static final String RUN_DIST = "runDistancePerFrame";
	private static final String OFFSETX = "offsetx";
	private static final String OFFSETY = "offsety";

	private static final String MAX_FRAMERATE = "maxFramerate";
	private static final String RUN_FRAMERATE = "runFramerate";
	private static final String SIT_FRAMERATE = "sitFramerate";
	private static final String SHARPEN_FRAMERATE = "sharpenFramerate";
	private static final String SCRATCH_FRAMERATE = "scratchFramerate";
	private static final String LOAD_FRAMERATE = "loadFramerate";

	private static final String SLEEP_DELAY = "sleepDelay";
	private static final String YAWN_DELAY = "yawnDelay";
	private static final String SURPRISE_DELAY = "surpriseDelay";
	private static final String ACTIVITY_MODE = "activityMode";
	private static final String PERSONALITY_PROFILE = "personalityProfile";
	private static final String FOLLOW_MOUSE = "followMouse";
	private static final String ENABLE_IDLE_REACTIONS = "idleReactions";
	private static final String ENABLE_TAIL_SWING = "tailSwing";
	private static final String ENABLE_WINDOW_TRANSPARENCY = "windowTransparency";
	private static final String SMART_BOUNDS = "smartBounds";
	private static final String CLICK_TO_TOGGLE = "clickToToggle";
	private static final String COMPACT_MODE = "compactMode";
	private static final String FOCUS_MODE = "focusMode";
	private static final String CONTEXT_MENU = "contextMenu";
	private static final String CAT_SCALE_PERCENT = "catScalePercent";
	private static final String FRAME_SCALE_MULTIPLIER = "frameScaleMultiplier";
	private static final String MOTION_INTENSITY = "motionIntensity";
	private static final String DESKTOP_MARGIN = "desktopMargin";
	private static final String EDITOR_PADDING = "editorPadding";

	private Settings settings;
	private int triggerDist;
	private int catchDist;
	private int runDist;
	private int minDelay;
	private int runDelay;
	private int sitDelay;
	private int scratchDelay;
	private int sharpenDelay;
	private int loadDelay;
	private int sleepDelay;
	private int yawnDelay;
	private int surpriseDelay;
	private String activityMode;
	private String personalityProfile;
	private boolean followMouse;
	private boolean idleReactions;
	private boolean tailSwing;
	private boolean windowTransparency;
	private boolean smartBounds;
	private boolean clickToToggle;
	private boolean compactMode;
	private boolean focusMode;
	private boolean contextMenu;
	private int catScalePercent;
	private int frameScaleMultiplier;
	private int motionIntensity;
	private int desktopMargin;
	private int editorPadding;
	private int offsetX;
	private int offsetY;

	public KairoSettings() {
		settings=new Settings("kairo.properties");
		load();
	}

	public String getTitle() { return settings.getString(TITLE);}

	/** Convert frames-per-seconds to milliseconds */
	private int getDelay(String key)
	{
		// Convert the framerate settings into milliseconds
		Integer ret = settings.getInt(key,100);

		return 1000/ret;
	}

	public int getTriggerDist() { return triggerDist;}
	public int getCatchDist() { return catchDist;}
	public int getRunDist() { return runDist;}
	public int getOffsetX() { return offsetX;}
	public int getOffsetY() { return offsetY;}
	public int getMinDelay() { return minDelay;}
	public int getRunDelay() { return runDelay;}
	public int getSitDelay() { return sitDelay;}
	public int getScratchDelay() { return scratchDelay;}
	public int getSharpenDelay() { return sharpenDelay;}
	public int getLoadDelay() { return loadDelay;}
	public int getSleepDelay() { return sleepDelay;}
	public int getYawnDelay() { return yawnDelay;}
	public int getSurpriseDelay() { return surpriseDelay;}
	public String getActivityMode() { return activityMode; }
	public String getPersonalityProfile() { return personalityProfile; }
	public boolean isFollowMouse() { return followMouse; }
	public boolean isIdleReactionsEnabled() { return idleReactions; }
	public boolean isTailSwingEnabled() { return tailSwing; }
	public boolean isWindowTransparencyEnabled() { return windowTransparency; }
	public boolean isSmartBoundsEnabled() { return smartBounds; }
	public boolean isClickToToggleEnabled() { return clickToToggle; }
	public boolean isCompactModeEnabled() { return compactMode; }
	public boolean isFocusModeEnabled() { return focusMode; }
	public boolean isContextMenuEnabled() { return contextMenu; }
	public int getCatScalePercent() { return catScalePercent; }
	public int getFrameScaleMultiplier() { return frameScaleMultiplier; }
	public int getMotionIntensity() { return motionIntensity; }
	public int getDesktopMargin() { return desktopMargin; }
	public int getEditorPadding() { return editorPadding; }

	public void setTriggerDist(int value) { triggerDist = value; settings.setInt(TRIGGER_DIST, value); }
	public void setCatchDist(int value) { catchDist = value; settings.setInt(CATCH_DIST, value); }
	public void setRunDist(int value) { runDist = value; settings.setInt(RUN_DIST, value); }
	public void setOffsetX(int value) { offsetX = value; settings.setInt(OFFSETX, value); }
	public void setOffsetY(int value) { offsetY = value; settings.setInt(OFFSETY, value); }
	public void setActivityMode(String value) { activityMode = value; settings.setString(ACTIVITY_MODE, value); }
	public void setPersonalityProfile(String value) { personalityProfile = value; settings.setString(PERSONALITY_PROFILE, value); }
	public void setFollowMouse(boolean value) { followMouse = value; settings.setBoolean(FOLLOW_MOUSE, value); }
	public void setIdleReactions(boolean value) { idleReactions = value; settings.setBoolean(ENABLE_IDLE_REACTIONS, value); }
	public void setTailSwing(boolean value) { tailSwing = value; settings.setBoolean(ENABLE_TAIL_SWING, value); }
	public void setWindowTransparency(boolean value) { windowTransparency = value; settings.setBoolean(ENABLE_WINDOW_TRANSPARENCY, value); }
	public void setSmartBounds(boolean value) { smartBounds = value; settings.setBoolean(SMART_BOUNDS, value); }
	public void setClickToToggle(boolean value) { clickToToggle = value; settings.setBoolean(CLICK_TO_TOGGLE, value); }
	public void setCompactMode(boolean value) { compactMode = value; settings.setBoolean(COMPACT_MODE, value); }
	public void setFocusMode(boolean value) { focusMode = value; settings.setBoolean(FOCUS_MODE, value); }
	public void setContextMenu(boolean value) { contextMenu = value; settings.setBoolean(CONTEXT_MENU, value); }
	public void setCatScalePercent(int value) { catScalePercent = Math.max(100, value); settings.setInt(CAT_SCALE_PERCENT, catScalePercent); }
	public void setFrameScaleMultiplier(int value) { frameScaleMultiplier = clampScaleMultiplier(value); settings.setInt(FRAME_SCALE_MULTIPLIER, frameScaleMultiplier); }
	public void setMotionIntensity(int value) { motionIntensity = clampPercent(value); settings.setInt(MOTION_INTENSITY, motionIntensity); }
	public void setDesktopMargin(int value) { desktopMargin = Math.max(0, value); settings.setInt(DESKTOP_MARGIN, desktopMargin); }
	public void setEditorPadding(int value) { editorPadding = Math.max(0, value); settings.setInt(EDITOR_PADDING, editorPadding); }
	public final void save() { settings.save(); }

	public final void load()
	{
		settings.load();
		String hello=settings.getString(HELLO);
		if ( hello!=null ) System.out.println(hello);

		triggerDist = settings.getInt(TRIGGER_DIST,16);
		catchDist = settings.getInt(CATCH_DIST,16);
		runDist = settings.getInt(RUN_DIST,16);
		offsetX = settings.getInt(OFFSETX,0);
		offsetY = settings.getInt(OFFSETY,0);
		activityMode = settings.getString(ACTIVITY_MODE, "calm");
		personalityProfile = settings.getString(PERSONALITY_PROFILE, activityMode);
		followMouse = settings.getBoolean(FOLLOW_MOUSE, true);
		idleReactions = settings.getBoolean(ENABLE_IDLE_REACTIONS, true);
		tailSwing = settings.getBoolean(ENABLE_TAIL_SWING, true);
		windowTransparency = settings.getBoolean(ENABLE_WINDOW_TRANSPARENCY, true);
		smartBounds = settings.getBoolean(SMART_BOUNDS, true);
		clickToToggle = settings.getBoolean(CLICK_TO_TOGGLE, true);
		compactMode = settings.getBoolean(COMPACT_MODE, false);
		focusMode = settings.getBoolean(FOCUS_MODE, false);
		contextMenu = settings.getBoolean(CONTEXT_MENU, true);
		catScalePercent = Math.max(100, settings.getInt(CAT_SCALE_PERCENT, 100));
		frameScaleMultiplier = clampScaleMultiplier(settings.getInt(FRAME_SCALE_MULTIPLIER, 1));
		motionIntensity = clampPercent(settings.getInt(MOTION_INTENSITY, 100));
		desktopMargin = Math.max(0, settings.getInt(DESKTOP_MARGIN, 24));
		editorPadding = Math.max(0, settings.getInt(EDITOR_PADDING, 40));

		sleepDelay = settings.getInt(SLEEP_DELAY,1000);
		yawnDelay = settings.getInt(YAWN_DELAY,1000);
		surpriseDelay = settings.getInt(SURPRISE_DELAY,1000);

		minDelay=getDelay(MAX_FRAMERATE);
		runDelay=getDelay(RUN_FRAMERATE);
		sitDelay=getDelay(SIT_FRAMERATE);
		scratchDelay=getDelay(SCRATCH_FRAMERATE);
		sharpenDelay=getDelay(SHARPEN_FRAMERATE);
		loadDelay=getDelay(LOAD_FRAMERATE);

	}

	private int clampPercent(int value) {
		return Math.max(50, Math.min(150, value));
	}

	private int clampScaleMultiplier(int value) {
		return Math.max(1, Math.min(4, value));
	}

}
