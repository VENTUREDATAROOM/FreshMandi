package com.sellerapp.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.RegisterModel;
import com.sellerapp.repository.GdmsApiRepository;

@Service
public class RegistrationService {
	
	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationService.class);

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	GdmsApiRepository gdmsRepository;
	
	@Autowired
	ModelMapper mapper;
	
	
	public String  registerUser(RegisterModel regDto)
	{
		  try
		  {
			 String userCode=generateUserCode(regDto.getName(),regDto.getMobileNumber());
			GdmsApiUsers ec=mapper.map(regDto,GdmsApiUsers.class);
			ec.setPassword(bcryptEncoder.encode(regDto.getPassword()));
			ec.setName(regDto.getName());
			ec.setUserCode(userCode);
		    ec.setEmail(regDto.getEmail());
			gdmsRepository.save(ec);
			return "Success";
		  } catch(Exception e)
		  {
			  log.error("there is an exception in  registring the user {} ", e.getMessage());
			  return "Error";
		  }
	}
	public String findByMobile(RegisterModel reg) {

		try {
			Optional<GdmsApiUsers> mobileCheck = gdmsRepository.findByMobileNumber(reg.getMobileNumber());
			if (mobileCheck.isPresent()) {
				return "A";
			} else {
				return "NA";
			}

		} catch (Exception e) {
			log.error("there is an exception in  registring the user {} ", e.getMessage());
			return "A";
		}

	}
  public String  generateUserCode(String name, String mobileNumber)
  {
	  if(mobileNumber.length()!=10)
	  {
		  log.info("mobile no was incorrect , thats why no user got registered");
		  System.out.println("Mobile number is already have length 10 digits");
		  
	  }
	   return (name.substring(0, 2).toUpperCase())+mobileNumber;
  }
	
	   
	
	
}
