package com.rob.BrickBreaker.Engine.Window;

import com.rob.BrickBreaker.Engine.Actors.Ball;
import com.rob.BrickBreaker.Engine.Actors.Brick;
import com.rob.BrickBreaker.Engine.Actors.Paddle;
import com.rob.BrickBreaker.Engine.Input.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Panel extends JPanel implements Runnable {

    int FPS = 60;
    int panelWidth = 1000;
    int panelHeight = 760;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    Paddle player;
    ArrayList<Ball> balls;
    ArrayList<Brick> bricks;
    int playerLives = 5;
    JLabel lives = new JLabel("Lives: " + playerLives);

    public Panel() {
        System.out.println("Building Panel");
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(new Color(0, 0, 0));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
        lives.setVisible(true);
        lives.setForeground(Color.WHITE);
        lives.setBounds(0,0,100,50);
        this.add(lives);
        player = new Paddle();
        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        balls.add(new Ball());
        bricks.add(new Brick(1,1,1));
        bricks.add(new Brick(2,1,2));
        bricks.add(new Brick(3,1,3));
        bricks.add(new Brick(4,1,4));
        bricks.add(new Brick(5,1,5));
        bricks.add(new Brick(6,1,4));
        bricks.add(new Brick(7,1,3));
        bricks.add(new Brick(8,1,2));
        bricks.add(new Brick(9,1,1));
        bricks.add(new Brick(10,1,1));
        bricks.add(new Brick(11,1,2));
        bricks.add(new Brick(12,1,3));
        bricks.add(new Brick(13,1,4));
        bricks.add(new Brick(14,1,5));
        bricks.add(new Brick(15,1,4));
        bricks.add(new Brick(16,1,3));
        bricks.add(new Brick(17,1,2));
        bricks.add(new Brick(18,1,1));

        bricks.add(new Brick(1,3,1));
        bricks.add(new Brick(2,3,2));
        bricks.add(new Brick(3,3,3));
        bricks.add(new Brick(4,3,4));
        bricks.add(new Brick(5,3,5));
        bricks.add(new Brick(6,3,4));
        bricks.add(new Brick(7,3,3));
        bricks.add(new Brick(8,3,2));
        bricks.add(new Brick(9,3,1));
        bricks.add(new Brick(10,3,1));
        bricks.add(new Brick(11,3,2));
        bricks.add(new Brick(12,3,3));
        bricks.add(new Brick(13,3,4));
        bricks.add(new Brick(14,3,5));
        bricks.add(new Brick(15,3,4));
        bricks.add(new Brick(16,3,3));
        bricks.add(new Brick(17,3,2));
        bricks.add(new Brick(18,3,1));

        bricks.add(new Brick(1,5,1));
        bricks.add(new Brick(2,5,2));
        bricks.add(new Brick(3,5,3));
        bricks.add(new Brick(4,5,4));
        bricks.add(new Brick(5,5,5));
        bricks.add(new Brick(6,5,4));
        bricks.add(new Brick(7,5,3));
        bricks.add(new Brick(8,5,2));
        bricks.add(new Brick(9,5,1));
        bricks.add(new Brick(10,5,1));
        bricks.add(new Brick(11,5,2));
        bricks.add(new Brick(12,5,3));
        bricks.add(new Brick(13,5,4));
        bricks.add(new Brick(14,5,5));
        bricks.add(new Brick(15,5,4));
        bricks.add(new Brick(16,5,3));
        bricks.add(new Brick(17,5,2));
        bricks.add(new Brick(18,5,1));
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double)1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null){
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime<0){
                    remainingTime = 0;
                }
                //noinspection BusyWait
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        // Generate Additional Balls:
        if (keyHandler.newBall){
            balls.add(new Ball());
            System.out.println(balls.size());
        }

        // Move Player Paddle Left:
        if (keyHandler.leftPressed && player.getPosX() > 0) {
            player.moveLeft();
        }

        // Move Player Paddle Right:
        if (keyHandler.rightPressed && player.getPosX() + 100 < panelWidth)
            player.moveRight();

        // Check for collision between Balls and Screen Edge:
        balls.forEach((ball) -> {
            if (ball.getPosX() + 10 >= panelWidth)
                ball.reverseVelX();
            if (ball.getPosY() + 10 >= panelHeight+45){
                playerLives--;
                // GAME OVER!
                if (playerLives == 0){
                    player.setColor(Color.BLACK);
                    gameThread = null;
                }
                ball.setPosY(-640);

            }
            if (ball.getPosX() <= 0)
                ball.reverseVelX();
            if (ball.getPosY() <= 0)
                ball.reverseVelY();

            // Check for collision between Balls and Player Paddles:
            if (ball.getPosY() + 10 >= player.getPosY() &&
                    ball.getPosY() <= player.getPosY() + 10 &&
                    ball.getPosX() + 10 >= player.getPosX() &&
                    ball.getPosX() <= player.getPosX() + 100) {
                ball.reverseVelY();
            }

            // Check for collision between Balls and Bricks
            Iterator<Brick> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                Brick brick = iterator.next();

                if (ball.getPosY() < brick.getPosY() + brick.getHeight() &&
                    ball.getPosY() + ball.getHeight() > brick.getPosY() &&
                    ball.getPosX() < brick.getPosX() + brick.getWidth() &&
                    ball.getPosX() + ball.getWidth() > brick.getPosX()) {
                    System.out.println("COLLISION!");
                    ball.setVelX(ball.getVelX()*1.005);
                    ball.setVelY(ball.getVelY()*1.005);
                    ball.reverseVelY();
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0){
                        iterator.remove();
                    }
                }
            }

            // Move Ball:
            ball.setPosX((int)ball.getVelX());
            ball.setPosY((int)ball.getVelY());
        });
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(player.getColor());
        balls.forEach((ball) ->
                g2d.drawArc(ball.getPosX(),ball.getPosY(),10,10,0,360));
        bricks.forEach((brick) -> {
            g2d.setColor(brick.updateColor(brick.getHealth()));
            g2d.fillRect(brick.getPosX(), brick.getPosY(), brick.getWidth(), brick.getHeight());
        });
        g2d.setColor(Color.GRAY);
        g2d.fillRect(player.getPosX(), player.getPosY(),100,10);
        g2d.dispose();
    }
}