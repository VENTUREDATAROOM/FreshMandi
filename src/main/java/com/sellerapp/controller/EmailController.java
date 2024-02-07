/*package com.example.demo.controller;




//import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmailService;
import com.example.demo.model.EmailRequest;
import com.example.demo.model.Response2;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "EmailOTP-API")
public class EmailController {
	
	
	@Autowired
	private EmailService emailService;
  
	//private Random random=new Random();
	
	@PostMapping("/sendemail")
	 public ResponseEntity<?>  sendEmail(@Valid @RequestBody EmailRequest request)
	 {
		 
		 boolean  res=emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        if(res)
         {
		 
		 return Response2.generateResponse("Email is sent successfully",HttpStatus.OK, "200");
	   }
      else
      {
   	   return Response2.generateResponse("Email is not send successfully",HttpStatus.INTERNAL_SERVER_ERROR, "500");
      }
	 }
}*/
	 
  /*/
 /*@PostMapping("sendotp")
	public ResponseEntity<String> sendOTP(@RequestParam String email)
	{
		int otp=random.nextInt(999999);
		System.out.println("OTP" +otp);
		String subject="OTP FROM";
		String message="<h1> OTP = "+otp+" </h1>";
		String to=email;
		boolean flag=emailService.sendEmail(subject, message, to);
		if(flag)
		{
			return Response2.generateResponse("Verify OTP",HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Wrong email id",HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	
}*/




