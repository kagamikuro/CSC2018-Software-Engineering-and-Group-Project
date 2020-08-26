package view;

import java.awt.Image;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Massage extends JPanel {

	protected Image bg = new ImageIcon("images/massage/massage.png").getImage();

	protected Point origin = new Point(); // Global position variable, used to indicate the position of the mouse on the window

	protected int x, y, w, h;

	protected String titileStr = "title";
	protected JLabel titile  = null;

	protected JPanelGame panel = null;


	
	/**
	 * 
	 * create a message conversation
	 * 
	 */
	protected Massage(String titile,JPanelGame panel) {
		this.titileStr = titile;
		// initial position
		initBounds();
		setLayout(null);
		// add title
		addTitle();
		// add text field
		// add listener
		addListener();
		// set background transparent
		setOpaque(false);
		this.panel = panel;
	}

	public void setTitileStr(String titileStr) {
		this.titileStr = titileStr;
		this.titile.setText("<html><font color='white' >"+titileStr+"</font></html>");
	}

	/**
	 * 
	 * hide window
	 * 
	 */
	public void moveToBack() {
		this.panel.getLayeredPane().moveToBack(this);
	}

	/**
	 * 
	 * display window
	 * 
	 */
	public void moveToFront() {
		this.panel.getLayeredPane().moveToFront(this);
	}
	
	private void initBounds() {
		this.x = (950 - bg.getWidth(null)) /2 ;
		this.y = (650 - bg.getHeight(null)) / 2;
		this.w = bg.getWidth(null);
		this.h =bg.getHeight(null);
		setBounds(x, y, w, h);
	}

	private void addTitle() {
		titile = new JLabel("<html><font color='white' >"+titileStr+"</font></html>");
		titile.setBounds(18, 4, 230, 20);
		add(titile);
	}


	private void addListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // press
				origin.x = e.getX(); // used to indicate the position of the mouse on the window
				origin.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // drag
				x += e.getX() - origin.x;
				y += e.getY() - origin.y;
				if (x < 0) {
					x = 0;
				}
				if (x + w > 950) {
					x = 950 - w;
				}
				if (y < 0) {
					y = 0;
				}
				if (y + h > 650) {
					y = 650 - h;
				}
				setBounds(x, y, w, h);
			}
		});
	}
	/**
	 * 
	 * press ok button
	 * 
	 */
	public void ok() {
		System.out.println("ok");
	}
	
	/**
	 * 
	 * press cancel button
	 * 
	 */
	public void cancel() {
		System.out.println("cancel");
	}
}
