package com.wang.event03;

import javax.swing.*;

/**
 * @Author:wsz
 * @Date: 2023/2/12 22:05
 * @Description:小球移动案例
 * @Version: 1.0
 * @Since: 1.0
 */
public class BallMove extends JFrame { //窗口
MyPanel mp = null;
public static void main(String[] args) {
    BallMove ballMove = new BallMove();
}
    //构造器
    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        //窗口 JFrame 对象可以监听键盘事件, 即可以监听到面板发生的键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}