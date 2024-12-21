import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class StarPatternGrid extends JFrame {
    private static final int GRID_SIZE = 3;
    private static final int CELL_SIZE = 100;
    private static final int MARGIN = 50;
    
    public StarPatternGrid() {
        setTitle("Star Pattern Grid");
        setSize(GRID_SIZE * CELL_SIZE + 2 * MARGIN, GRID_SIZE * CELL_SIZE + 2 * MARGIN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new StarPatternPanel());
    }
    
    class StarPatternPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Enable anti-aliasing for smoother lines
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Set line thickness
            g2d.setStroke(new BasicStroke(2));
            
            // Draw the outer grid
            g2d.setColor(Color.BLACK);
            for (int i = 0; i <= GRID_SIZE; i++) {
                // Vertical lines
                g2d.drawLine(i * CELL_SIZE + MARGIN, MARGIN, 
                            i * CELL_SIZE + MARGIN, GRID_SIZE * CELL_SIZE + MARGIN);
                // Horizontal lines
                g2d.drawLine(MARGIN, i * CELL_SIZE + MARGIN,
                            GRID_SIZE * CELL_SIZE + MARGIN, i * CELL_SIZE + MARGIN);
            }
            
            // Draw the star patterns in each cell
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    drawStarPattern(g2d, col * CELL_SIZE + MARGIN, row * CELL_SIZE + MARGIN);
                }
            }
        }
        
        private void drawStarPattern(Graphics2D g2d, int x, int y) {
            // Calculate points for the star pattern
            int quarterSize = CELL_SIZE / 4;
            
            Path2D path = new Path2D.Double();
            
            // Draw the outer star points
            // Top
            path.moveTo(x + CELL_SIZE/2, y + quarterSize);
            path.lineTo(x + CELL_SIZE - quarterSize, y + CELL_SIZE/2);
            path.lineTo(x + CELL_SIZE/2, y + CELL_SIZE - quarterSize);
            path.lineTo(x + quarterSize, y + CELL_SIZE/2);
            path.closePath();
            
            // Draw the connecting lines
            // Top
            g2d.drawLine(x + CELL_SIZE/2, y, x + CELL_SIZE/2, y + quarterSize);
            // Right
            g2d.drawLine(x + CELL_SIZE, y + CELL_SIZE/2, x + CELL_SIZE - quarterSize, y + CELL_SIZE/2);
            // Bottom
            g2d.drawLine(x + CELL_SIZE/2, y + CELL_SIZE, x + CELL_SIZE/2, y + CELL_SIZE - quarterSize);
            // Left
            g2d.drawLine(x, y + CELL_SIZE/2, x + quarterSize, y + CELL_SIZE/2);
            
            // Draw the star path
            g2d.draw(path);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StarPatternGrid frame = new StarPatternGrid();
            frame.setVisible(true);
        });
    }
}

