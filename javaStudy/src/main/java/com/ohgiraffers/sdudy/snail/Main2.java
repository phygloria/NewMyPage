package com.ohgiraffers.sdudy.snail;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("total 막대기 총길이 : ");
        long v = sc.nextLong();
        System.out.println("day+ 낮에 올라가는 길이 : ");
        long a = sc.nextLong();
        System.out.println("night- 밤에 미끄러지는 길이 : ");
        long b = sc.nextLong();

        long h = 0; //하루동안 순이동거리
        long result = 0; // 반복되는 하루

//        long v = 6; //막대기 총길이
//        long a = 5; //day+ 낮에 올라가는 길이
//        long b = 1; //night- 밤에 미끄러지는 길이
//        long h = 0;
//        long result = 0;

        while(h < v){
            result++;

            h += a;

            if(h >= v){
                break;
            }

            h -= b;

        }

        long totalDay = result;

        System.out.println("Total days: " + totalDay);

    }
}
