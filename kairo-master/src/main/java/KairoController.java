/*
 * @(#)KairoController.java  2.0.1  2019-02-02
 *
 * Copyright (c) 2019 Jerry Reno
 * This is public domain software, under the terms of the UNLICENSE
 * http://unlicense.org 
 * 
 * This is an extended version of the earlier Java Kairo 1.0:
 * Copyright (c) 2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 *
 * This source code is free to everyone.
 *
 * This is a desktop adaptation of the applet
 * JAVA KAIRO V1.0 by Chris Parent, 1999.
 * https://en.wikipedia.org/wiki/Cat
 */

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 * Kairo the cat.
 * <p>
 * This program loads in 32 images of Kairo, and tests them. (to show you that
 * they've been loaded). Kairo will chase your mouse cursor around the desktop.
 * Once she's over it and the mouse doesn't move she'll prepare to take a nap.
 * If the mouse goes outside the desktop she will reach the border and try
 * to dig for it. She'll eventually give up, and fall asleep.
 *
 *
 * @author Werner Randelshofer (adaption for desktop)
 *		 Chris Parent (original code)
 * @version 1.0.1 2010-07-17 Fixes timers. Sets longer sleep times when the
 * cat sleeps.
 * <br>1.0 2010-07-16 Created.
 */
public class KairoController {
	//
	//Constants

	private static final double pi = Math.PI;
	private enum Position { OVER, UNDER, LEFT, RIGHT };

	private enum BehaviorState {
		INIT,
		CHASING,
		IDLE,
		SURPRISED
	}

	private enum FacingPosition {
		OVER,
		UNDER,
		LEFT,
		RIGHT
	}

	private static final int FRAME_DEFAULT = 0;
	private static final int FRAME_SIT = 31;
	private static final int FRAME_LICK = 25;
	private static final int FRAME_SCRATCH = 27;
	private static final int FRAME_YAWN = 26;
	private static final int FRAME_SLEEP = 29;
	private static final int FRAME_SLEEPING = 30;
	private static final int FRAME_SURPRISE = 32;

	//
	//Variables
	private boolean windowMode;
	private int ox, oy;   //image pos.
	private int no;	   //image number.
	private int init;	 //for image loading initialize counter
	private BehaviorState state;
	private int slp;	  //sleep time
	private int ilc1;	 //image loop counter
	private int ilc2;	 //second loop counter
	private boolean mouseMoved; //mouse move, flag
	private boolean isIdleAnimationPrepared;
	private int idleMood;
	private int personalityPhase;
	private long lastInteractionTime;
	private long lastMoodShift;
	private long lastTailMove;
	private String activityMode;
	private boolean followMouse;
	private boolean idleReactionsEnabled;
	private boolean tailSwingEnabled;
	private boolean smartBoundsEnabled;
	private ImageIcon image[];  //images
	private Point windowOffset = new Point(-16, -30);
	private Rectangle kairoBounds = new Rectangle();
	private Timer timer;
	private int w,h; // size of icons

	//
	// UI Components
	private JWindow invisibleWindow;
	private JFrame catbox;
	private JLabel freeLabel,boxLabel;
	private KairoSettings settings;

	/** Creates new form Kairo */
	public KairoController(KairoSettings settings,JWindow invisibleWindow,JFrame visibleWindow,JLabel free,JLabel boxed) {
		this.settings=settings;
		this.invisibleWindow=invisibleWindow;
		this.catbox=visibleWindow;
		this.freeLabel=free;
		this.boxLabel=boxed;
		this.windowMode=false;

		this.init=0;
		this.state = BehaviorState.INIT;
		this.slp=0;
		this.idleMood = 0;
		this.personalityPhase = 0;
		this.lastInteractionTime = System.currentTimeMillis();
		this.lastMoodShift = this.lastInteractionTime;
		this.lastTailMove = this.lastInteractionTime;
		this.isIdleAnimationPrepared = false;
		this.activityMode = this.settings.getActivityMode();
		this.followMouse = this.settings.isFollowMouse();
		this.idleReactionsEnabled = this.settings.isIdleReactionsEnabled();
		this.tailSwingEnabled = this.settings.isTailSwingEnabled();
		this.smartBoundsEnabled = this.settings.isSmartBoundsEnabled();

		loadKitten();
		w=image[1].getIconWidth();
		h=image[1].getIconHeight();
		invisibleWindow.setSize(w,h);
		//invisibleWindow.setLocation(ox + windowOffset.x, oy + windowOffset.y);
		boxLabel.setSize(w,h);
		catbox.setSize(16*w, 9*h);

		timer = new Timer(50, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					locateMouseAndAnimateCat();
				}
				catch (Exception ex) {}
			}
		});
		timer.setRepeats(true);
		timer.start();

	}

	private void loadKitten() {
		// Note: The files start with 1. image[0] is a copy of 25.
		// image[0] is a default, image[25] is part of the wash animation with image[31]
		image = new ImageIcon[33];
		for (int i = 1; i <= 32; i++) {
			image[i] = new ImageIcon(Kairo.class.getResource("images/" + i + ".GIF"));
		}
		image[0]=image[25];
	}

	public int getWidth() { return w;}
	public int getHeight() { return h;}
	public boolean getWindowMode() { return this.windowMode;}

	private void calculateBounds(PointerInfo pointerInfo)
	{
		if(windowMode)
		{
			// kairoBounds is the area of the window that the Kairo can be in
			Point panePoint = catbox.getContentPane().getLocationOnScreen();
			Insets paneInsets=catbox.getContentPane().getInsets();
			Dimension sz=catbox.getContentPane().getSize();

			kairoBounds.x = panePoint.x + paneInsets.left + w/2;
			kairoBounds.y = panePoint.y + paneInsets.top + h;
			kairoBounds.width = Math.max(0, sz.width - paneInsets.left - paneInsets.right - w);
			kairoBounds.height = Math.max(0, sz.height - paneInsets.top - paneInsets.bottom - h);
			return;
		}

		Rectangle usableBounds = getDesktopBounds(pointerInfo);
		if (usableBounds == null) return;

		kairoBounds = usableBounds;
		if (smartBoundsEnabled) {
			kairoBounds.x += w / 2;
			kairoBounds.y += h;
			kairoBounds.width = Math.max(0, kairoBounds.width - w);
			kairoBounds.height = Math.max(0, kairoBounds.height - h);
		}
		else {
			kairoBounds.x += 8;
			kairoBounds.y += 8;
			kairoBounds.width = Math.max(0, kairoBounds.width - 16);
			kairoBounds.height = Math.max(0, kairoBounds.height - 16);
		}
	}

	private Rectangle getDesktopBounds(PointerInfo pointerInfo) {
		GraphicsDevice pointerDevice = (pointerInfo != null) ? pointerInfo.getDevice() : null;
		if (pointerDevice != null) {
			GraphicsConfiguration gc = pointerDevice.getDefaultConfiguration();
			if (gc != null) {
				Rectangle screenBounds = gc.getBounds();
				if (screenBounds != null) {
					Toolkit tk = invisibleWindow.getToolkit();
					Insets screenInsets = tk.getScreenInsets(gc);
					if (screenInsets != null) {
						Rectangle safeBounds = new Rectangle(
							screenBounds.x + screenInsets.left,
							screenBounds.y + screenInsets.top,
							screenBounds.width - screenInsets.left - screenInsets.right,
							screenBounds.height - screenInsets.top - screenInsets.bottom);
						if (safeBounds.width > 0 && safeBounds.height > 0) {
							return safeBounds;
						}
					}
				}
			}
		}

		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = environment.getScreenDevices();
		Rectangle union = null;
		for (GraphicsDevice device : devices) {
			GraphicsConfiguration gc = device.getDefaultConfiguration();
			if (gc == null) continue;
			Rectangle deviceBounds = gc.getBounds();
			if (deviceBounds == null) continue;
			if (union == null) {
				union = (Rectangle) deviceBounds.clone();
			}
			else {
				union = union.union(deviceBounds);
			}
		}
		return union != null ? union : new Rectangle(0, 0, 1920, 1080);
	}

	/** Locates the mouse on the screen and determines what the cat shall do. */
	public void locateMouseAndAnimateCat() {
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if (pointerInfo==null) return;
		Point mouseLocation = pointerInfo.getLocation();
		if (mouseLocation==null) return;

		int mx = mouseLocation.x + settings.getOffsetX();
		int my = mouseLocation.y + settings.getOffsetY();

		calculateBounds(pointerInfo);

		//Determines what the cat should do, if the mouse moves
		Position pos = null;
		boolean out = !kairoBounds.contains(mx, my);
		int x = mx;
		int y = my;
		if (out) {
			if (y < kairoBounds.y) {
				y = kairoBounds.y;
				pos = Position.OVER;
			}
			if (y > kairoBounds.y + kairoBounds.height) {
				y = kairoBounds.y + kairoBounds.height;
				pos = Position.UNDER;
			}
			if (x < kairoBounds.x) {
				x = kairoBounds.x;
				pos = Position.LEFT;
			}
			if (x > kairoBounds.x + kairoBounds.width) {
				x = kairoBounds.x + kairoBounds.width;
				pos = Position.RIGHT;
			}
		}

		//image-mouse distance
		// x,y are the mouse location on screen
		// ox,oy are the old kairo location on screen
		int dx = x - ox;
		int dy = oy - y;
		double dist = Math.sqrt(dx * dx + dy * dy); //distance formula (from mouse to cat)
		double theta = Math.atan2(dy, dx);	 //angle from mouse to cat

		// If the kitty is already moving, keep moving;
		// If not moving, but the mouse is over 64px away, wake up!
		mouseMoved = mouseMoved || dist > settings.getTriggerDist();
		if (mouseMoved) {
			long now = System.currentTimeMillis();
			lastInteractionTime = now;
			if (now - lastMoodShift > 2500L) {
				personalityPhase = (personalityPhase + 1) % 6;
				lastMoodShift = now;
			}
			if ("playful".equals(activityMode) && tailSwingEnabled) {
				lastTailMove = now;
			}
		}
		//
		slp = Math.max(0, slp - timer.getDelay());
		if (slp == 0)
		{
			animateCat(pos,theta,dist);
		}
	}

	private void animateCat(Position pos,double theta,double dist)
	{
		// There are four states.
		// 0. Initialization (init < 33)
		// 1. Chasing the mouse (doMove = true)
		// 2. Sleeping or preparing to sleep
		// 3. Surprised (was asleep, but the mouse moved) (no = 32)
		boolean doMove = false;
		if (state == BehaviorState.INIT) {
			if (init < 33) {
				doMove = true;
				//tells the user the program is testing the images, and tells them
				//when the test is done.
				slp = settings.getLoadDelay();
				ox = kairoBounds.x + kairoBounds.width / 2;
				oy = kairoBounds.y + kairoBounds.height / 2;
				no = init;
				init++;
			}
			else {
				state = BehaviorState.CHASING;
			}
		}
		else if (state == BehaviorState.CHASING) {
			doMove = true;
			slp = settings.getRunDelay(); // 5fps, 200ms
			// note that ox,oy are screen-relative
			double run = settings.getRunDist(); // 16
			if ("calm".equals(activityMode)) {
				run *= 0.8;
			}
			else if ("playful".equals(activityMode)) {
				run *= 1.35;
			}
			if (run > dist) {
				run = dist;
			}
			ox = (int) (ox + Math.cos(theta) * run);
			oy = (int) (oy - Math.sin(theta) * run);
			dist = dist - run;
			if (dist < settings.getCatchDist()) {
				// mouse has been caught!
				state = BehaviorState.IDLE;
			}
			selectRunningFrame(theta);
			if ("playful".equals(activityMode) && idleReactionsEnabled && (System.currentTimeMillis() - lastMoodShift) > 1200L) {
				no = (no == FRAME_LICK) ? FRAME_SCRATCH : no;
			}
			if (System.currentTimeMillis() - lastTailMove > 550L) {
				lastTailMove = System.currentTimeMillis();
				if (tailSwingEnabled && "playful".equals(activityMode) && (personalityPhase % 2 == 0)) {
					no = (no == 5 || no == 6) ? 3 : no;
				}
			}
			//We have moved toward the mouse, so set mouseMoved back to false
			mouseMoved = false;
		}
		else {
			advanceIdleAnimation(pos);
			if (mouseMoved) {
				// State 3. Go to state 1 and chase the mouse,
				// after a suitable delay.
				//re-initialize some variables
				slp = settings.getSurpriseDelay();
				no = FRAME_SURPRISE;
				ilc1 = 0;
				ilc2 = 0;
				state = BehaviorState.CHASING;
			}
		}
		if (doMove) {
			moveCat();
		}
		freeLabel.setIcon(image[no]);
		boxLabel.setIcon(image[no]);
	}

	private void selectRunningFrame(double theta) {
		/*
		The following conditions determine what image should be shown.
		Remember there are two images for each action. For example if the cat's
		going right, display the cat with open legs and then with close legs,
		open, and so on.
		 */
		if (theta >= -pi / 8 && theta <= pi / 8) //right
		{
			no = (no == 5) ? 6 : 5;
		}
		if (theta > pi / 8 && theta < 3 * pi / 8) //upper-right
		{
			no = (no == 3) ? 4 : 3;
		}
		if (theta >= 3 * pi / 8 && theta <= 5 * pi / 8) //up
		{
			no = (no == 1) ? 2 : 1;
		}
		if (theta > 5 * pi / 8 && theta < 7 * pi / 8) //upper-left
		{
			no = (no == 15) ? 16 : 15;
		}
		if (theta >= 7 * pi / 8 || theta <= -7 * pi / 8) //left
		{
			no = (no == 13) ? 14 : 13;
		}
		if (theta > -7 * pi / 8 && theta < -5 * pi / 8) //bottom-left
		{
			no = (no == 11) ? 12 : 11;
		}
		if (theta >= -5 * pi / 8 && theta <= -3 * pi / 8) //down
		{
			no = (no == 9) ? 10 : 9;
		}
		if (theta > -3 * pi / 8 && theta < -pi / 8) //bottom-right
		{
			no = (no == 7) ? 8 : 7;
		}
	}

	private void advanceIdleAnimation(Position pos) {
		boolean isLongIdle = System.currentTimeMillis() - lastInteractionTime > getIdleThreshold();
		if (isLongIdle && !isIdleAnimationPrepared) {
			personalityPhase = (personalityPhase + 1) % 6;
			idleMood = (idleMood + 1) % 6;
			lastMoodShift = System.currentTimeMillis();
			isIdleAnimationPrepared = true;
			if (!idleReactionsEnabled) {
				no = FRAME_DEFAULT;
			}
			else if ("playful".equals(activityMode)) {
				no = FRAME_LICK;
			}
			else if ("active".equals(activityMode)) {
				no = FRAME_SCRATCH;
			}
			else {
				no = FRAME_YAWN;
			}
		}
		if (!isLongIdle) {
			isIdleAnimationPrepared = false;
		}

		switch (no) {
			case FRAME_DEFAULT:
				if (pos != null) {
					slp = settings.getSharpenDelay();
					switch (pos) {
						case OVER:
							no = 17;
							break;
						case UNDER:
							no = 21;
							break;
						case LEFT:
							no = 23;
							break;
						case RIGHT:
							no = 19;
							break;
						default:
							slp = settings.getSitDelay();
							no = FRAME_SIT;
							break;
					}
					break;
				}
				slp = settings.getSitDelay();
				no = FRAME_SIT;
				break;
			case 17:
				slp = settings.getSharpenDelay();
				no = 18;
				ilc1++;
				if (ilc1 == 6) {
					no = selectIdleMoodFrame();
					ilc1 = 0;
				}
				break;
			case 18:
				slp = settings.getSharpenDelay();
				no = 17;
				break;
			case 21:
				slp = settings.getSharpenDelay();
				no = 22;
				ilc1++;
				if (ilc1 == 6) {
					no = selectIdleMoodFrame();
					ilc1 = 0;
				}
				break;
			case 22:
				slp = settings.getSharpenDelay();
				no = 21;
				break;
			case 23:
				slp = settings.getSharpenDelay();
				no = 24;
				ilc1++;
				if (ilc1 == 6) {
					no = selectIdleMoodFrame();
					ilc1 = 0;
				}
				break;
			case 24:
				slp = settings.getSharpenDelay();
				no = 23;
				break;
			case 19:
				slp = settings.getSharpenDelay();
				no = 20;
				ilc1++;
				if (ilc1 == 6) {
					no = selectIdleMoodFrame();
					ilc1 = 0;
				}
				break;
			case 20:
				slp = settings.getSharpenDelay();
				no = 19;
				break;
			case FRAME_SIT:
				slp = settings.getSitDelay();
				no = FRAME_LICK;
				ilc1++;
				if (ilc1 == 6) {
					slp = settings.getScratchDelay();
					no = selectIdleMoodFrame();
					ilc1 = 0;
				}
				break;
			case FRAME_LICK:
				slp = settings.getSitDelay();
				no = FRAME_SIT;
				break;
			case FRAME_SCRATCH:
				slp = settings.getScratchDelay();
				no = 28;
				break;
			case 28:
				no = FRAME_SCRATCH;
				ilc2++;
				if (ilc2 == 4) {
					no = selectIdleMoodFrame();
					slp = settings.getYawnDelay();
					ilc2 = 0;
				}
				break;
			case FRAME_YAWN:
				no = FRAME_SLEEP;
				slp = settings.getSleepDelay();
				break;
			case FRAME_SLEEP:
				no = FRAME_SLEEPING;
				slp = settings.getSleepDelay();
				break;
			case FRAME_SLEEPING:
				no = FRAME_SLEEP;
				slp = settings.getSleepDelay();
				break;
			default:
				no = FRAME_DEFAULT;
				break;
		}
	}

	private long getIdleThreshold() {
		if ("calm".equals(activityMode)) {
			return 8000L;
		}
		if ("active".equals(activityMode)) {
			return 3000L;
		}
		return 4000L;
	}

	private int selectIdleMoodFrame() {
		int phase = (personalityPhase + idleMood) % 6;
		if ("calm".equals(activityMode)) {
			if (phase == 0 || phase == 3) return FRAME_SCRATCH;
			if (phase == 1 || phase == 4) return FRAME_YAWN;
			if (phase == 2) return FRAME_SIT;
			return FRAME_LICK;
		}
		if ("active".equals(activityMode)) {
			if (phase == 0 || phase == 2) return FRAME_SCRATCH;
			if (phase == 1) return FRAME_LICK;
			if (phase == 3) return FRAME_SIT;
			return FRAME_YAWN;
		}
		if (phase == 0 || phase == 2) return FRAME_LICK;
		if (phase == 1 || phase == 4) return FRAME_SCRATCH;
		if (phase == 3) return FRAME_SIT;
		return FRAME_YAWN;
	}

	private void moveCat() {
		if (windowMode) {
			// note that ox,oy are screen-relative
			int x = clamp(ox, kairoBounds.x, kairoBounds.x + kairoBounds.width) - kairoBounds.x;
			int y = clamp(oy, kairoBounds.y, kairoBounds.y + kairoBounds.height) - kairoBounds.y;
			boxLabel.setLocation(x, y);
		}
		else {
			int x = clamp(ox, kairoBounds.x, kairoBounds.x + kairoBounds.width) + windowOffset.x;
			int y = clamp(oy, kairoBounds.y, kairoBounds.y + kairoBounds.height) + windowOffset.y;
			if (followMouse) {
				Point mouse = MouseInfo.getPointerInfo() != null ? MouseInfo.getPointerInfo().getLocation() : null;
				if (mouse != null) {
					x = mouse.x + settings.getOffsetX();
					y = mouse.y + settings.getOffsetY();
				}
			}
			invisibleWindow.setLocation(x, y);
		}
	}

	private int clamp(int value, int min, int max) {
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	public void setWindowMode(boolean windowed) {
		this.windowMode = windowed;
		repositionCatForCurrentMode();
	}

	public void setActivityMode(String mode) {
		this.activityMode = (mode == null) ? "calm" : mode;
		this.idleMood = 0;
		this.personalityPhase = 0;
		this.lastMoodShift = System.currentTimeMillis();
	}

	public void setBehaviorOptions() {
		this.followMouse = this.settings.isFollowMouse();
		this.idleReactionsEnabled = this.settings.isIdleReactionsEnabled();
		this.tailSwingEnabled = this.settings.isTailSwingEnabled();
		this.smartBoundsEnabled = this.settings.isSmartBoundsEnabled();
		this.lastInteractionTime = System.currentTimeMillis();
	}

	public void repositionCatForCurrentMode() {
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if (pointerInfo == null) return;
		calculateBounds(pointerInfo);
		if (windowMode) {
			ox = kairoBounds.x + kairoBounds.width / 2;
			oy = kairoBounds.y + kairoBounds.height / 2;
			if (catbox != null) {
				Dimension d = catbox.getSize();
				catbox.setLocation(ox - d.width / 2, Math.max(0, oy - d.height / 2));
			}
		}
		else {
			Point mouse = pointerInfo.getLocation();
			int cx = mouse.x + settings.getOffsetX();
			int cy = mouse.y + settings.getOffsetY();
			ox = Math.max(kairoBounds.x, Math.min(cx, kairoBounds.x + kairoBounds.width));
			oy = Math.max(kairoBounds.y, Math.min(cy, kairoBounds.y + kairoBounds.height));
		}
		moveCat();
	}

	public void recenterCat() {
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if (pointerInfo == null) return;
		calculateBounds(pointerInfo);
		if (windowMode) {
			ox = kairoBounds.x + kairoBounds.width / 2;
			oy = kairoBounds.y + kairoBounds.height / 2;
		}
		else {
			Point mouse = pointerInfo.getLocation();
			ox = mouse.x + settings.getOffsetX();
			oy = mouse.y + settings.getOffsetY();
			ox = Math.max(kairoBounds.x, Math.min(ox, kairoBounds.x + kairoBounds.width));
			oy = Math.max(kairoBounds.y, Math.min(oy, kairoBounds.y + kairoBounds.height));
		}
		lastInteractionTime = System.currentTimeMillis();
		mouseMoved = true;
		moveCat();
	}

	public void moveCatInBox() {
		if ( windowMode ) {
			Dimension d=catbox.getSize();
			catbox.setLocation(ox-d.width/2,Math.max(0,oy-d.height/2));
		}
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if (pointerInfo==null) return;
		calculateBounds(pointerInfo);
		moveCat();
	}

	public void catboxDeiconified() {
		// Calculate the new kairoBounds
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if (pointerInfo==null) return;
		calculateBounds(pointerInfo);
		// Move the cat to the center of the window
		ox=kairoBounds.x+kairoBounds.width/2;
		oy=kairoBounds.y+kairoBounds.height/2;
	}

	public void catboxMoved() {
		if ( windowMode ) {
			// The window moved. We have the old location in kairoBounds.
			int oldx=kairoBounds.x;
			int oldy=kairoBounds.y;
			// Calculate the new kairoBounds
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			if (pointerInfo==null) return;
			calculateBounds(pointerInfo);
			// How much did it move?
			int dx=kairoBounds.x-oldx;
			int dy=kairoBounds.y-oldy;
			ox+=dx;
			oy+=dy;
			// don't need to move the jlabel, it will have moved with the window
		}
	}

}

