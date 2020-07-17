package com.shris;

public class ToStringTest {

	private int empNo;
	private String name;
	private double salary;

	public ToStringTest(int empNo, String name, int salary) {
		this.empNo = empNo;
		this.name = name;
		this.salary = salary;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "ToStringTest [empNo=" + empNo + ", name=" + name + ", salary=" + salary + "]";
	}

	public static void main(String[] args) {
		ToStringTest t1 = new ToStringTest(100, "vivek", 50000);
		System.out.println(t1);
	}

}
