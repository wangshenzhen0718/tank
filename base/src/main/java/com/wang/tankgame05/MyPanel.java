package com.wang.tankgame05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
* @author 韩顺平
* @version 1.0
* 坦克大战的绘图区域
*/
public class MyPanel extends JPanel implements KeyListener {
//定义我的坦克
    Hero hero = null;
    int enemySize=3;
    Vector<EnemyTank> enemyTanks = new Vector();
    public MyPanel() {
        //初始化自己坦克
        hero = new Hero(100, 100);
        hero.setSpeed(10);
        for (int i = 0; i < enemySize; i++) {
            EnemyTank enemyTank = new EnemyTank((i + 1) * 100, 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);
        //画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        for (int i = 0; i < enemyTanks.size(); i++) {
            drawTank(enemyTanks.get(i).getX(), enemyTanks.get(i).getY(), g, enemyTanks.get(i).getDirect(), 1);
        }
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
                g.setColor(Color.yellow);
            break;
            //敌人的坦克
            case 1:
                g.setColor(Color.cyan);
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
        case 1: //表示向右
            g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
            g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
            g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
            g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
            g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
            break;
        case 2: //表示向下
            g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
            g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
            g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
            g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
            g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
            break;
        case 3: //表示向左
            g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
            g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
            g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
            g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
            g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
            break;
        default:
            System.out.println("暂时没有处理");
            }
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下 W 键
//改变坦克的方向
            hero.setDirect(0);//
//修改坦克的坐标 y -= 1
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D 键, 向右
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S 键
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A 键
            hero.setDirect(3);
            hero.moveLeft();
        }
        //如果用户按下的是J,就发射
        if(e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J, 开始射击.");
            hero.shotEnemyTank();
        }
//让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}