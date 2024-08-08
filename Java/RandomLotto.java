package com.cathaybk.practice.nt50356.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomLotto {
	public static void main(String[] args) {
		Random randNum = new Random();
		List<Integer> randList = new ArrayList<Integer>();

		while (randList.size() <= 6) {
			int lottoNum = randNum.nextInt(49) + 1;
			if (!randList.contains(lottoNum)) {
				randList.add(lottoNum);
			}
		}

		System.out.print("排序前：");
		for (Integer rand : randList) {
			System.out.print(rand + " ");
		}

		System.out.printf("\n" + "排序後：");
		Collections.sort(randList);
		for (Integer rand : randList) {
			System.out.print(rand + " ");
		}
	}
}
