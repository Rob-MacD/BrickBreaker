package com.rob.BrickBreaker.Engine.Actors;
public class Ball {
    int width;
    int height;
    int scale;
    int posX;
    int posY;
    double velX;
    double velY;

    public Ball() {
        this.width = 10;
        this.height = 10;
        this.scale = 1;
        this.posX = 100;
        this.posY = 200;
        this.velX = 3;
        this.velY = 3;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX += posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY += posY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void reverseVelX() {
        this.velX *= -1;
    }

    public void reverseVelY() { this.velY *= -1; }

    public void setVelX(double velX) { this.velX = velX; }
    public void setVelY(double velY) { this.velY = velY; }
}