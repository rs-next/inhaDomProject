package main.entity;

import java.util.List;

public class Applicant {
	private int stuNum;
	private String stuName;
	private String stuBirth;
	private String stuDept;
	private int stuPhoneNum;
	private String stuAddress;
	private String stuDetailAddress;
	private String appliType;
	private double stuGrade;
	private String stuMail;
	private double auditScore;
	private String stuGender;

	
	public Applicant() {
		
	}
	public Applicant(int stuNum, String stuName, String stuBirth, String stuDept, int stuPhoneNum, String stuAddress, String stuDetailAddress, 
			String
			appliType, double stuGrade, String stuMail, double auditScore, String stuGender) {
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.stuBirth = stuBirth;
		this.stuDept = stuDept;
		this.stuPhoneNum = stuPhoneNum;
		this.stuAddress = stuAddress;
		this.stuDetailAddress = stuDetailAddress;
		this.appliType = appliType;
		this.stuMail = stuMail;
		this.auditScore = auditScore;
		this.stuGrade = stuGrade;
		this.stuGender = stuGender;
	}
	
	public Applicant(String
			appliType, double auditScore, String stuGender) {
		this.appliType = appliType;
		
		this.auditScore = auditScore;
		
		this.stuGender = stuGender;
	}
	
	public String getStuGender() {
		return stuGender;
	}
	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuBirth() {
		return stuBirth;
	}
	public void setStuBirth(String stuBirth) {
		this.stuBirth = stuBirth;
	}
	public String getStuDept() {
		return stuDept;
	}
	public void setStuDept(String stuDept) {
		this.stuDept = stuDept;
	}
	public int getStuPhoneNum() {
		return stuPhoneNum;
	}
	public void setStuPhoneNum(int stuPhoneNum) {
		this.stuPhoneNum = stuPhoneNum;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public String getStuDetailAddress() {
		return stuDetailAddress;
	}
	public void setStuDetailAddress(String stuDetailAddress) {
		this.stuDetailAddress = stuDetailAddress;
	}
	public String getAppliType() {
		return appliType;
	}
	public void setAppliType(String appliType) {
		this.appliType = appliType;
	}
	public double getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(double stuGrade) {
		this.stuGrade = stuGrade;
	}
	public String getStuMail() {
		return stuMail;
	}
	public void setStuMail(String stuMail) {
		this.stuMail = stuMail;
	}
	public double getAuditScore() {
		return auditScore;
	}
	public void setAuditScore(double auditScore) {
		this.auditScore = auditScore;
	}
	
	
	
	
}
