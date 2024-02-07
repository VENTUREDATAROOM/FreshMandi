package com.sellerapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.Response2;
import com.sellerapp.model.VerifyMobileOtpDto;
import com.sellerapp.service.MobileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "MobileOTP-API")
public class MobileController {


	@Autowired
	private MobileService mobileService;

	//private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MobileController.class);



	@PostMapping("/sendOtp")
	@Operation(summary="send an otp through mobile number")
	public ResponseEntity<Object> sendanOtp(@RequestParam String mobileNumber)

	{
		String result=mobileService.sendOtp(mobileNumber);
		if("Success".equals(result))
		{
			return Response2.generateResponse("Mobile number is already there",HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Mobile Number is not there", HttpStatus.BAD_REQUEST, "400");
		}

	}
	@PostMapping("/verifyotp")
	@Operation(summary="to verify an otp through mobile number")
	public ResponseEntity<Object> verifyAnOtp(@RequestBody VerifyMobileOtpDto verifyMobileOtpDto) {



		boolean otpIsValid = mobileService.VerifyOtp(verifyMobileOtpDto);
		if (otpIsValid) {

			return Response2.generateResponse("OTP is valid", HttpStatus.OK, "200");
		} else {

			return Response2.generateResponse("Invalid OTP", HttpStatus.BAD_REQUEST, "400");
		}
	}

	/*	@PostMapping("/verifyotp")
	public ResponseEntity<Object>  verifyanotp(@RequestParam String mobileNumber, @RequestParam String enteredOtp)
	{

		boolean otpisValid=mobileService.verifyOtp(mobileNumber,enteredOtp);
		if(otpisValid)
		{
			return Response2.generateResponse("OTP is valid",HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Invalid OTP",HttpStatus.BAD_REQUEST, "400");
		}
	}*/
}
