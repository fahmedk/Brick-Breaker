import java.awt.Graphics;
import java.awt.Color;

public class Paddle {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    private int x, y;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x = Math.max(0, x - 100);
    }

    public void moveRight() {
        x = Math.min(GameWindow.WIDTH - WIDTH, x + 100);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK); // Paddle color
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
