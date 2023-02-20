package com.wang.tankgame06;
/**
 * @Author:wsz
 * @Date: 2023/2/15 21:28
 * @Description:坦克对象
 * @Version: 4.0
 * @Since: 1.0
 */
public class Tank {
    //坦克的横坐标
    private int x;
    //坦克的纵坐标
    private int y;
    private int direct;
    private int speed = 1;
    boolean isLive = true;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
    //上右下左移动方法
    public void moveUp() {
        y -= speed;
    }
    public void moveRight() {
        x += speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
    }



    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}