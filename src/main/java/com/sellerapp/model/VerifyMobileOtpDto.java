package com.sellerapp.model;

public class VerifyMobileOtpDto {

	private String mobileNumber;
	private String enteredOtp;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEnteredOtp() {
		return enteredOtp;
	}
	public void setEnteredOtp(String enteredOtp) {
		this.enteredOtp = enteredOtp;
	}



}
