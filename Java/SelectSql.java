package com.cathaybk.practice.nt50356.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SelectSql {
	// SQL指令
	private static final String QUERY_ALLCARS_SQL = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from STUDENT.CARS";
	private static final String QUERY_CARS_SQL = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	private static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	private static final String UPDATE_CARS_SQL = "update STUDENT.CARS set  MIN_PRICE = ? , PRICE = ? where MANUFACTURER = ? and TYPE = ? ";
	private static final String DELETE_STRING = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";

	// 連線資訊
	private static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static final String ACCOUNT = "student";
	private static final String PASSWORD = "student123456";

	// 四個主要欄位
	private static final String MANUFACTURER = "MANUFACTURER";
	private static final String TYPE = "TYPE";
	private static final String PRICE = "PRICE";
	private static final String MIN_PRICE = "MIN_PRICE";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 列出所有資料
		displayAllCars(scanner);

		// 輸入指令
		doCommand(scanner);
	}

	private static void doCommand(Scanner scanner) {
		System.out.printf("請選擇以下指令輸入：select, insert, update, delete\n");
		String command = scanner.next();

		switch (command) {
		case "select":
			doQuery(scanner);
			break;
		case "insert":
			doInsert(scanner);
			break;
		case "update":
			doUpdate(scanner);
			break;
		case "delete":
			doDelete(scanner);
			break;
		default:
			System.out.println("無效的指令");
			doCommand(scanner);
			break;
		}
		scanner.close();
	}

	// 調出所有資料
	private static void displayAllCars(Scanner scanner) {
		List<Map<String, String>> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(QUERY_ALLCARS_SQL)) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put(MANUFACTURER, rs.getString(MANUFACTURER));
				map.put(TYPE, rs.getString(TYPE));
				map.put(MIN_PRICE, rs.getString(MIN_PRICE));
				map.put(PRICE, rs.getString(PRICE));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {

			sb.append("製造商：").append((list.get(i)).get(MANUFACTURER)).append("，型號：").append((list.get(i)).get(TYPE))
					.append("，售價：$").append((list.get(i)).get(PRICE)).append("，底價：$")
					.append((list.get(i)).get(MIN_PRICE));

			System.out.println(sb.toString());

			sb.setLength(0);
		}

		doCommand(scanner);
	}

	// 甲、提供 method: query(製造商,類別);查詢條件為製造商、類別。
	private static void doQuery(Scanner scanner) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL);) {

			Map<String, String> data = enterBasicData(scanner);
			pstmt.setString(1, data.get("manu"));
			pstmt.setString(2, data.get("type"));

			ResultSet rs = pstmt.executeQuery();

			StringBuilder sb = new StringBuilder();

			boolean check = true;

			while (rs.next()) {
				sb.append("製造商： ").append(rs.getString(MANUFACTURER)).append("，型號：").append(rs.getString(TYPE))
						.append("，售價：").append(rs.getString(PRICE)).append("，底價：").append(rs.getString(MIN_PRICE))
						.append("\n");
				check = false;
			}
			System.out.println(sb.toString());
			sb.setLength(0);

			if (check) {
				System.out.println("查無此資料");
			}

		} catch (Exception e) {
			System.out.println("查詢失敗，原因：" + e.getMessage());
			e.printStackTrace();
		}

		doCommand(scanner);
	};

	// 乙、提供 method: insert(Map);設定所有欄位。
	private static void doInsert(Scanner scanner) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);) {
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL);

				Map<String, String> data = enterBasicData(scanner);
				pstmt.setString(1, data.get("manu"));
				pstmt.setString(2, data.get("type"));

				Map<String, String> data2 = enterBasicPrice(scanner);
				pstmt.setString(3, data2.get("minPrice"));
				pstmt.setString(4, data2.get("price"));

				pstmt.executeUpdate();
				conn.commit();
				System.out.println("新增成功");

			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		doCommand(scanner);
	}

	// 丙、提供 method: update(Map);by PK (製造商&類別)
	private static void doUpdate(Scanner scanner) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL)) {
			try {
				conn.setAutoCommit(false);
				Map<String, String> data = enterBasicData(scanner);
				Map<String, String> data2 = enterBasicPrice(scanner);

				pstmt.setString(1, data2.get("minPrice"));
				pstmt.setString(2, data2.get("price"));
				pstmt.setString(3, data.get("manu"));
				pstmt.setString(4, data.get("type"));

				int i = pstmt.executeUpdate();

				conn.commit();

				// 判斷是否更新成功
				if (i == 0) {
					System.out.println("更新失敗");
				} else {
					System.out.println("更新成功");

				}

			} catch (Exception e) {
				System.out.println("更新失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		doCommand(scanner);
	}

	// 丁、提供 method: delete(製造商,類別);by PK (製造商&類別)
	private static void doDelete(Scanner scanner) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE_STRING)) {
			try {
				conn.setAutoCommit(false);
				Map<String, String> data = enterBasicData(scanner);

				pstmt.setString(1, data.get("manu"));
				pstmt.setString(2, data.get("type"));

				int i = pstmt.executeUpdate();
				conn.commit();

				if (i == 0) {
					System.out.println("刪除失敗");
				} else {
					System.out.println("刪除成功");
				}

			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		doCommand(scanner);
	}

	// 輸入製造商、類型
	private static Map<String, String> enterBasicData(Scanner scanner) {
		Map<String, String> data = new HashMap<>();
		System.out.print("請輸入製造商: ");
		data.put("manu", scanner.next());
		System.out.print("請輸入類型: ");
		data.put("type", scanner.next());
		return data;
	}

	// 輸入底價、售價
	private static Map<String, String> enterBasicPrice(Scanner scanner) {
		Map<String, String> data = new HashMap<>();
		System.out.print("請輸入底價: ");
		data.put("minPrice", scanner.next());
		System.out.print("請輸入售價: ");
		data.put("price", scanner.next());
		return data;
	}

}