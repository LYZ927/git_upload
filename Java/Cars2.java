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

    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv";
        List<Map<String, String>> carList = new ArrayList<>();

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
			};

//			Collections.sort(carList, new Comparator<Map<String, String>>() {
//			    @Override
//			    public int compare(Map<String, String> car1, Map<String, String> car2) {
//			        return car1.get("Manufacturer").compareTo(car2.get("Manufacturer"));
//			    }
//			});

			
			 // 分組存儲結果
            Map<String, List<Map<String, String>>> groupedByM = new TreeMap<>();  

            for (Map<String, String> car : carList) {
                String manufacturer = car.get("Manufacturer");
                if (!groupedByM.containsKey(manufacturer)) {
                    groupedByM.put(manufacturer, new ArrayList<>());
                }
                groupedByM.get(manufacturer).add(car);
            }
            
            //title
            for (int i = 0; i < title.length; i++) {
				 System.out.printf(title[i]+"\t");
			}
            
           System.out.println();
           
            // 輸出結果
            for (Map.Entry<String, List<Map<String, String>>> entry : groupedByM.entrySet()) {
//                System.out.println("Manufacturer: " + entry.getKey());
                for (Map<String, String> car : entry.getValue()) {
                    System.out.print(car.get("Manufacturer"));
                    System.out.print(car.get("Type"));
                    System.out.print(car.get("Min.Price"));
                    System.out.print(car.get("Price"));
                    System.out.println();                    
                    
                    BigDecimal b1 = new BigDecimal(Double.(car.get("Min.Price"))); 
                    BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
                    return b1.add(b2).doubleValue(); 
                }
                
                
                System.out.println("小計");
                System.out.println("合計");
                
                
            }
			
			
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


