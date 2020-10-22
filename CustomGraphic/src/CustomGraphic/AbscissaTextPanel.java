package CustomGraphic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AbscissaTextPanel extends JLabel{
	
	public AbscissaTextPanel() {
		setOpaque(true);
	}
	public void paint(Graphics g) {
		Font yazi = new Font("Arial", Font.BOLD ,12);
		super.paintComponent(g);
		g.setFont(yazi);
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
		g2.drawRect(0, 0, Graph.size[0] - 2, 49);
		double a = Graph.Ordinate.size();
		for (double i = 0.0; i < a; i+=1.0) {
			g.drawString(Graph.Ordinate.get((int)i)+"", (int)(((Graph.size[0]) / (double)(Graph.Ordinate.size())) * (i+1.0)), 30);
			g2.drawLine((int)(Graph.size[0] / (double)(Graph.Ordinate.size()) * i), 0, (int)(Graph.size[0] / (double)(Graph.Ordinate.size()) * i), 5);
			
			
		}
	}
	
}
