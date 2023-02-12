package com.wang.tankgame02;

import javax.swing.*;
import java.awt.*;

/**
* @author 韩顺平
* @version 1.0
* 坦克大战的绘图区域
*/
public class MyPanel extends JPanel {
//定义我的坦克
    Hero hero = null;
    public MyPanel() {
        //初始化自己坦克
        hero = new Hero(100, 100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);
        //画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
    }
    //编写方法，画出坦克
    /**
    * @param x 坦克的左上角 x 坐标
    * @param y 坦克的左上角 y 坐标
    * @param g 画笔
    * @param direct 坦克方向（上下左右）
    * @param type 坦克类型
    */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型坦克，设置不同颜色
        switch (type) {
            //我们的坦克
            case 0:
                g.setColor(Color.cyan);
            break;
            //敌人的坦克
            case 1:
                g.setColor(Color.yellow);
            break;
    }
    //根据坦克方向，来绘制坦克
    switch (direct) {
        //表示向上
        case 0:
            //画出坦克左边轮子
            g.fill3DRect(x, y, 10, 60, false);
            //画出坦克右边轮子
            g.fill3DRect(x + 30, y, 10, 60, false);
            //画出坦克盖子
            g.fill3DRect(x + 10, y + 10, 20, 40, false);
            //画出圆形盖子
            g.fillOval(x + 10, y + 20, 20, 20);
            //画出炮筒
            g.drawLine(x + 20, y + 30, x + 20, y);
            break;
        default:
            System.out.println("暂时没有处理");
            }
        }
}