package com.cathaybk.practice.nt50356.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {
	//輸入1~12月
	public static void main(String[] args) {
		Scanner monthScanner = new Scanner(System.in);
		System.out.print("輸入介於1~12的整數：");
		int enterMonth = monthScanner.nextInt();

		while (enterMonth < 1 || enterMonth > 12) {
			System.out.println("輸入的月份有誤，請輸入1~12月!");
			System.out.println("----------------------");
			System.out.print("輸入介於1~12的整數：");
			enterMonth = monthScanner.nextInt();
		}

		printCalendar(enterMonth);
		monthScanner.close();
	}
	
	//執行列印
	public static void printCalendar(int enterMonth) {
		//enterMonth目前的月份
		//currentYear 目前的年份
		int currentYear = LocalDate.now().getYear();
		System.out.print("    "+currentYear + "年" + enterMonth + "月"+'\n');
		System.out.println("---------------------");
		System.out.println("日 一  二  三  四  五  六");
		System.out.println("=====================");
		
		
		//currentYearMonth 取得年、月		
		YearMonth currentYearMonth = YearMonth.of(currentYear, enterMonth);
		
		//年 月 1
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        System.out.println(firstDayOfMonth);
        
        //daysInMonth每月的天數
        int daysInMonth = currentYearMonth.lengthOfMonth(); 
        System.out.println(daysInMonth);
        
        //在每周的第幾天
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue()+1;
        System.out.println(firstDayOfWeek);
        
        //以八月為例，firstDayOfWeek=5
        //1~4個輸出兩個字元        
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("   ");
        }

      //以八月為例，daysInMonth=31
        //for迴圈會印出1~31天，不足兩字元的天數自動補空格靠右
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%2d ", day);
            
            //當天日期+在每周的第幾天-1，若結果為七的倍數，代表一周七天以打印完，要換行
            if ((day + firstDayOfWeek - 1) % 7 == 0) {
                System.out.println();
            }
        }
	}

}
