package com.sellerapp.service;

import java.io.IOException;
import java.util.Base64;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.AadharEntity;
import com.sellerapp.model.AadharDto;
import com.sellerapp.repository.AadharRepository;
@Service
public class AadharService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AadharService.class);
	
	@Autowired
	AadharRepository aadharRepo;
	
	public String saveAadharDetails(MultipartFile frontPage,MultipartFile backPage,AadharDto aadhar)

	{
		try
		{
		    AadharEntity aadharentity=aadharRepo.findByAadharNumber(aadhar.getAadharNumber());
		    if(aadharentity==null)
		    {
		    aadharentity=new AadharEntity();
		    aadharentity.setAadharNumber(aadhar.getAadharNumber());
		    }
			//byte[] bytes=frontPage.getBytes();
			//String base64Image= Base64.getEncoder().encodeToString(bytes);
		    if(frontPage!=null)
		    {
			aadharentity.setFrontPage(Base64.getEncoder().encode(frontPage.getBytes()));
		    }
		    if(backPage!=null)
		    {
		    	aadharentity.setBackPage(Base64.getEncoder().encode(backPage.getBytes()));	
		    }
			aadharRepo.save(aadharentity);
           log.info("Aadhar details saved successfully");
			 return "Success";
		} catch (IOException ioException) {
		       log.error("IO Exception while reading or saving the pancard photo: " + ioException.getMessage(), ioException);
		        return "Error";
		   }
		    
	}

	
	
	
	
}
