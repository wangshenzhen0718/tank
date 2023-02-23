package com.wang.tankgame07;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        new Thread(mp).start();
        //把面板(就是游戏的绘图区域)
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到了");
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
}