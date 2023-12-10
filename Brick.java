import java.awt.Graphics;
import java.awt.Color;

public class Brick {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 20;
    private int x, y;
    private boolean isVisible;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.isVisible = true;
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.GRAY); // Brick color
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    // Getters and setters
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}