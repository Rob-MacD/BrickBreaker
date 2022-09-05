package com.rob.BrickBreaker.Engine.Actors;

import java.awt.*;

public class Paddle {
    int posX;
    int posY;
    int velocity;
    int width;
    int height;
    Color color;

    public Paddle() {
        this.posX = 650;
        this.posY = 650;
        this.velocity = 5;
        this.width = 100;
        this.height = 10;
        this.color = Color.WHITE;
    }

    public int getPosX() {
        return posX;
    }

    public void moveLeft() {
        this.posX -= this.velocity;
    }

    public void moveRight() {
        this.posX += this.velocity;
    }

    public int getPosY() {
        return posY;
    }

    public Color getColor() { return color; }

    public void setColor(Color color) {
        this.color = color;
    }
}
