package com.shris;

public class EqualsTest {

	private int x;
	public EqualsTest(int i) {
		this.x = i;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqualsTest other = (EqualsTest) obj;
		if (x != other.x)
			return false;
		return true;
	}

	public static void main(String[] args) {

		//Integer t1=129;
		//Integer t2=129;
		EqualsTest t1 = new EqualsTest(10);
		EqualsTest t2 = new EqualsTest(10);
		System.out.println(t1==t2);
		System.out.println("t1 object "+t1);
		System.out.println("t2 object "+t2);
		System.out.println(t1.equals(t2));
	}

}
