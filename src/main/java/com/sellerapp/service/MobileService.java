package com.sellerapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sellerapp.model.VerifyMobileOtpDto;
@Service
public class MobileService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MobileService.class);

	private Map<String,String> mp=new HashMap<>();

	public String  sendOtp(String mobileNumber)
	{
		try
		{
			if(mp.containsKey(mobileNumber))
			{
				return  "Mobile number is present";
			}
			String otp=generateOtp();
			mp.put(mobileNumber, otp);
			log.info("Sending OTP number " + mobileNumber + ":" +otp);
			return "Success";
		} catch(Exception e)  {
			log.error("Exception is there in reading a mobileNumber : "+e.getMessage());
			return "Error";
		}

	}

	public boolean VerifyOtp(VerifyMobileOtpDto verifyMobileOtpDto) {
		try {
			String mobileNumber=verifyMobileOtpDto.getMobileNumber();
			String enteredOtp=verifyMobileOtpDto.getEnteredOtp();
			String storedOtp = mp.get(mobileNumber);
			if (storedOtp != null && storedOtp.equals(enteredOtp)) {
				return true; // OTP is valid
			} else {
				return false; // Invalid OTP
			}
		} catch (Exception e) {
			log.error("An error occurred during OTP verification: {}", e.getMessage());
			return false; // Handle the exception gracefully
		}
	}



	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
