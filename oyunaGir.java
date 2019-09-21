

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class oyunaGir implements Runnable {
	private String baslik;
	private int genislik;
	private int yukseklik;
	private Thread islem;
	private boolean kos;
	private BufferStrategy bellek;
	private Graphics g;
	private int y;
	private boolean baslat;
	private oyunKur admin;
	
	private Ekran ekran;
	public static final int oyun_Genislik=400;
	public static final int oyun_Yukseklik=400;
	
	public oyunaGir(String baslik,int genislik,int yukseklik) {
		this.yukseklik=yukseklik;
		this.baslik=baslik;
		this.genislik=genislik;
	}
	public void init() {
		ekran=new Ekran(baslik,genislik,yukseklik);
		resim_ekle.init();
		admin=new oyunKur();
		admin.init();
		baslat=true;
	}
	public synchronized void start() {
		if(kos)
			return;
		kos=true;
		if(islem==null) {
			islem=new Thread(this);
			islem.start();
		}
		
	}
	public synchronized void stop() {
		if(!(kos))
			return;
		kos=false;
		try {
			islem.join();
			
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void temp() {
		admin.temp();
	}
	public void onayla() {
		bellek=ekran.getCanvas().getBufferStrategy();
		if(bellek==null) {
			ekran.getCanvas().createBufferStrategy(3);
			return;
		}
		g=bellek.getDrawGraphics();
		g.clearRect(0, 0, genislik, yukseklik);
		g.drawImage(resim_ekle.resim,50,50,oyun_Genislik,oyun_Yukseklik,null);
		admin.onayla(g);
		
		
		bellek.show();
		g.dispose();
	}
	public void run() {
		init();
		int var=50;
		double zaman=1000000000/var;
		double deger=0;
		long der=System.nanoTime();
		
		while(kos) {
			deger=deger+(System.nanoTime()-der)/zaman;
			der=System.nanoTime();
			if(deger>=1) {
				temp();
				onayla();
				deger--;
			}
		}
	}
	
	
}

