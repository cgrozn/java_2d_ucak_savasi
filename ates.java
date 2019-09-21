


import java.awt.Color;
import java.awt.Graphics;

public class Ates {
	
	private int x;
	private int y;
	private int hiz;
	
	public Ates(int x,int y) {
		this.x=x;
		this.y=y;
		hiz=10;
	}
	public void temp() {
		y-=hiz;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	public void onayla(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 6, 10);
		g.setColor(Color.GREEN);
		
	}

}
