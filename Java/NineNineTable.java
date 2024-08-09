package com.cathaybk.practice.nt50356.b;

public class NineNineTable {

	static int countDigit(int x) {
		return (int) Math.floor(Math.log10(x) + 1);
	}

	// multiplier乘數 multi被乘數
	public static void main(String[] args) {
		for (int multiplier = 1; multiplier <= 9; multiplier++) {
			for (int multi = 2; multi <= 9; multi++) {
				int result = multi * multiplier;
				if (countDigit(result) == 2) {
					System.out.printf(multi + "*" + multiplier + "=" + result + "\t");
				} else {
					System.out.print(multi + "*" + multiplier + "= " + result + "\t");
				}
			}
			System.out.println();

		}

	}

}
