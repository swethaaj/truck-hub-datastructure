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
		info.setLocationDescription(truckInfo.getLocationDescription());
		info.setAddress(truckInfo.getAddress());
		info.setBlocklot(truckInfo.getBlocklot());
		info.setBlock(truckInfo.getBlock());
		info.setLot(truckInfo.getLot());
		info.setPermit(truckInfo.getPermit());
		info.setStatus(truckInfo.getStatus());
		info.setFoodItems(truckInfo.getFoodItems());
		info.setLocation(truckInfo.getLocation());
		info.setZipCodes(truckInfo.getZipCodes().isEmpty() ? truckInfo.getZipCodes() : null);
		return info;
	}
	

}
