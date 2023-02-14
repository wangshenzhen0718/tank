package com.wang.tankgame05;

import javax.swing.*;
/**
 * @Author:wsz
 * @Date: 2023/2/12 22:50
 * @Description:绘制坦克上右下左
 * @Version: 1.0
 * @Since: 1.0
 */
public class TankGame extends JFrame {
    //定义 MyPanel
    MyPanel mp = null;
    public TankGame() {
        mp = new MyPanel();
        //把面板(就是游戏的绘图区域)
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
}