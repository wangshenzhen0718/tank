package com.wang.tankgame08;
/**
 * @Author:wsz
 * @Date: 2023/2/15 21:28
 * @Description:坦克对象
 * @Version: 4.0
 * @Since: 1.0
 */
public class Node {
    //坦克的横坐标
    private int x;
    //坦克的纵坐标
    private int y;
    private int direct;

    public Node(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
    //上右下左移动方法



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