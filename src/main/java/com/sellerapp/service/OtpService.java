package com.sellerapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.OtpEntity;
import com.sellerapp.model.OtpSignDto;
import com.sellerapp.model.VerifyOtpRequest;
import com.sellerapp.repository.UserRepository;
@Service
public class OtpService {


	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OtpService.class);


	@Autowired
	UserRepository userRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	ModelMapper mapper;

	public String otpSignin(OtpSignDto otpsignDto)
	{
		try
		{
			String userCode = otpsignDto.getUserCode();
			String email = otpsignDto.getEmail();
			String username = otpsignDto.getUsername();

			String otp=generateRandomOtp();
			OtpEntity oe=mapper.map(otpsignDto,OtpEntity.class);
			//OtpEntity oe=new OtpEntity();

			oe.setOtp(otp);
			LocalDateTime currentDateTime=LocalDateTime.now();
			LocalDateTime otpSendStringFormatted = currentDateTime.plusMinutes(1);


			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpSendString= otpSendStringFormatted.format(formatter);

			oe.setOtpSend(otpSendString);
			LocalDateTime currentdateTime=LocalDateTime.now();
			LocalDateTime otpExpiryStringFormatted = currentdateTime.plusMinutes(1);


			DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpExpiryString= otpExpiryStringFormatted.format(formater);

			oe.setOtpExpiry(otpExpiryString);
			oe=userRepository.saveAndFlush(oe);
			//String email=otpsignDto.getEmail();
			sendOtpByEmail(email,otp);
			//sendAttachByEmail(email,otp);
			//sendVerificationEmail(email);


			log.info("Otp sign in " +userCode+","+username+","+email);
			return "Success";
		}catch(Exception e)
		{
			log.error("Error in otpsign: " + e.getMessage());
			return "Error";
		}
	}
	public String  verifyOtp(VerifyOtpRequest request)

	{     try
	{
		Optional<OtpEntity> otpOptional=userRepository.findByUserCodeAndOtp(request.getUserCode(),request.getOtp());
		if (otpOptional.isPresent())
		{
			OtpEntity oe=otpOptional.get();
			if(request.getOtp().equals(oe.getOtp()) && oe.getOtpExpiry()!=null)
			{
				oe.setOtp(request.getOtp());
				String otpexpiry=oe.getOtpExpiry();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime otpExpiry = LocalDateTime.parse(otpexpiry, formatter);

				LocalDateTime currentDateTime = LocalDateTime.now();
				if (otpExpiry.isAfter(currentDateTime)) {

					System.out.println("otpExpiry get expire after the current time");
					return "Success";
				}
				else if(otpExpiry.isBefore(currentDateTime))
				{
					System.out.println("otpexpiry get expire before the current time");
					return "Invalid OTP";
				}
				else {

					System.out.println("otpExpiry is not equal to current DateTime");

				}
				oe.setOtpExpiry(otpexpiry);
				userRepository.saveAndFlush(oe);
				System.out.println("Verify otp" +request.getUserCode()+","+request.getOtp());
				log.info("Verify otp : " + request.getUserCode() + ", " + request.getOtp());
				return "Success";
			}
			else
			{
				return "It is wrong";
			}
		}
		else
		{
			return "User is not found";
		}
	}catch(Exception e)
	{
		log.error("Error in sign in for otp" +e.getMessage()) ;
		return "Error";
	}
	}
	private String generateRandomOtp() {

		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000);
		return String.valueOf(otpValue);
	}

	private  void sendOtpByEmail(String email,String otp)
	{
		String subject="OTP verification";
		String message ="<html><body>"
				+ "<p>Dear Gorank,</p>"
				+ "<p>Your OTP for verification is: <strong>" + otp + "</strong></p>"
				+ "<p>Please use this OTP within 1 minute to reset your password.</p>"
				+ "<p>If you are unable to change the password within 1 minute of OTP generation, please click on 'Forgot Password' and continue with the same process again.</p>"
				+ "<p>Wish you all the best!</p>"
				+ "<br>"
				+ "<p>Regards,<br>"
				+ "Campus Recruitment Team<br>"
				+ "Venture Consultancy Service, Lucknow</p>"
				+ "</body></html>";
		emailService.sendEmail(subject,message,email);

	}




}
