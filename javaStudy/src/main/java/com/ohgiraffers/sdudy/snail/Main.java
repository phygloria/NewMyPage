package com.ohgiraffers.sdudy.snail;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

//        long v = 6; //막대기 총길이
//        long a = 5; //day+ 낮에 올라가는 길이
//        long b = 1; //night- 밤에 미끄러지는 길이
//        long h = 0; //하루동안 순이동거리
//        long days = 0; // 반복되는 하루


        Scanner sc = new Scanner(System.in);
        System.out.println("total 막대기 총길이 : ");
        long v = sc.nextLong();
        System.out.println("day+ 낮에 올라가는 길이 : ");
        long a = sc.nextLong();
        System.out.println("night- 밤에 미끄러지는 길이 : ");
        long b = sc.nextLong();

        long h = a-b; //하루동안 순이동거리
        long days; // 반복되는 하루

        h = (a-b);


        // 만약 낮에 올라가는 길이가 막대기 총 길이보다 크거나 같으면 하루만에 도착
        if (a >= v) {
            days = 1;
        } else {
            // 나머지 거리를 하루 동안의 순 이동 거리로 나누어 올림 처리한 후, 첫 날을 더함
            days = (v - a + h - 1) / h + 1;
        }

        long totalDay = days;

        System.out.println("Total days: " + totalDay);

    }
}
