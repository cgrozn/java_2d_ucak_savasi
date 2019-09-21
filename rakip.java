

import java.awt.Graphics;
import java.awt.Color;


public class Rakip {
	private int x;
	private int y;
	
	
	public Rakip(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public void degis() {
		y+=1;
	}
	
	public void onayla(Graphics g) {
		g.drawImage(resim_ekle.rakip, x, y, 50, 50, null);
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
