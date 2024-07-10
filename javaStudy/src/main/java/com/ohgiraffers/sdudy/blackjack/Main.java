package com.ohgiraffers.sdudy.blackjack;

import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        /*입력해야되는 값*/
        System.out.println("숫자가 보이도록 바닥에 놓인 카드 수 N : ");
        int N = sc.nextInt(); //(3 <= N <= 100)//숫자가 보이도록 바닥에 놓인 카드 수 (N장)
        System.out.println("딜러가 외친 숫자 M : ");
        int M = sc.nextInt(); //(10 ≤ M ≤ 300,000)//딜러가 외친 숫자


        System.out.println("바닥에 놓인 카드들에 적힌 수 (N장만큼) : ");
        int[] cardNum = new int[N]; //바닥에 놓인 카드들에 적힌 수 (N장)
        int f = 0; // for문 안에서 반복할 거.

        if(3<= N && N <=100){ //N장의 조건일 때
            for (int i = 0; i < N; i++) { //배열은 0부터 시작하니까 N보다 -1작은 값까지만 반복하면 된다.
                cardNum[f] = sc.nextInt(); //N만큼 늘어놓은 카드들에 적힌 숫자를 입력해준다.
                f++; //반복할 때 f자리에 덮어쓰지 않도록 증감해준다.
            }
        }
        


        /*ArrayList은 여러 데이터를 순서대로 관리하고 싶을 때 사용한다.
        제너릭 클래스이기 때문에 <> 안에는 다양한 type이 들어갈 수 있다.*/
        List<Integer> result = new ArrayList<>();


        /*플레이어가 고른 카드 3장에 적힌 수*/
        /*int a;  int b;  int c;
        long sum; // a+b+c = 최종 구해야할 값*/

        if(10<=M && M<=300000){ //M의 조건일 때(백준이 내준 조건)
            // 모든 가능한 3개의 정수조합 생성
            for (int a = 0; a < N; a++) { //
                for (int b = a+1; b < N; b++) {
                    for (int c = b+1; c < N; c++) {
                        // 3개의 정수 조합으로 합하는 식까지가 1회 , 각각 N미만까지 증감, 모든 조합 반복
                        int sum = cardNum[a] + cardNum[b] + cardNum[c];

                        // 합이 M이하인 경우 결과 리스트에 추가
                        if(sum <= M){
                            result.add(sum);
                        }
                    }
                }
            }
            // 결과리스트를 내림차순으로 재정렬해줌
            result.sort(Collections.reverseOrder());
        };

        // 정렬된 결과리스트중에서 가장 위에 있는 0번째 값이 가장 M과 가까운 수이므로 0번째를 호출
        System.out.println("플레이어가 고른 카드 3장의 합에서 M에 가장 가까운 수 : ");
        System.out.println(result.get(0));
    }
}
