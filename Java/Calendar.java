package com.cathaybk.practice.nt50356.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

	// 輸入1~12月
	public static void main(String[] args) {
		Scanner monthScanner = new Scanner(System.in);
		StringBuilder str = new StringBuilder();

		System.out.print("輸入介於1~12的整數：");
		int enterMonth = monthScanner.nextInt();

		while (enterMonth < 1 || enterMonth > 12) {
			str.append("輸入的月份有誤，請輸入1~12月!").append('\n').append("----------------------").append('\n')
					.append("輸入介於1~12的整數：");
			System.out.println(str.toString());
			enterMonth = monthScanner.nextInt();
		}

		printCalendar(enterMonth);
		monthScanner.close();
	}

	// 執行列印
	public static void printCalendar(int enterMonth) {
		// currentYear目前的年份
		int currentYear = LocalDate.now().getYear();

		StringBuilder str = new StringBuilder();

		str.append('\t').append(currentYear).append("年").append(enterMonth).append("月").append('\n')
				.append("---------------------").append('\n').append("日  一  二  三  四  五  六").append('\n')
				.append("=====================");
		System.out.println(str.toString());
		str.setLength(0);

		// currentYearMonth 取得年、月
		YearMonth currentYearMonth = YearMonth.of(currentYear, enterMonth);

		// firstDayOfMonth year/month/01
		LocalDate firstDayOfMonth = currentYearMonth.atDay(1);

		// daysInMonth每月的天數
		int daysInMonth = currentYearMonth.lengthOfMonth();

		// 在每周的第幾天
		int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() + 1;

		// 空的天用3個空白字元代替
		int i = 1;
		while (i < firstDayOfWeek && firstDayOfWeek != 8) {
			System.out.print("   ");
			i++;
		}

		// for迴圈會印出1~??天
		for (int day = 1; day <= daysInMonth; day++) {
			System.out.printf("%3d", day);

			// 當天日期+在每周的第幾天-1，若結果為七的倍數，代表一周七天以打印完，要換行
			if ((day + firstDayOfWeek - 1) % 7 == 0) {
				System.out.println();
			}
		}
	}

}
