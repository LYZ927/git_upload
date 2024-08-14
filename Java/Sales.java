package com.cathaybk.practice.nt50356.b;

public class Sales extends Employee {

	private int bouns;
	private int payment;

	public int getBouns() {
		return bouns;
	}

	public void setBouns(int sale) {
		this.bouns = (int) (sale * 0.05);
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Sales(String name, String department, int salary, int sale) {
		super(name, department, salary);
		setBouns(sale);
	}

	@Override
	public void printInfo() {
		setPayment(getSalary() + bouns);
		super.printInfo();
		StringBuilder sb = new StringBuilder();
		sb.append("業績獎金： ").append(getBouns()).append('\n').append("總計： ").append(getPayment());
		System.out.println(sb.toString());
		sb.setLength(0);

	}
}
