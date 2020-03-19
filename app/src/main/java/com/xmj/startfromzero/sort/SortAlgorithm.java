package com.xmj.startfromzero.sort;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Random;

//排序算法
public class SortAlgorithm {

    /**
     * 冒泡排序----数据比较少时效率高（n<10 最好3-5个数据）
     * 相邻两个元素比较大小，大的放后面
     * @param array
     */
    public void bubbleSort(@NonNull int[] array){
        int num = 0;
        for (int n = array.length - 1; n > 0; n--){
            boolean flag = true;
            for (int i = 0; i < n; i++){
                num++;
                if (array[i] > array[i+1]){
                    int temp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = temp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }

        System.out.println(num);
        for (int i: array) {
            System.out.print(i + " ");
        }
    }

    /**
     * 选择排序----与冒泡差不多
     * 依次与开始位置值进行比较，找出最小值位置，然后换值
     * @param array
     */
    public void selectSort(@NonNull int[] array){
        for (int i = 0; i < array.length; i++){
            int index = i;
            for (int n = i + 1; n < array.length; n++){
                if (array[index] > array[n]){
                    index = n;
                }
            }

            if (index != i){
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }

        for (int i: array) {
            System.out.print(i + " ");
        }

    }

    /**
     * 欢乐麻将排序
     * 1九个链表按数值放入相应麻将
     * 2合并到总表
     * 3三个链表按类型放入
     * 4合并到总表
     * @param mahjongList
     */
    public void mahjongSort(LinkedList<Mahjong> mahjongList){
        LinkedList[] list = new LinkedList[9];
        for (int i = 0; i < list.length; i++){
            list[i] = new LinkedList();
        }
        while (mahjongList.getSize() > 0){
            Mahjong maJ = mahjongList.remove();
            list[maJ.getNum() - 1].add(maJ);
        }

        for (int i = 0; i < list.length; i++) {
            mahjongList.addAll(list[i]);
        }

        System.out.println();

        LinkedList[]  tList= new LinkedList[3];
        for (int i = 0; i < tList.length; i++){
            tList[i] = new LinkedList();
        }

        while (mahjongList.getSize() > 0){
            Mahjong maJ = mahjongList.remove();
            tList[maJ.getType() - 1].add(maJ);
        }

        for (int i = 0; i < tList.length; i++) {
            mahjongList.addAll(tList[i]);
        }

        while (mahjongList.getSize() > 0){
            Mahjong maJ = mahjongList.remove();
            System.out.print(maJ.getType() + ":" + maJ.getNum() + "   ");
        }
    }

    public LinkedList<Mahjong> createMahjongList(){
        LinkedList<Mahjong> mjList = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 13; i++){
            Mahjong maJ = new Mahjong(random.nextInt(3) + 1, random.nextInt(9) + 1);
            System.out.print(maJ.getType() + ":" + maJ.getNum() + "   ");
            mjList.add(maJ);
        }
        return mjList;
    }
}
