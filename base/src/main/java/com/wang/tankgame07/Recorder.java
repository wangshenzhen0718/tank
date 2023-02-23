package com.wang.tankgame07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author:wsz
 * @Date: 2023/2/23 21:18
 * @Description:
 * @Version: 1.0
 * @Since: 1.0
 */
public class Recorder {

    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象, 准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "e:\\myRecord.txt";

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void addAllEnemyTankNum() {
        allEnemyTankNum++;
    }
    public static void keepRecord() {

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(recordFile))){
            bufferedWriter.write(allEnemyTankNum+"");
            bufferedWriter.newLine();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
