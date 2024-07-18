package com.ohgiraffers.sdudy.drectiorShome;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //입력셋팅
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //입력받을 숫자
        int num = 666; //증감할 숫자
        int count = 1; //카운트할 숫자//언젠가는 N번째가 될 카운트

    /*3. 각 숫자가 '666'을 포함하는지 확인하기
         666이 포함되는지를 검사할 때는 문자열로 검사하는게 좋다.

    * 문자열 검사 방법은 contains() 메소드를 사용하는 방법이 있다. //chatAt() 도 있음.
      contain 메소드는 해당 문자열 안에 검사하려는 문자열이 포함이 되어있는지를 검사하고,
      검사하려는 문자열이 담겨있으면 true, 없으면 false 를 반환한다.*/

        while (count != N) {
            num++;
            if (String.valueOf(num).contains("666")) {
                count++;
            }
        }
        System.out.println(num);
    }

}
