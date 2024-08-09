package com.cathaybk.practice.nt50356.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {

	public static void main(String[] args) {
//		將檔案在 Java 中讀取，並做以下輸出
//		一筆資料轉存成一個 Map，並將所有資料放入 List 中並利用 Collections 類別的 sort 方法，針對 Price 這個欄位進行資料排序後(DESC)輸
//		出成另一份檔案，ex: cars2.csv 。

		String csvFile = "C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv";
		String line = "";
		String delimiter = ","; // 定義CSV的分隔符號
		Map<String, String> map = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// 讀取CSV的第一行，作為Map的Key值
//			if ((line = br.readLine()) != null) {
//				String[] keys = line.split(delimiter);
//				for (String key : keys) {
//					map.put(key, null); // 暫時將value設為null
//				}
//				System.out.println(keys);
//			}
			
//			List<Map<Key, V>> list = new ArrayList<Map<K, V>>();
			
			// 如果需要讀取第二行之後的值，可以再繼續讀取後面的行，並將對應的值存入Map
			// 例如：
			if ((line = br.readLine()) != null) {
				String[] values = line.split(delimiter);
				int index = 0;
				for (String key : map.keySet()) {
					if (index < values.length) {
						map.put(key, values[index]);
						index++;
					}
				}
			}

			
			
			
			// 印出Map的內容
			System.out.println("Map內容：" + map);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
