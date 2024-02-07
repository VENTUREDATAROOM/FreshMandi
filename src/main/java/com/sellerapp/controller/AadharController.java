package com.sellerapp.controller;





import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.model.AadharDto;
import com.sellerapp.model.Response2;
import com.sellerapp.service.AadharService;

import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "Aadhar-API")
public class AadharController {

	@Autowired
	AadharService aadharService;
	@PostMapping(value="/uploadAadhar",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="upload aadhar card details")
	public ResponseEntity<?> uploadAadharDetailsphoto(@Valid @RequestParam("frontPage") MultipartFile frontPage,@RequestParam("backPage") MultipartFile backPage,@RequestBody AadharDto aadhar)
	{
		String result=aadharService.saveAadharDetails(frontPage,backPage,aadhar);
		if("Success".equals(result))
		{
			return Response2.generateResponse("Aaadhar card  front details are get upload", HttpStatus.OK, "200");
		}
		else if("Error".equals(result))
		{
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
		else
		{
			return Response2.generateResponse("Aadhar Card front details are not upload", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}

	}
}

