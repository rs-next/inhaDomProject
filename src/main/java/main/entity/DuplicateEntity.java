package main.entity;

public class DuplicateEntity implements Comparable<DuplicateEntity>{
	private int stuNum;
	private String stuAddress;
	private double distance;
	public DuplicateEntity() {
		
	}

public DuplicateEntity(int stuNum, String stuAddress, double stuDistance) {
		this.stuNum = stuNum;
		this.stuAddress = stuAddress;
		this.distance = stuDistance;
	}
	
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(DuplicateEntity o) {
		
		return Double.compare(o.distance, this.distance);
	}
	
}
