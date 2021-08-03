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
	public String cnn;
	public String LocationDescription;
	public String Address;
	public String blocklot;
	public String block;
	public String lot;
	public String permit;
	public String Status;
	@Column(length = 1337)
	public String FoodItems;
	public String X;
	public String Y;
	public String Latitude;
	public String Longitude;
	public String Schedule;
	public String dayshours;
	public String NOISent;
	public String Approved;
	public String Received;
	public String PriorPermit;
	public String ExpirationDate;
	public String Location;
	public String FirePreventionDistricts;
	public String PoliceDistricts;
	public String SupervisorDistricts;
	public String ZipCodes;
	public String Neighborhoods;
	
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
	
	public String getCnn() {
		return cnn;
	}
	public void setCnn(String cnn) {
		this.cnn = cnn;
	}
	public String getX() {
		return X;
	}
	public void setX(String x) {
		X = x;
	}
	public String getY() {
		return Y;
	}
	public void setY(String y) {
		Y = y;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getSchedule() {
		return Schedule;
	}
	public void setSchedule(String schedule) {
		Schedule = schedule;
	}
	public String getDayshours() {
		return dayshours;
	}
	public void setDayshours(String dayshours) {
		this.dayshours = dayshours;
	}
	public String getNOISent() {
		return NOISent;
	}
	public void setNOISent(String nOISent) {
		NOISent = nOISent;
	}
	public String getApproved() {
		return Approved;
	}
	public void setApproved(String approved) {
		Approved = approved;
	}
	public String getReceived() {
		return Received;
	}
	public void setReceived(String received) {
		Received = received;
	}
	public String getPriorPermit() {
		return PriorPermit;
	}
	public void setPriorPermit(String priorPermit) {
		PriorPermit = priorPermit;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getFirePreventionDistricts() {
		return FirePreventionDistricts;
	}
	public void setFirePreventionDistricts(String firePreventionDistricts) {
		FirePreventionDistricts = firePreventionDistricts;
	}
	public String getPoliceDistricts() {
		return PoliceDistricts;
	}
	public void setPoliceDistricts(String policeDistricts) {
		PoliceDistricts = policeDistricts;
	}
	public String getSupervisorDistricts() {
		return SupervisorDistricts;
	}
	public void setSupervisorDistricts(String supervisorDistricts) {
		SupervisorDistricts = supervisorDistricts;
	}
	public String getNeighborhoods() {
		return Neighborhoods;
	}
	public void setNeighborhoods(String neighborhoods) {
		Neighborhoods = neighborhoods;
	}
}
