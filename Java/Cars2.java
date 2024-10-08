package com.cathaybk.practice.nt50356.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Cars2 {
	public static void main(String[] args) {
		//桌面存放的cars.csv路徑
		String userHome = System.getProperty("user.home");
		String csvFilePath = Paths.get(userHome, "Desktop", "Java評量_第6題cars.csv").toString();
		List<Map<String, String>> carList = new ArrayList<>();
		String[] title = null;
		// 資料讀取，將HashMap整理到carList
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

			String line = reader.readLine();
			title = line.split(",");

			while ((line = reader.readLine()) != null) {
				String[] value = line.split(",");
				Map<String, String> carMap = new HashMap<String, String>();
				for (int i = 0; i < title.length; i++) {
					carMap.put(title[i], value[i]);
				}
				carList.add(carMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-12s\t%-10s\t%10s\t%10s\t", title[0], title[1], title[2], title[3]));
		System.out.println(sb.toString());
		sb.setLength(0);

		// 累計小計
		BigDecimal sumMinPrice = BigDecimal.ZERO;
		BigDecimal sumPrice = BigDecimal.ZERO;
		// 累計合計
		BigDecimal totalMinPrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;

		for (Map.Entry<String, List<Map<String, String>>> entry : groupedByM.entrySet()) {
			for (Map<String, String> car : entry.getValue()) {
				// 列印每筆項目
				sb.append(String.format("%-12s\t%-10s\t%10s\t%10s\t", car.get("Manufacturer"), car.get("Type"),
						car.get("Min.Price"), car.get("Price")));

				System.out.println(sb);
				sb.setLength(0);

				sumMinPrice = sumMinPrice.add(new BigDecimal((car.get("Min.Price"))));
				sumPrice = sumPrice.add(new BigDecimal((car.get("Price"))));
			}
			// 列印小計
			sb.append(String.format("%-12s\t%-10s\t%10s\t%10s\t", "小計", "", sumMinPrice, sumPrice));
			System.out.println(sb.toString());
			sb.setLength(0);

			totalMinPrice = totalMinPrice.add(sumMinPrice);
			totalPrice = totalPrice.add(sumPrice);

			sumMinPrice = BigDecimal.ZERO;
			sumPrice = BigDecimal.ZERO;

		}
		// 列印合計
		sb.append(String.format("%-12s\t%-10s\t%10s\t%10s\t", "合計", "", totalMinPrice, totalPrice));
		System.out.println(sb.toString());
		sb.setLength(0);

	}
}
