package com.cathaybk.practice.nt50356.b;

public class Supervisor extends Employee {

	private int payment;

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Supervisor(String name, String department, int salary) {
		super(name, department, salary);
	}

	@Override
	public void printInfo() {
		setPayment(getSalary());
		super.printInfo();
		System.out.println("總計： " + getPayment());

	}

}
