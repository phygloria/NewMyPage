package com.ohgiraffers.sdudy.sugerDilivery;

import java.time.Year;
import java.util.Scanner;

public class Main {
    public void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int five = 0; //초기화하고 사용하진 않음...음.
        int three = 0;
        int R; //result 상근이가 배달할 봉지의 최소개수
               // 없어도 그냥 출력하면 되었다.


        /*while(){
            //아 이건 필요없겠다.
        }*/

        if(N % 5 == 0){
            //0으로 나누어떨어지는가??
            //나누어 남은 수가 0 인지를 확인하는 것이므로 % 수식을 사용한다.
            System.out.println("상근이가 배달할 봉지의 최소개수 : " + (N / 5));

        }else if(N < 10 && N % 3 == 0){ // 추가조건식을 쓰는구나
            // 3으로 나누어떨어지는 수 중에서 9이상은 5랑 조합하는게 봉지개수를 줄일수있다
            // 3으로 나누어 떨어지는지 확인 % 수식을 사용한다.
            System.out.println("상근이가 배달할 봉지의 최소개수 : " + (N / 3));

        } else{ // 5나 3으로 나누어떨어지지 않는 숫자들용
            //***검증 : 아래식은 오류. 그래도 일단 써보기.

            /* (N - 5) / 5 = M; */
            // 5를 하나 줄인다.
            // N에서 5를 한번 뺀 수에서 5로 최대한 나눈다.
            double j = Math.floor((N - 5) / 5);
            // 소수점 내림 함수(FLOOR)
            // j 는 (5kg봉지로 들수있는 최대 개수)


            // 5로 최대한 봉지를 든 후 남은 N을 구한다.
            // 남은 N을 3으로 나누는 수식을 쓴다.
            // j 가 위에서 double로 선언되어있고 아래는 int라 자료형이 안맞는다..?
            // 소수점 내림함수는 double로만 되는 것 같다.
            int k = N - ( j * 5 ); //오류가남..오류안나게 하려면 어떻게??//이미 틀린거 검증됨;
            // k 는 (남은 수를 3kg봉지로 들 개수)

            /* j + k = R; */
            System.out.println("상근이가 배달할 봉지의 최소개수 : " + (j + k));
        }

    }
}
