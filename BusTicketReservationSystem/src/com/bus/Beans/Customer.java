package com.bus.Beans;

import java.util.Objects;

public class Customer {
	
	private int cusId;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String address;
	private String mobile;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int cusId, String userName, String passWord, String firstName, String lastName, String address,
			String mobile) {
		super();
		this.cusId = cusId;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobile = mobile;
	}
	
	public Customer(String userName, String passWord, String firstName, String lastName, String address,
			String mobile) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobile = mobile;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", userName=" + userName + ", passWord=" + passWord + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address + ", mobile=" + mobile + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, cusId, firstName, lastName, mobile, passWord, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && cusId == other.cusId
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(mobile, other.mobile) && Objects.equals(passWord, other.passWord)
				&& Objects.equals(userName, other.userName);
	}
	
	

}
