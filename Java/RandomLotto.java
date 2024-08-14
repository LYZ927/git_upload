package com.cathaybk.practice.nt50356.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLotto {
	public static void main(String[] args) {
		Random randNum = new Random();
		Set<Integer> hashSet = new HashSet<>();

		while (hashSet.size() < 6) {
			int lottoNum = randNum.nextInt(49) + 1;
			hashSet.add(lottoNum);
		}

		System.out.print("排序前：");
		for (Integer rand : hashSet) {
			System.out.print(rand + " ");
		}
		
		System.out.printf("\n" + "排序後：");
		List<Integer> sortedList = new ArrayList<>(hashSet);
		Collections.sort(sortedList);
		for (Integer list : sortedList) {
			System.out.print(list + " ");
		}
	}
}
