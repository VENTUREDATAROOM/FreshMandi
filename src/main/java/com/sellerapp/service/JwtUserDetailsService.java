package com.sellerapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.repository.GdmsApiRepository;
@Service
public class JwtUserDetailsService  implements UserDetailsService {

	@Autowired
	GdmsApiRepository gdmsRepository;

	Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

		try {
			Optional<GdmsApiUsers> us = gdmsRepository.findByMobileNumber(mobileNumber);
			System.out.println(us.isPresent());
			if (!us.isPresent()) {
				throw new UsernameNotFoundException("user is not present in the database  " + mobileNumber);

			} else {
				return new User(us.get().getMobileNumber(), us.get().getPassword(), new ArrayList<>());
			}

		} catch (Exception e) {
			throw new UsernameNotFoundException("something  went wrong exception is :" + e.getMessage());
		}

	}

}
