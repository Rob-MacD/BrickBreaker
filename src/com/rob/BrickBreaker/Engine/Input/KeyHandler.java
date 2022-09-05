package com.rob.BrickBreaker.Engine.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean newBall = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_N){
            newBall = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key= e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_N){
            newBall = false;
        }
    }
}
