package com.sellerapp.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.GdmsApiUsers;
public interface GdmsApiRepository extends JpaRepository<GdmsApiUsers, String>{

	Optional<GdmsApiUsers> findByEmail(String email);
	GdmsApiUsers findByUserCode(String userCode);
	
	Optional<GdmsApiUsers> findByMobileNumber(String mobileNumber);
	
	
}
