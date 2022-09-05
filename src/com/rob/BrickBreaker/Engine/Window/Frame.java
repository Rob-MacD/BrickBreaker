package com.rob.BrickBreaker.Engine.Window;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(){
        System.out.println("Building Frame");
        this.setResizable(false);
        this.setTitle("BrickBreaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
