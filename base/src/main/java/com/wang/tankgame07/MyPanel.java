package com.wang.tankgame07;

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
public class MyPanel extends JPanel implements KeyListener, Runnable{
//定义我的坦克
    Hero hero = null;
    int enemySize=3;
    Vector<EnemyTank> enemyTanks = new Vector();
    //定义一个Vector ,用于存放炸弹
    //说明，当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    Vector<Node> nodes =null;

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public MyPanel(String key) {
        nodes  = Recorder.getNodesAndEnemyTankRec();
        //将MyPanel对象的 enemyTanks 设置给 Recorder 的 enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        //初始化自己坦克
        hero = new Hero(100, 100);
        hero.setSpeed(10);
        switch (key){
            case "1":
                for (int i = 0; i < enemySize; i++) {
                    EnemyTank enemyTank = new EnemyTank((i + 1) * 100, 0);
                    enemyTank.setDirect(2);
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setDirect(node.getDirect());
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage("E:\\视频资料\\韩顺平\\java基础\\韩顺平 2021零基础学Java 【软件 资料 代码 笔记】\\资料\\分享资料\\bomb_1.gif");
        image2 = Toolkit.getDefaultToolkit().getImage("E:\\视频资料\\韩顺平\\java基础\\韩顺平 2021零基础学Java 【软件 资料 代码 笔记】\\资料\\分享资料\\bomb_2.gif");
        image3 = Toolkit.getDefaultToolkit().getImage("E:\\视频资料\\韩顺平\\java基础\\韩顺平 2021零基础学Java 【软件 资料 代码 笔记】\\资料\\分享资料\\bomb_3.gif");
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        //画出坦克-封装方法
        if (hero!=null&&hero.isLive){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }
        //画出hero射击的子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot!= null && shot.isLive){
                System.out.println("子弹被绘制...");
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            }else {
                hero.shots.remove(shot);
            }

        }
        /*if(hero.shot != null && hero.shot.isLive == true) {

        }*/
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {//当敌人坦克是存活的，才画出该坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);

                for(int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive){
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    }else {
                        //从Vector 移除
                        enemyTank.shots.remove(shot);
                    }
                }

            }

        }

        //如果bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life 为0, 就从bombs 的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
    }

    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g) {

        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);//画出一个敌方坦克
        g.setColor(Color.BLACK);//这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum()+"", 1080, 100);

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

    public void hitEnemyTank() {

        /*//遍历我们的子弹
        for(int j = 0;j < hero.shots.size();j++) {
            Shot shot = hero.shots.get(j);
            //判断是否击中了敌人坦克
            if (shot != null && hero.shot.isLive) {//当我的子弹还存活

                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }

            }
        }*/

        //单颗子弹。
        if (hero.shot != null && hero.shot.isLive) {//当我的子弹还存活

            //遍历敌人所有的坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(hero.shot, enemyTank);
            }

        }

    }

    public void hitTank(Shot shot, Tank tank) {
        switch (tank.getDirect()){
            case 0:
            case 2:
                if (shot.x>tank.getX()&&shot.x<tank.getX()+40&&shot.y>tank.getY()&&shot.y<tank.getY()+60){
                    shot.isLive=false;
                    tank.isLive=false;
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                    //攻击中敌人后从容器中移除
                    enemyTanks.remove(tank);
                    if (tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
            case 1:
            case 3:
                if (shot.x>tank.getX()&&shot.x<tank.getX()+60&&shot.y>tank.getY()&&shot.y<tank.getY()+40){
                    shot.isLive=false;
                    tank.isLive=false;
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(tank);
                    if (tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下 W 键
        //改变坦克的方向
            hero.setDirect(0);//
        //修改坦克的坐标 y -= 1
            if (hero.getY()>0){
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D 键, 向右
            hero.setDirect(1);
            if (hero.getX()+60<1000){
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S 键
            hero.setDirect(2);
            if (hero.getY() + 60 < 750){
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A 键
            hero.setDirect(3);
            if (hero.getX()>0){
                hero.moveLeft();
            }
        }
        //如果用户按下的是J,就发射
        if(e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J, 开始射击.");
            /*//打一颗子弹的情况
            if (hero.shot==null||!hero.shot.isLive)
            hero.shotEnemyTank();*/
            hero.shotEnemyTank();
        }
//让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //编写方法，判断敌人坦克是否击中我的坦克
    public void hitHero() {
        //遍历所有的敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank 对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断 shot 是否击中我的坦克
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }
    @Override
    public void run(){
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }

    }
}