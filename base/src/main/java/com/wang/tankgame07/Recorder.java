package com.wang.tankgame07;

import java.io.*;
import java.util.Vector;

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
    //定义Vector ,指向 MyPanel 对象的 敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    private static String recordFile = "e:\\myRecord.txt";
    private static Vector<Node> nodes=new Vector<>();
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

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
            //OOP, 定义一个属性 ，然后通过setXxx得到 敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { //建议判断.
                    //保存该enemyTank信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bufferedWriter.write(record + "\r\n");
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static Vector<Node>  getNodesAndEnemyTankRec(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(recordFile));){
            String line = null;
            allEnemyTankNum = Integer.parseInt(bufferedReader.readLine());
            while ((line=bufferedReader.readLine()) != null){
                String[] xyd = line.split(" ");

                Node node = new Node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return nodes;


    }


}
