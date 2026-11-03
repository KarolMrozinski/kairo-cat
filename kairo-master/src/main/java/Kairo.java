/*
 * @(#)Kairo.java  2.0.1  2019-02-29
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

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
public class Kairo {

	//
	//Constants
	private static Dimension MINSIZE=new Dimension(64,64);
	private static Dimension PRFSIZE=new Dimension(64*16,64*9);

	//
	// UI Components
	private JFrame catbox;
	private JWindow invisibleWindow;
	private JLabel freeLabel,boxLabel;
	private KairoSettings settings;
	private KairoController controller;
	private JDialog settingsDialog;

	/** Creates new form Kairo */
	public Kairo() {
		settings=new KairoSettings();
		initComponents();
		invisibleWindow.setLocation(100,100);
		controller=new KairoController(settings,invisibleWindow,catbox,freeLabel,boxLabel);
		setWindowMode(false);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 */
	private void initComponents() {
		String title=settings.getTitle();
		if ( title==null ) title="Kairo";
		catbox=new JFrame(title);
		catbox.setBackground(new Color(235, 238, 242, 255));
		catbox.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		catbox.setAlwaysOnTop(true);
		catbox.setAutoRequestFocus(false);
		catbox.setFocusable(false);
		invisibleWindow=new JWindow();
		invisibleWindow.getRootPane().putClientProperty("Window.shadow", false);
		invisibleWindow.setBackground(new Color(0, 0, 0, 0));
		invisibleWindow.setAlwaysOnTop(true);
		invisibleWindow.setFocusable(false);

		freeLabel = new JLabel();
		boxLabel = new JLabel();
		freeLabel.setOpaque(false);
		boxLabel.setOpaque(false);

		MouseListener mouseMoveListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if ((evt.getSource() == freeLabel)||(evt.getSource() == boxLabel)) {
					imageClicked(evt);
				}
			}
		};

		freeLabel.addMouseListener(mouseMoveListener);
		boxLabel.addMouseListener(mouseMoveListener);
		invisibleWindow.getContentPane().add(freeLabel, BorderLayout.CENTER);
		catbox.getContentPane().add(boxLabel);
		catbox.pack();

		// We really don't want a layout manager messing with us.
		// Maybe this class should BE a layout manager.
		catbox.getContentPane().setLayout(new LayoutManager() {
			public void addLayoutComponent(String n,Component c) {}
			public void layoutContainer(Container p){}
			public Dimension minimumLayoutSize(Container p) { return MINSIZE;}
			public Dimension preferredLayoutSize(Container p) { return PRFSIZE;}
			public void removeLayoutComponent(Component c) {}
		});

		catbox.addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				controller.catboxMoved();
			}
		});
		catbox.addWindowListener(new WindowAdapter() {
			public void windowDeiconified(WindowEvent e) {
				controller.catboxDeiconified();
			}
		});

		invisibleWindow.pack();
	}

	private void imageClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON3) {
			showSettingsDialog();
			return;
		}
		if (settings.isClickToToggleEnabled()) {
			setWindowMode(!controller.getWindowMode());
		}
	}

	private void showSettingsDialog() {
		if (settingsDialog == null) {
			settingsDialog = createSettingsDialog();
		}
		settingsDialog.pack();
		settingsDialog.setLocationRelativeTo(catbox);
		settingsDialog.setVisible(true);
	}

	private JDialog createSettingsDialog() {
		final JDialog dialog = new JDialog(catbox, "Kairo settings", true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);

		final JPanel content = new JPanel(new BorderLayout(12, 12));
		content.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

		final JPanel fields = new JPanel(new GridLayout(0, 2, 10, 8));
		fields.setBorder(javax.swing.BorderFactory.createTitledBorder("Behaviour"));

		final JSlider triggerDist = new JSlider(1, 200, settings.getTriggerDist());
		final JSlider catchDist = new JSlider(1, 100, settings.getCatchDist());
		final JSlider runDist = new JSlider(1, 100, settings.getRunDist());
		final JSlider offsetX = new JSlider(-100, 100, settings.getOffsetX());
		final JSlider offsetY = new JSlider(-100, 100, settings.getOffsetY());
		final javax.swing.JComboBox<String> activityMode = new javax.swing.JComboBox<String>(new String[] { "calm", "active", "playful" });
		activityMode.setSelectedItem(settings.getActivityMode());
		final javax.swing.JCheckBox followMouse = new javax.swing.JCheckBox("Follow mouse", settings.isFollowMouse());
		final javax.swing.JCheckBox idleReactions = new javax.swing.JCheckBox("Idle reactions", settings.isIdleReactionsEnabled());
		final javax.swing.JCheckBox tailSwing = new javax.swing.JCheckBox("Tail swing", settings.isTailSwingEnabled());
		final javax.swing.JCheckBox clickToToggle = new javax.swing.JCheckBox("Click-to-toggle", settings.isClickToToggleEnabled());
		final javax.swing.JCheckBox smartBounds = new javax.swing.JCheckBox("Smart monitor bounds", settings.isSmartBoundsEnabled());
		final javax.swing.JCheckBox transparency = new javax.swing.JCheckBox("Window transparency", settings.isWindowTransparencyEnabled());

		addLabeledControl(fields, "Trigger distance", triggerDist);
		addLabeledControl(fields, "Catch distance", catchDist);
		addLabeledControl(fields, "Run distance", runDist);
		addLabeledControl(fields, "Offset X", offsetX);
		addLabeledControl(fields, "Offset Y", offsetY);
		addLabeledControl(fields, "Activity mode", activityMode);
		fields.add(followMouse);
		fields.add(idleReactions);
		fields.add(tailSwing);
		fields.add(clickToToggle);
		fields.add(smartBounds);
		fields.add(transparency);

		final JPanel buttons = new JPanel(new GridLayout(1, 3, 8, 0));
		final JButton centerButton = new JButton("Center Kairo");
		centerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.recenterCat();
			}
		});
		final JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				settings.setTriggerDist(triggerDist.getValue());
				settings.setCatchDist(catchDist.getValue());
				settings.setRunDist(runDist.getValue());
				settings.setOffsetX(offsetX.getValue());
				settings.setOffsetY(offsetY.getValue());
				settings.setActivityMode((String) activityMode.getSelectedItem());
				settings.setFollowMouse(followMouse.isSelected());
				settings.setIdleReactions(idleReactions.isSelected());
				settings.setTailSwing(tailSwing.isSelected());
				settings.setClickToToggle(clickToToggle.isSelected());
				settings.setSmartBounds(smartBounds.isSelected());
				settings.setWindowTransparency(transparency.isSelected());
				settings.save();
				controller.setActivityMode(settings.getActivityMode());
				controller.setBehaviorOptions();
				dialog.dispose();
			}
		});
		final JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dialog.dispose();
			}
		});
		buttons.add(centerButton);
		buttons.add(applyButton);
		buttons.add(closeButton);

		content.add(fields, BorderLayout.CENTER);
		content.add(buttons, BorderLayout.SOUTH);
		dialog.setContentPane(content);
		dialog.pack();
		return dialog;
	}

	private void addLabeledControl(JPanel target, String labelText, Component control) {
		JLabel label = new JLabel(labelText);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		target.add(label);
		target.add(control);
	}

	private String resolveWindowTitle() {
		String title = settings.getTitle();
		return (title == null) ? "Kairo" : title;
	}

	private void applyWindowModeState(boolean windowed) {
		controller.setWindowMode(windowed);
		settings.load();
		if (windowed) {
			catbox.setTitle(resolveWindowTitle());
			catbox.setOpacity(0.96f);
			catbox.setBackground(new Color(245, 246, 249, 255));
			invisibleWindow.setVisible(false);
			catbox.setVisible(true);
		}
		else {
			catbox.setOpacity(1.0f);
			catbox.setVisible(false);
			invisibleWindow.setVisible(true);
		}
		controller.moveCatInBox();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Kairo();
			}
		});
	}

	public void setWindowMode(boolean windowed)
	{
		applyWindowModeState(windowed);
	}

}

