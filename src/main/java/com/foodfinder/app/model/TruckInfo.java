package com.foodfinder.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TruckInfo {
	
	@Id
	public String locationid;
	public String Applicant;
	public String FacilityType;
	public String LocationDescription;
	public String Address;
	public String blocklot;
	public String block;
	public String lot;
	public String permit;
	public String Status;
	@Column(length = 1337)
	public String FoodItems;
	public String Location;
	public String ZipCodes;
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getApplicant() {
		return Applicant;
	}
	public void setApplicant(String applicant) {
		Applicant = applicant;
	}
	public String getFacilityType() {
		return FacilityType;
	}
	public void setFacilityType(String facilityType) {
		FacilityType = facilityType;
	}
	public String getLocationDescription() {
		return LocationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		LocationDescription = locationDescription;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getBlocklot() {
		return blocklot;
	}
	public void setBlocklot(String blocklot) {
		this.blocklot = blocklot;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	public String getFoodItems() {
		return FoodItems;
	}
	public void setFoodItems(String foodItems) {
		FoodItems = foodItems;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getZipCodes() {
		return ZipCodes;
	}
	public void setZipCodes(String zipCodes) {
		ZipCodes= zipCodes;
	
	}
}
