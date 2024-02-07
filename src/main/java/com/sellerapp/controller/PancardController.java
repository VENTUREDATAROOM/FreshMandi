package com.sellerapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.model.PancardDto;
import com.sellerapp.model.Response2;
import com.sellerapp.service.PancardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.models.media.MediaType;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "Pancard-API")
public class PancardController {

	//private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PancardController.class);

	@Autowired
	PancardService pancardService;
	@PostMapping(value="/uploadPancardDetails",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary ="upload pan card details  through pan number")
	public ResponseEntity<?> uploadPancardphoto(@Valid @RequestParam("pic") MultipartFile pic, @RequestBody PancardDto pan)
	{
		String responsesave=pancardService.savepancardphoto(pic,pan);
		if("Success".equals(responsesave))
		{
			return  Response2.generateResponse("Pancard details are uploaded",HttpStatus.OK,"200");

		}
		else if("Error".equals(responsesave))
		{
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
		else
		{
			return Response2.generateResponse("Pan card details are not get uploaded", HttpStatus.INTERNAL_SERVER_ERROR, "500");

		}
	}
	@PostMapping(value ="/verifyPanCard",produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="verify pan card number")
	public ResponseEntity<?> verifyPancard(@RequestBody PancardDto pan) {
		boolean isValid = pancardService.verifyPancardNumber(pan);
		if (isValid) {
			return Response2.generateResponse("Pancard details are verified", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Invalid PAN card number", HttpStatus.BAD_REQUEST, "400");
		}
	}



}
