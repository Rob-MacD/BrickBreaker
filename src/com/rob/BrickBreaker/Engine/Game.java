package com.rob.BrickBreaker.Engine;

import com.rob.BrickBreaker.Engine.Window.Frame;
import com.rob.BrickBreaker.Engine.Window.Panel;

public class Game {
    public Game(){
        Frame frame = new Frame();
        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.startGameThread();
    }
}
