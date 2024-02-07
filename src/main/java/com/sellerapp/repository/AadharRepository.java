package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.AadharEntity;

public interface AadharRepository  extends JpaRepository<AadharEntity,Long>
{
  AadharEntity findByAadharNumber(String aadharNumber);
}
