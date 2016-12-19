import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JuliaSets extends JFrame
{
	protected int height = 800;
	protected int width = 1000;

	public static void main( String[] args ){
		JuliaSets s = new JuliaSets();
		s.init();
	}

	public JuliaSets() {
		setSize(width,height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Fractals");
	}

	public void init() {
		add(new Pane());
		setVisible(true);
	}

	class Pane extends Canvas implements MouseListener {
		protected Image offscreen;
		protected Graphics2D graphics;
		protected boolean called;
		protected boolean drawn;

		protected int radius;
		protected double detail;
		protected double prev_detail = -1;
		protected Complex c;
		protected Complex prev_c = new Complex(-100000,-100000);
		protected int depth = 20;

		public Pane() {
			radius = 2;
			detail = .001;
			c = new Complex(-.9,.22);
		}

		protected void init() {
			offscreen = createImage(width,height);
			graphics = (Graphics2D)offscreen.getGraphics();
		}

		protected void after() {
			called = true;
			addMouseListener(this);
		}

		public void paint( Graphics g ) {
			if(!called) init();

			if( !drawn || !c.equals(prev_c) || Math.abs(detail-prev_detail) >= .0001)
			{
				prev_detail = detail;
				prev_c = c;
				drawn=true;
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0,0,width,height);
				for( float i = -radius; i <= radius; i+=detail )
				{
					for( float j = -radius; j <= radius; j+= detail )
					{
						Complex temp = new Complex(i,j);
						Complex start = new Complex(temp.getRe(),temp.getIm());
						for( int it = 0; it < depth; it++ )
						{
							temp = (temp.mul(temp)).add(c);
						}
						double dist = start.dist(temp);
						int c = (int)(255*(-dist/(dist+1) + 1));
						//int c = (int)(255*(dist/(dist+1)));
						//int c = (int)(255*.5*(-dist/(dist-1) + 1 ));
						graphics.setColor(new Color(Math.abs(c-0),Math.abs(c-0),Math.abs(c)));
						graphics.fillRect((int)((width/2)+(200*start.getRe())),(int)((height/2)-(200*start.getIm())),1,1);
					}
				}
			}
			g.drawImage(offscreen,0,0,this);
			if(!called)
				after();
		}

		public void repaint() {
			paint(getGraphics());
		}

		public void update(){
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
			getGraphics().drawImage(offscreen,0,0,this);
		}

		public void mousePressed(MouseEvent e) {
			getGraphics().drawImage(offscreen,0,0,this);
		}

		public void mouseReleased(MouseEvent e) {
			getGraphics().drawImage(offscreen,0,0,this);
		}

		public void mouseEntered(MouseEvent e) {
			getGraphics().drawImage(offscreen,0,0,this);
		}

		public void mouseExited(MouseEvent e) {
			getGraphics().drawImage(offscreen,0,0,this);
		}
	}
}
