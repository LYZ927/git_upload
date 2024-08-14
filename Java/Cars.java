package com.cathaybk.practice.nt50356.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {

	public static void main(String[] args) throws IOException {
		
		//桌面存放的cars.csv路徑
		String userHome = System.getProperty("user.home");
		String csvFilePath = Paths.get(userHome, "Desktop", "Java評量_第6題cars.csv").toString();
		String outputPath = Paths.get(userHome, "Desktop", "cars2.csv").toString();
		
		List<Map<String, String>> carList = new ArrayList<Map<String, String>>();
		String[] title = null;
		// 1. 讀取csv檔案資料
		try (FileReader fileReader = new FileReader(csvFilePath); BufferedReader reader = new BufferedReader(fileReader)) {
			String line = reader.readLine();
			title = line.split(",");

			while ((line = reader.readLine()) != null) {
				String[] value = line.split(",");
				Map<String, String> carMap = new HashMap<String, String>();
				for (int i = 0; i < title.length; i++) {
					carMap.put(title[i], value[i]);
				}
				carList.add(carMap);
			};
			// 2.利用 Collections的sort，將Price欄位進行排序(DESC)		
			Collections.sort(carList, new Comparator<Map<String, String>>() {
			    @Override
			    public int compare(Map<String, String> car1, Map<String, String> car2) {
			        BigDecimal price1 = new BigDecimal(car1.get("Price"));
			        BigDecimal price2 = new BigDecimal(car2.get("Price"));
			        return price2.compareTo(price1); 
			    }
			});
			for (Map<String, String> car : carList) {
				System.out.println(car);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 3.輸出成另一份檔案cars2.csv
		try (FileWriter fileWriter = new FileWriter(outputPath);
				BufferedWriter writer = new BufferedWriter(fileWriter)) {
			writer.write(String.join(",", title));
			writer.newLine();

			// 寫入資料
			for (Map<String, String> carMap : carList) {
				List<String> list = new ArrayList<String>();

				for (String key : title) {
					list.add(carMap.get(key));
				}

				writer.write(String.join(",", list));
				writer.newLine();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
