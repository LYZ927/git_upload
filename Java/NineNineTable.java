package com.cathaybk.practice.nt50356.b;

public class NineNineTable {

	// multiplier乘數 multi被乘數
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int multiplier = 1; multiplier <= 9; multiplier++) {
			for (int multi = 2; multi <= 9; multi++) {
				int result = multi * multiplier;
				if (result > 9) {
					sb.append(multi).append('*').append(multiplier).append('=').append(result).append('\t');
				} else {
					sb.append(multi).append('*').append(multiplier).append("= ").append(result).append('\t');
				}
			}
			System.out.println(sb.toString());
			sb.setLength(0);
		}

	}

}
