package com.wang.draw01;

import javax.swing.*;
import java.awt.*;

/**
 * @Author:wsz
 * @Date: 2023/2/12 12:48
 * @Description:绘图案例
 * @Version: 1.0
 * @Since: 1.0
 */
public class DrawShape02 extends JFrame {
    Panel panel =null;
    public static void main(String[] args){
        new DrawShape02();


    }
    public DrawShape02(){
        panel = new Panel();
        this.add(panel);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


class Panel extends JPanel {
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
        g.setColor(Color.PINK);
        //演示绘制不同的图形..
        //画直线 drawLine(int x1,int y1,int x2,int y2)
        //g.drawLine(10, 10, 100, 100);
        //画矩形边框 drawRect(int x, int y, int width, int height)
        //g.drawRect(10, 10, 100, 100);
        //画椭圆边框
        // drawOval(int x, int y, int width, int height)
        //填充矩形
        //填充椭圆 fillOval(int x, int y, int width, int height)
        // g.setColor(Color.red);
        // g.fillOval(10, 10, 100, 100);
        //g.fillRect(10, 10, 100, 100);
        //画图片 drawImage(Image img, int x, int y, ..)
        //1. 获取图片资源, /bg.png 表示在该项目的根目录去获取 bg.png 图片资
        //Image image = Toolkit.getDefaultToolkit().getImage("/hrm.jpg");
        //g.drawImage(image,0,0,500,500,this);
        g.setFont(new Font("隶书",Font.BOLD,50));
        //这里x,y是左下角
        g.drawString("王深圳",100,100);

    }
}
