package com.cathaybk.practice.nt50356.b;

public class NineNineTable {

	static int countDigit(int x) {
		return (int) Math.floor(Math.log10(x) + 1);
	}

	public static void main(String[] args) {
		for (int divisor = 1; divisor <= 9; divisor++) {
			for (int dividend = 2; dividend <= 9; dividend++) {
				int result = dividend * divisor;
				if (countDigit(result) == 2) {
					System.out.printf(dividend + "*" + divisor + "=" + result + "\t");
				} else {
					System.out.print(dividend + "*" + divisor + "= " + result + "  ");
				}
			}
			System.out.println();

		}

	}

}
