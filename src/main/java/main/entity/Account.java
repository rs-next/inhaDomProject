package main.entity;

public class Account {
	private String domID;
	private String domPW;
	private String email;
	private String phone;
	private String role;
	
	public String getDomID() {
		return domID;
	}
	public void setDomID(String domID) {
		this.domID = domID;
	}
	public String getDomPW() {
		return domPW;
	}
	public void setDomPW(String domPW) {
		this.domPW = domPW;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
