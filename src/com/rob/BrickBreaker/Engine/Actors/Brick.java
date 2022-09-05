package com.rob.BrickBreaker.Engine.Actors;

import java.awt.*;

public class Brick {
    int width;
    int height;
    int posX;
    int posY;
    int health;
    Color color;

    public Brick(int posX, int posY, int health) {
        this.width = 50;
        this.height = 25;
        this.posX = posX * width;
        this.posY = posY * height;
        this.health = health;
        this.color = updateColor(this.health);
    }

    public Color updateColor(int health) {
        switch (health) {
            case 0 -> color = Color.BLACK;
            case 1 -> color = Color.RED;
            case 2 -> color = Color.BLUE;
            case 3 -> color = Color.GREEN;
            case 4 -> color = Color.YELLOW;
            case 5 -> color = Color.PINK;
        }
        return color;
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

    public int getPosY() {
        return posY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
