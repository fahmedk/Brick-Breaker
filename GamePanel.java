import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread gameThread;
    private boolean running;

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;

    // Game loop control constants
    private static final int FPS = 500;
    private static final long MILLIS_PER_FRAME = 500 / FPS;

    public GamePanel() {
        ball = new Ball(100, 300); // Start position of the ball
        paddle = new Paddle(300, 550); // Start position of the paddle
        bricks = new Brick[20]; // Example; adjust as needed

        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new Brick(i * (Brick.WIDTH + 5) + 30, 50, Brick.WIDTH, Brick.HEIGHT);
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        startGame();
    }

    private void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000 / FPS;
        double delta = 0;
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                updateGame();
                repaint();
                delta--;
            }
        }
    }

    private void updateGame() {
        ball.move();
        checkBallCollisions();
    }

    private void checkBallCollisions() {
        // Ball collision with the left and right walls
        if (ball.getX() < 0 || ball.getX() > getWidth() - Ball.SIZE) {
            ball.reverseXDir();
        }
    
        // Ball collision with the top wall
        if (ball.getY() < 0) {
            ball.reverseYDir();
        }
    
        // Ball collision with the paddle
        if (new Rectangle(ball.getX(), ball.getY(), Ball.SIZE, Ball.SIZE)
            .intersects(new Rectangle(paddle.getX(), paddle.getY(), Paddle.WIDTH, Paddle.HEIGHT))) {
            ball.reverseYDir();
        }
    
        // Ball collision with bricks
        for (Brick brick : bricks) {
            if (brick.isVisible() && new Rectangle(ball.getX(), ball.getY(), Ball.SIZE, Ball.SIZE)
                    .intersects(new Rectangle(brick.getX(), brick.getY(), Brick.WIDTH, Brick.HEIGHT))) {
                brick.setVisible(false); // Break the brick
                ball.reverseYDir(); // Reverse the ball's y-direction
    
                // To avoid the ball getting "stuck" or disappearing
                // Adjust the ball's position slightly outside the collision area
                adjustBallPositionPostCollision(brick);
    
                break; // Only interact with one brick per frame
            }
        }
    }
    
    private void adjustBallPositionPostCollision(Brick brick) {
        // Adjust the ball's position to be just outside the brick
        if (ball.getYDirection() > 0) {
            // Ball is moving down; place it above the brick
            ball.setY(brick.getY() - Ball.SIZE);
        } else {
            // Ball is moving up; place it below the brick
            ball.setY(brick.getY() + Brick.HEIGHT);
        }
    }
    
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Optionally implement if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}