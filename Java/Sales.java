package com.cathaybk.practice.nt50356.b;

public class Sales extends Employee {

	private int bouns;
	private int payment;

	public int getBouns() {
		return bouns;
	}

	public void setBouns(int bouns) {
		this.bouns = bouns;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Sales(String name, String department, int salary, int bouns) {
		super(name, department, salary);
		setBouns(bouns *= 0.05);
	}

	@Override
	public void printInfo() {
		setPayment(getSalary() + bouns);
		super.printInfo();
		System.out.println("月薪： " + getSalary());
		System.out.println("業績獎金： " + getBouns());
		System.out.println("總計： " + getPayment());

	}
}
