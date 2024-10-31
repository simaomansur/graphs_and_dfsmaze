import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother lines and text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));  // Set edge thickness
        for (Vertex vertex : graph.getVertices()) {
            for (Vertex neighbour : vertex.getNeighbours()) {
                g2.drawLine(vertex.x, vertex.y, neighbour.x, neighbour.y);
            }
        }

        // Draw vertices
        for (Vertex vertex : graph.getVertices()) {
            g2.setColor(Color.BLUE);
            Ellipse2D.Double circle = new Ellipse2D.Double(vertex.x - 10, vertex.y - 10, 20, 20);
            g2.fill(circle);

            // Draw vertex ID in white, centered
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics metrics = g2.getFontMetrics();
            int labelX = vertex.x - metrics.stringWidth(String.valueOf(vertex.id)) / 2;
            int labelY = vertex.y + metrics.getHeight() / 3;
            g2.drawString(String.valueOf(vertex.id), labelX, labelY);
        }
    }
}
