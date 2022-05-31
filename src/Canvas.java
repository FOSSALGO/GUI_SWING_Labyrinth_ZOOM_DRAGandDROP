
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    //variables
    private int[][] labyrinth = {
        {-1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1},
        {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0},
        {0, 0, -1, -1, -1, -1, 0, 0, 0, -1, 0},
        {0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0},
        {0, -1, -1, 0, 0, -1, 0, 0, 0, 0, 0},
        {0, 0, -1, 0, 0, -1, -1, -1, -1, -1, -1}
    };
    
    private int cellSize = 40;
    private double translateX;
    private double translateY;
    private double scale;

    Canvas() {
        translateX = 0;
        translateY = 0;
        scale = 1;
        setOpaque(false);
        setDoubleBuffered(true);
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int[][] getLabyrinth() {
        return labyrinth;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void resetGrid(int rows, int cols) {
        labyrinth = new int[rows][cols];
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        //---------------------------------------------------------------------------------------------------      
        AffineTransform tx = new AffineTransform();
        tx.translate(translateX, translateY);
        tx.scale(scale, scale);
        //---------------------------------------------------------------------------------------------------   
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        //---------------------------------------------------------------------------------------------------   
        g2d.setTransform(tx);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.9F));
        //---------------------------------------------------------------------------------------------------
        if (labyrinth != null) {
            int rows = labyrinth.length;
            int cols = labyrinth[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int value = labyrinth[i][j];
                    if (value == -1) {
                        g2d.setColor(Color.decode("#34495e"));
                    } else if (value == 0) {
                        g2d.setColor(Color.decode("#ecf0f1"));
                    }
                    g2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g2d.setColor(Color.decode("#95a5a6"));
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
        //---------------------------------------------------------------------------------------------------
        g2d.dispose();
    }//end of paint
}
