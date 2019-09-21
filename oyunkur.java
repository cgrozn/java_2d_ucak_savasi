

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;



public class oyunKur implements KeyListener {
	private Oyuncu oyuncu;
	public static ArrayList<Ates>ates;
	private ArrayList<Rakip>rakipler;
	private long der;
	private long gecikme;
	private int saglik;
	private int skor;
	private boolean basla;
	
	public oyunKur() {
		
	}
	public void init() {
		Ekran.frame.addKeyListener(this);
		oyuncu=new Oyuncu((oyunaGir.oyun_Genislik/2)+50,(oyunaGir.oyun_Yukseklik-60)+50);
		oyuncu.init();
		ates=new ArrayList<Ates>();
		rakipler=new ArrayList<Rakip>();
		der=System.nanoTime();
		gecikme=2000;
		saglik=oyuncu.getSaglik();
		skor=0;
	}
	public void temp() {
		if(basla) {
			oyuncu.temp();;
			for(int i=0;i<ates.size();i++) {
				ates.get(i).temp();
			}
			long son=(System.nanoTime()-der)/1000000;
			if(son>gecikme) {
				for(int i=0;i<2;i++) {
					Random rand=new Random();
					int randX=rand.nextInt(450);
					int randY=rand.nextInt(450);
					if(saglik>=0) {
						rakipler.add(new Rakip(randX,-randY));
						
					}
				}
				der=System.nanoTime();
			}
			for(int i=0;i<rakipler.size();i++) {
				rakipler.get(i).degis();
			}
		}
	}
	public void onayla(Graphics g) {
		if(basla) {
			oyuncu.onayla(g);
			for(int i=0;i<ates.size();i++) {
				ates.get(i).onayla(g);
			}
			for(int i=0;i<ates.size();i++) {
				if(ates.get(i).getY()<=50) {
					ates.remove(i);
					i--;
				}
			}
			for(int i=0;i<rakipler.size();i++) {
				if(!(rakipler.get(i).getX()<=50||rakipler.get(i).getX()>=450-50||rakipler.get(i).getY()>=450-50)) {
					if((rakipler.get(i).getY()>=50)) {
						rakipler.get(i).onayla(g);
					}
				}
			}
			for(int i=0;i<rakipler.size();i++) {
				int ex=rakipler.get(i).getX();
				int ey=rakipler.get(i).getY();
				int px=oyuncu.getX();
				int py=oyuncu.getY();
				
				if(px<ex+50&&px+60>ex&&py<ey+50&&py+60>ey) {
					rakipler.remove(i);
					i--;
					saglik--;
					System.out.println(saglik);
					if(saglik<=0) {
						rakipler.removeAll(rakipler);
						oyuncu.setSaglik(0);
						basla=false;
					}
				}
				
				for(int j=0;j<ates.size();j++) {
					int bx=ates.get(j).getX();
					int by=ates.get(j).getY();
					if(ex<bx+6&&ex+50>bx&&ey<by+6&&ey+50>by) {
						rakipler.remove(i);
						i--;
						ates.remove(j);
						j--;
						skor=skor+5;
					}
				}
				g.setColor(Color.GREEN);
				g.setFont(new Font("arial",Font.BOLD,40));
				g.drawString("Skor:"+skor,70, 500);
			}
		}
		else {
			g.setColor(Color.GREEN);
			g.setFont(new Font("arial",Font.PLAIN,33));
			g.drawString("  Başla için Entry ", 100, (oyunaGir.oyun_Yukseklik/2)+50);
			
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int yer=e.getKeyCode();
		if(yer==KeyEvent.VK_ENTER) {
			basla=true;
			init();
		}
	}
	public void keyReleased(KeyEvent arg0) {
		
	}
	public void keyTyped(KeyEvent arg0) {
		
	}

}
