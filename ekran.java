
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ekran {
  private String baslik;
  private int genislik;
  private int yukseklik;
  public static JFrame frame;
  public static Canvas canvas;
  
  public Ekran(String baslik,int genislik,int yukseklik) {
	  this.baslik=baslik;
	  this.yukseklik=yukseklik;
	  this.genislik=genislik;
	  Ekran();
  }

private void Ekran() {
	frame=new JFrame(baslik);
	frame.setSize(genislik,yukseklik);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	canvas=new Canvas();
	canvas.setPreferredSize(new Dimension(genislik,yukseklik));
	canvas.setBackground(new Color(100,100,100));
	canvas.setFocusable(false);
	frame.add(canvas);
	frame.pack();
	
}
public Canvas getCanvas() {
	return canvas;
}

}
