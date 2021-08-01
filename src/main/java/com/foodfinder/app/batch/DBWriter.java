package com.foodfinder.app.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodfinder.app.model.TruckInfo;
import com.foodfinder.app.repository.TruckInfoRepository;

@Component
public class DBWriter implements ItemWriter<TruckInfo>{

	@Autowired
	private TruckInfoRepository truckInfoRepository;

	@Override
	public void write(List<? extends TruckInfo> truckInfo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Data saved for truckInfo" + truckInfo);
		truckInfoRepository.saveAll(truckInfo);
	}
	
	
}
