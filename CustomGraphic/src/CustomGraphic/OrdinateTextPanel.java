package CustomGraphic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OrdinateTextPanel extends JLabel{
	
	public OrdinateTextPanel() {
		setOpaque(true);
	}
	public void paint(Graphics g) {
		Font yazi = new Font("Arial", Font.BOLD ,12);
		super.paintComponent(g);
		g.setFont(yazi);
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.drawRect(0, 0, 79, Graph.size[1] + 7);
		g2.drawRect(0, Graph.size[1] + 7, 80, 42);
		for (double i = Graph.x * Graph.prt, j = 0; i >= 0.0; i -= Graph.prt, j++)
        {
			g.drawString(Graph.min + i+"", 30, (int)(((Graph.size[1] - 10) / (Graph.x)) * j));
			g2.drawLine(75, (int)((Graph.size[1] / (Graph.x)) * j), 80, (int)((Graph.size[1] / (Graph.x)) * j));
        }
		
		g2.drawLine(80, Graph.size[1] + 7, 0, Graph.size[1] + 49);
		g.drawString(Graph.OrdinateText, 5, Graph.size[1] + 25);
		g.drawString(Graph.AbscissaText, 50, Graph.size[1] + 45);
	}
}
