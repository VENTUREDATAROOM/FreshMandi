package com.sellerapp.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.PancardEntity;
import com.sellerapp.model.PancardDto;
import com.sellerapp.repository.PancardRepo;

@Service
public class PancardService {
	@Autowired
	PancardRepo pancardRepo;
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PancardService.class);


	public String savepancardphoto(MultipartFile pic, PancardDto pan)
	{
		try
		{
			PancardEntity pancardEntity=pancardRepo.findByPancardNumber(pan.getPancardNumber());
			if(pancardEntity==null)
			{
				pancardEntity=new PancardEntity();
				pancardEntity.setPancardNumber(pan.getPancardNumber());
			}
			pancardEntity.setPic(Base64.getEncoder().encode(pic.getBytes()));
			pancardRepo.save(pancardEntity);
			log.info("Pancard details are get successfully");
			return "Success";
		}
		catch (IOException ioException) {
			log.error("IO Exception while reading or saving the pancard photo: " + ioException.getMessage(), ioException);
			return "Error";
		}

	}
	public boolean verifyPancardNumber(PancardDto pan)
	{
		return pan!=null && pan.getPancardNumber()!=null && pan.getPancardNumber().length()==10;
	}
}
