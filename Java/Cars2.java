package com.cathaybk.practice.nt50356.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Cars2 {
	//改寫String.format
	public static void main(String[] args) {
		String csvFile = "C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv";
		List<Map<String, String>> carList = new ArrayList<>();
		// 資料讀取，將HashMap整理到carList
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

			String line = reader.readLine();
			String[] title = line.split(",");

			while ((line = reader.readLine()) != null) {
				String[] value = line.split(",");
				Map<String, String> carMap = new HashMap<String, String>();
				for (int i = 0; i < title.length; i++) {
					carMap.put(title[i], value[i]);
				}
				carList.add(carMap);
			}
			;

			// 使用分組排序(分組依據:manufacturer)
			// 使用treeMap自然排序
			Map<String, List<Map<String, String>>> groupedByM = new TreeMap<>();

			for (Map<String, String> car : carList) {
				String manufacturer = car.get("Manufacturer");
				if (!groupedByM.containsKey(manufacturer)) {
					groupedByM.put(manufacturer, new ArrayList<>());
				}
				groupedByM.get(manufacturer).add(car);
			}

			// 印出title
			// str-> sb
			StringBuilder str = new StringBuilder();
			str.append(String.format("%-12s\t", title[0])).append(String.format("%-10s\t", title[1]))
					.append(String.format("%10s\t", title[2])).append(String.format("%10s\t", title[3]));

			System.out.println(str.toString());
			str.setLength(0);

			// 累計小計
			BigDecimal sumMinPrice = BigDecimal.ZERO;
			BigDecimal sumPrice = BigDecimal.ZERO;
			// 累計合計
			BigDecimal totalMinPrice = BigDecimal.ZERO;
			BigDecimal totalPrice = BigDecimal.ZERO;

			for (Map.Entry<String, List<Map<String, String>>> entry : groupedByM.entrySet()) {
				for (Map<String, String> car : entry.getValue()) {
					// 列印每筆項目
					
					str.append(String.format("%-12s\t", car.get("Manufacturer")))
							.append(String.format("%-10s\t", car.get("Type")))
							.append(String.format("%10s\t", car.get("Min.Price")))
							.append(String.format("%10s\t", car.get("Price")));
					System.out.println(str);
					str.setLength(0);

					sumMinPrice = sumMinPrice.add(new BigDecimal((car.get("Min.Price"))));
					sumPrice = sumPrice.add(new BigDecimal((car.get("Price"))));
				}
				// 列印小計
				str.append(String.format("%-12s\t", "小計")).append(String.format("%-10s\t", ""))
						.append(String.format("%10s\t", sumMinPrice)).append(String.format("%10s\t", sumPrice));
				System.out.println(str.toString());
				str.setLength(0);

				totalMinPrice = totalMinPrice.add(sumMinPrice);
				totalPrice = totalPrice.add(sumPrice);

				sumMinPrice = BigDecimal.ZERO;
				sumPrice = BigDecimal.ZERO;

			}
			// 列印合計
			str.append(String.format("%-12s\t", "合計")).append(String.format("%-10s\t", ""))
					.append(String.format("%10s\t", totalMinPrice)).append(String.format("%10s\t", totalPrice));
			System.out.println(str.toString());
			str.setLength(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
