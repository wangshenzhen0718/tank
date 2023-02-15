package com.wang.tankgame06.tankgame05;

import java.util.Vector;

/**
 * @Author:wsz
 * @Date: 2023/2/15 20:46
 * @Description: 绘制敌人坦克的子弹
 * @Version: 1.0
 * @Since: 1.0
 */
public class EnemyTank extends Tank {
    //在敌人坦克类，使用Vector 保存多个Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
