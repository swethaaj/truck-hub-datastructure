package com.foodfinder.app.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.foodfinder.app.model.TruckInfo;

@Component
public class Processor implements ItemProcessor<TruckInfo, TruckInfo>{

	@Override
	public TruckInfo process(TruckInfo truckInfo) throws Exception {
		
		TruckInfo info = new TruckInfo();
		info.setLocationid(truckInfo.getLocationid());
		info.setApplicant(truckInfo.getApplicant());
		info.setFacilityType(truckInfo.getFacilityType());
		info.setCnn(truckInfo.getCnn());
		info.setLocationDescription(truckInfo.getLocationDescription());
		info.setAddress(truckInfo.getAddress());
		info.setBlocklot(truckInfo.getBlocklot());
		info.setBlock(truckInfo.getBlock());
		info.setLot(truckInfo.getLot());
		info.setPermit(truckInfo.getPermit());
		info.setStatus(truckInfo.getStatus());
		info.setFoodItems(truckInfo.getFoodItems());
		info.setX(truckInfo.getX());
		info.setY(truckInfo.getY());
		info.setLatitude(truckInfo.getLatitude());
		info.setX(truckInfo.getX());
		info.setLongitude(truckInfo.getLongitude());
		info.setSchedule(truckInfo.getSchedule());
		info.setDayshours(truckInfo.getDayshours());
		info.setNOISent(truckInfo.getNOISent());
		info.setApproved(truckInfo.getApproved());
		info.setReceived(truckInfo.getReceived());
		info.setPriorPermit(truckInfo.getPriorPermit());
		info.setExpirationDate(truckInfo.getExpirationDate());
		info.setLocation(truckInfo.getLocation());
		info.setFirePreventionDistricts(truckInfo.getFirePreventionDistricts());
		info.setPoliceDistricts(truckInfo.getPoliceDistricts());
		info.setSupervisorDistricts(truckInfo.getSupervisorDistricts());
		info.setZipCodes(truckInfo.getZipCodes().isEmpty() ? truckInfo.getZipCodes() : "");
		info.setNeighborhoods(truckInfo.getNeighborhoods());
		return info;
	}
	

}
