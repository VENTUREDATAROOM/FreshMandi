package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="aadhar_details")
public class AadharEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="aadhar_id")
	private Long Id;
	@Column(name="aadhar_no")
	private String aadharNumber;
	
	@Column(name="frontpage_pic")
	private byte[] frontPage;
	@Column(name="backpage_pic")
	private byte[] backPage;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public byte[] getFrontPage() {
		return frontPage;
	}
	public void setFrontPage(byte[] frontPage) {
		this.frontPage = frontPage;
	}
	public byte[] getBackPage() {
		return backPage;
	}
	public void setBackPage(byte[] backPage) {
		this.backPage = backPage;
	}
	
	

}
