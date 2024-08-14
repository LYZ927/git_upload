package com.cathaybk.practice.nt50356.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		try (Scanner monthScanner = new Scanner(System.in);) {

			System.out.print("輸入介於1~12的整數：");
			int enterMonth = monthScanner.nextInt();

			StringBuilder sb = new StringBuilder();
			while (enterMonth < 1 || enterMonth > 12) {
				sb.append("輸入的月份有誤，請輸入1~12月!").append('\n').append("----------------------").append('\n')
						.append("輸入介於1~12的整數：");
				System.out.println(sb.toString());
				sb.setLength(0);
				enterMonth = monthScanner.nextInt();
			}
			printCalendar(enterMonth);
		}
	}

	public static void printCalendar(int enterMonth) {
		// 目前的年份
		int currentYear = LocalDate.now().getYear();

		StringBuilder sb = new StringBuilder();
		sb.append('\t').append(currentYear).append("年").append(enterMonth).append("月").append('\n')
				.append("---------------------").append('\n').append("日  一  二  三  四  五  六").append('\n')
				.append("=====================");
		System.out.println(sb.toString());
		sb.setLength(0);

		// 取得年、月
		YearMonth currentYearMonth = YearMonth.of(currentYear, enterMonth);

		// year/month/01
		LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
		System.out.println(firstDayOfMonth);

		// 每月的天數
		int daysInMonth = currentYearMonth.lengthOfMonth();

		// 在第一周的第幾天
		int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() + 1;

		int i = 1;
		while (i < firstDayOfWeek && firstDayOfWeek != 8) {
			System.out.print("   ");
			i++;
		}

		for (int day = 1; day <= daysInMonth; day++) {
			System.out.printf("%3d", day);

			if ((day + firstDayOfWeek - 1) % 7 == 0) {
				System.out.println();
			}
		}
	}

}
