package com.wang.tankgame08;

import java.util.Vector;

/**
 * @Author:wsz
 * @Date: 2023/2/12 15:30
 * @Description:自己的tank
 * @Version: 1.0
 * @Since: 1.0
 */
public class Hero extends Tank {

    Shot shot=null;

    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank(){
        //如果子弹个数到了5个就不能发射了
        if (shots.size()==5)return;

        //创建 Shot 对象, 根据当前Hero对象的位置和方向来创建Shot
        switch (getDirect()) {//得到Hero对象方向
            case 0: //向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: //向右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: //向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: //向左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();

    }
}