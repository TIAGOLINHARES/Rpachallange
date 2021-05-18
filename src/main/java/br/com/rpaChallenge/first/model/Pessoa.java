package br.com.rpaChallenge.first.model;





public class Pessoa {
	
	
	private String firstName;
	private String lastName;
	private String companyName;
	private String roleInCompany;
	private String address;
	private String email;
	private Long phoneNumber;
	
	
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRoleInCompany() {
		return roleInCompany;
	}
	public void setRoleInCompany(String roleInCompany) {
		this.roleInCompany = roleInCompany;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}