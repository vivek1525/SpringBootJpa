package com.shris;

public class HashCodeTest {
 private String s;
	public HashCodeTest(String s) {
		this.s = s;
}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCodeTest other = (HashCodeTest) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}

	public static void main(String[] args) {

       // String s1= "Aa";//new String("Aa");
		//String s2 = "Aa"; //new String("Aa");
		HashCodeTest s1 = new HashCodeTest("Aa");
		HashCodeTest s2 = new HashCodeTest("BB");
		System.out.println(s1.equals(s2));
		System.out.println(s1==s2);
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.hashCode()==s2.hashCode());
	}

}
