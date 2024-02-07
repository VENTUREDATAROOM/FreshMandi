
package com.sellerapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.OtpSignDto;
import com.sellerapp.model.Response2;
import com.sellerapp.model.VerifyOtpRequest;
import com.sellerapp.service.EmailService;
import com.sellerapp.service.OtpService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "EmailOTP-API")
public class OtpController {

	@Autowired
	OtpService otpService;
	@Autowired
	EmailService emailService;
	@PostMapping("VerifyOtp")
	@Operation(summary="verify the otp through email")
	public ResponseEntity<?> Verifyotp(@Valid @RequestBody VerifyOtpRequest request) {
		String result = otpService.verifyOtp(request);

		if ("Success".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.OK, "200");
		} else if ("Invalid OTP".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.BAD_REQUEST, "400");
		} else {
			return Response2.generateResponse(result, HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}
	@PostMapping("/sendEmail")
	@Operation(summary="otp sign in through email")
	public ResponseEntity<?> OtpSignIn(@RequestBody OtpSignDto otpsignDto) {


		String savedotp = otpService.otpSignin(otpsignDto);

		if (savedotp!= null) {
			return Response2.generateResponse(savedotp, HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}
	@PostMapping("/verifyEmail")
	@Operation(summary="verification  email")
	public ResponseEntity<?> verificationEmail(@RequestParam String email) {

		boolean verificationsuccess=sendVerificationEmail(email);
		if(verificationsuccess)
		{
			return Response2.generateResponse("Email verified successfully", HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Email is not verified successfully", HttpStatus.BAD_REQUEST, "400");
		}

	}
	private boolean  sendVerificationEmail(String email)
	{
		String subject = "Verify your email";
		String message = "<html>" +
				"<body>" +
				"<p>Hello,</p>" +

				    "<p>You receive this message either because you recently applied to, registered on our website, or are considered as a potential candidate for a job offered through our portal.</p>" +

				    "<p>Please validate your email address by clicking <a href=\"YOUR_VALIDATION_LINK\">here</a> (please log in using your existing credentials).<br>" +
				    "This will take only a few seconds and is to make sure that the recruiters can safely reach you through email.</p>" +

				    "<p>Kind regards,<br>" +
				    "Recruitment Team<br>" +
				    "Venture Consultancy Services, Lucknow</p>" +

				    "</body>" +
				    "</html>";

		//emailService.sendEmail(subject, message, email);
		emailService.sendEmail(subject, message, email);
		return true;
	}
}
