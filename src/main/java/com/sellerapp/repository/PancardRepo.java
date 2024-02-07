package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.PancardEntity;

public interface PancardRepo extends JpaRepository<PancardEntity,Long>
{
  PancardEntity findByPancardNumber(String pancardNumber);
}
