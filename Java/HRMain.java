package com.cathaybk.practice.nt50356.b;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\output.csv");
			OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write("\uFEFF");

			for (Employee eachList : employeeList) {
				if (eachList instanceof Sales) {
					// 向下轉型，才能使用子類別的方法
					Sales sales = (Sales) eachList;
					bufferedWriter.write(sales.getName() + "，" + sales.getPayment() + "\n");
				} else {
					Supervisor supervisor = (Supervisor) eachList;
					bufferedWriter.write(supervisor.getName() + "，" + supervisor.getPayment() + "\n");
				}
			}
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
