package com.wang.draw;

import javax.swing.*;
import java.awt.*;
/**
 * @Author:wsz
 * @Date: 2023/2/12 12:33
 * @Description:绘图入门和机制
 * @Version: 1.0
 * @Since: 1.0
 */
public class DrawCircle extends JFrame {
    MyPanel myPanel=null;
    //JFrame 对应窗口 相当于画框
    public static void main(String[] args){
        new DrawCircle();


    }
    public DrawCircle(){
        myPanel = new MyPanel();
        this.add(myPanel);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}

class MyPanel extends JPanel {
    //说明:
    //1.MyPanel对象就是一个画板
    //2.Graphics g把g理解成一支画笔
    //3.Graphics提供了很多绘图的方法
    //Graphics g


    @Override
    public void paint(Graphics g) {
        /*当组件第一次在屏幕显示的时候,程序会自动的调用paint()方法来绘制组件。
        在以下情况paint()将会被调用:
        1.窗口最小化再最大化
        2.窗口的大小发生变化
        3.repaint数被调用*/

        super.paint(g);
        System.out.println("paint方法被调用了！");
        g.setColor(Color.BLACK);
        g.drawOval(10,10,100,100);
    }
}
