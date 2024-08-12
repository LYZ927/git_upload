package com.cathaybk.practice.nt50356.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Cars {

	public static void main(String[] args) {

		String csvFile = "C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv";
		String outputFile = "C:\\Users\\Admin\\Downloads\\cars.csv";
		List<Map<String, String>> carList = new ArrayList<Map<String, String>>();

		// 1. 讀取csv檔案資料
		try (FileReader fileReader = new FileReader(csvFile); BufferedReader reader = new BufferedReader(fileReader)) {
			// 讀取資料中第一列 -> ﻿Manufacturer,Type,Min.Price,Price
			String line = reader.readLine();
			String[] title = line.split(",");

			// 2.每筆資料轉存成一個 Map，並將所有資料放入 List 中
			while ((line = reader.readLine()) != null) {
				String[] value = line.split(","); // 將第二列資料開始後的每筆轉成陣列
				Map<String, String> carMap = new TreeMap<String, String>();// 每次迴圈都先初始化Map
				for (int i = 0; i < title.length; i++) {
					carMap.put(title[i], value[i]); // 將title和map輸入到hashMap中
				}
				carList.add(carMap);// 將hashMap輸入到arrayList中
				System.out.println(carMap);
			}
			;

			// 3.利用 Collections的sort，將Price欄位進行排序(DESC)
			Collections.sort(carList, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> car1, Map<String, String> car2) {
					double price1 = Double.parseDouble(car1.get("Price"));
					double price2 = Double.parseDouble(car2.get("Price"));
					return Double.compare(price2, price1);
				};
			});

			
//			4.輸出成另一份檔案cars2.csv 
			try (FileWriter fileWriter = new FileWriter(outputFile);
					BufferedWriter writer = new BufferedWriter(fileWriter)) {
				// 寫入標題
				writer.write(String.join(",", title));
				writer.newLine();

				// 寫入資料
				for (Map<String, String> carMap : carList) {
					// {﻿Manufacturer=Audi, Type=Midsize, Min.Price=30.8, Price=37.7}
					// 創一個新的arrayList來放每筆資料
					List<String> list = new ArrayList<String>();

					for (String key : title) {
						list.add(carMap.get(key));
						// list.add(carMap.get("Manufacturer")); -> list = [Audi]
						// list.add(carMap.get("Type")); -> list = [Audi, Midsize]
						// list.add(carMap.get("Min.Price")); -> list = [Audi, Midsize, 30.8]
						// list.add(carMap.get("Price")); -> list = [Audi, Midsize, 30.8, 37.7]
					}
					// 將陣列[Audi, Midsize, 30.8, 37.7]轉為字串，並寫入csv
					writer.write(String.join(",", list));
					writer.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
