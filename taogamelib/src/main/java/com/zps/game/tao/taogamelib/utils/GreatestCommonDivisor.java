package com.zps.game.tao.taogamelib.utils;

import java.util.Scanner;

public class GreatestCommonDivisor {
//	public static void main(String[] args) {
//        int m;
//        int n;
//        int temp;
//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入第一个数：");
//        m = input.nextInt();
//        System.out.println("请输入第二个数：");
//        n = input.nextInt();
//        System.out.println("最大公约数为：" + GreatestCommonDivisor.greatestCommonDivisorGetter(m, n));
//    }
	
	public static int greatestCommonDivisorGetter(int m,int n){
        int temp;
        int result = 1;
        if (m > n) {
            temp = n;
        } else {
            temp = m;
        }
        for (int i = temp; i >= 1; i--) {
            if (m % i == 0 && n % i == 0) {
                result = i;
                break;
            }
        }
        return result;
	}
}
