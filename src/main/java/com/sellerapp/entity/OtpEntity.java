package com.sellerapp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="otp_sign_in")
public class OtpEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="otp_id")
	private Long Id;
    @Column(name="user_name")
	private String username;
    @Column(name="user_code")
	private String userCode;
    @Column(name="email")
	private String email;
	@Column(name="otp")
	private String otp;
	@Column(name="password")
	private String password;
	@Column(name="otp_send")
	private String otpSend;
	
	@Column(name="otp_expiry")
	private String otpExpiry;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOtpSend() {
		return otpSend;
	}
	public void setOtpSend(String otpSend) {
		this.otpSend = otpSend;
	}
	public String getOtpExpiry() {
		return otpExpiry;
	}
	public void setOtpExpiry(String otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	public String getEmail() {
		return email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	
	
	
	
}
