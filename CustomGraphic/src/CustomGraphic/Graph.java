package CustomGraphic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph extends JLabel{
	
    public OrdinateTextPanel pnlOrdinateText = new OrdinateTextPanel();
    public AbscissaTextPanel pnlAbscissaText = new AbscissaTextPanel();
    public static ArrayList<Double> Ordinate ;
    static ArrayList<Double>[] Values;
    static int[] size, location;
	Color[] color;
	
	Boolean MaxToMin = false;
	int DrawSize;
	static String AbscissaText, OrdinateText;
    static double max = 0.0, min = 0.0, x = 0.0, y = 0.0, prt = 0.0;
    public Graph(){
    	
    	setOpaque(true);
    }
    public void Draw() {
    /** Maximum and Minimum Values **/
    	
    	Boolean Check = false;
    	
    	for (ArrayList<Double> a : Values)
        {
            for (double b : a)
            {
                if (Check == false)
                {
                    min = b;
                    max = b;
                    Check = true;
                }
                if (b < min) min = b;
                if (b > max) max = b;
            }
        }
    /** Axis Partitioning **/

        if (MaxToMin == true)
        {
            x = (int)((max - min) / prt);
            if (Math.abs((max - min) % prt) != 0)
                x += 1;
        }
        else
        {
            x = (int)(max / prt);
            if (Math.abs(max % prt) != 0)
                x += 1; max = x * prt;
            if (min < 0)
            {
                y = (int)(min / prt);
                if (Math.abs(min % prt) != 0)
                    y -= 1;
                if (Math.abs(x) < Math.abs(y))
                    x = Math.abs(y);
            }
        }
    /** Values Transformation **/
        
        if (MaxToMin == true)
        {
            for (int i = 0; i < Values.length; i++) {
                for (int j = 0; j < Values[i].size(); j++) {
                	double value = size[1] * (max - Values[i].get(j)) / (max - min);
                    Values[i].set(j, value);
                    }
                }
            
        }
        else
        {
            for (int i = 0; i < Values.length; i++)
                for (int j = 0; j < Values[i].size(); j++)
                    Values[i].add(j, size[1] / 2.0 * ((x * prt) - Values[i].get(j)) / (x * prt));
        }
       
        
    	pnlOrdinateText.setBounds(location[0], location[1], 80, size[1] + 50);
    	pnlOrdinateText.setBackground(Color.white);
    	
    	pnlAbscissaText.setBounds(location[0] + 80, location[1] + size[1], size[0], 50);
    	pnlAbscissaText.setBackground(Color.white);
    	
    	this.setBounds(location[0] + 80, location[1], size[0], size[1]);
    }
    
    public void setValues(ArrayList<Double>... V1)
    {
        Values = new ArrayList[V1.length];
        for (int i = 0; i < V1.length; i++)
        {
            Values[i] = new ArrayList<Double>();
            for (int j = 0; j < V1[i].size(); j++)
                Values[i].add(V1[i].get(j));
        }
    }
    public void setBound(int X, int Y, int x, int y)
    {
        size = new int[] { x, y };
        location = new int[] { X, Y };
    }
    public void setPartition(double P) { prt = P; }
    public void setMaxtoMin(Boolean e) { MaxToMin = e; }
    public void setColor(Color... e) { color = e; }
    public void setDrawSize(int e) { DrawSize = e; }
    public void setAbscissaText(String e) { AbscissaText = e; }
    public void setOrdinateText(String e) { OrdinateText = e; }
    public void paint(Graphics g) {
		Font yazi = new Font("Helvetica", 8, Font.ITALIC);
		super.paintComponent(g);
		g.setFont(yazi);
		g.setColor(Color.lightGray);
		Graphics2D g2 = (Graphics2D) g;
		
        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 19; j++)
            {
                g.drawRect((size[0] / 20) * (j), (size[1] / 20) * (i), (size[0] / 20) * (j + 1), (size[1] / 20) * (i + 1));
            }
        }
        g.drawRect(0, 0, size[0]-1, size[1]-1);
        g2.setStroke(new BasicStroke(2));
        int k = 0;
        for (int i = 0; i < Values.length; i++)
        {
            g.setColor(color[k]);;//input
            if (color.length > k) k++;
            for (double j = 0.0; j < Values[i].size() - 1; j+=1.0) {
            	double y = Values[i].get((int)j);
            	double y1 = Values[i].get((int)j + 1);
                g2.drawLine((int)(size[0] / (double)Values[i].size() * j), (int)y, (int)(int)(size[0] / (double)Values[i].size() * (j + 1)), (int)y1);
        }
            }
	}
}
