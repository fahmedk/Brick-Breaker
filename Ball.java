import java.awt.Graphics;
import java.awt.Color;

public class Ball {
    public static final int SIZE = 20;
    private int x, y;
    private int xDirection = 1;
    private int yDirection = 1;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xDirection;
        y += yDirection;
        // Ball collision with the window edges
        if (x <= 0 || x >= GameWindow.WIDTH - SIZE) {
            reverseXDir();
        }
        if (y <= 0 || y >= GameWindow.HEIGHT - SIZE) {
            reverseYDir();
        }
    }

    public void reverseXDir() {
        xDirection *= -1;
    }

    public void reverseYDir() {
        yDirection *= -1;
    }

    // Method to get the Y direction of the ball
    public int getYDirection() {
        return yDirection;
    }

    // Method to set the Y position of the ball
    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED); // Change the ball color to red
        g.fillOval(x, y, SIZE, SIZE);
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
