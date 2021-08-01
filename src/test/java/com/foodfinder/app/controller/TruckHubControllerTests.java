package com.foodfinder.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodfinder.app.model.TruckInfo;
import com.foodfinder.app.repository.TruckInfoRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TruckHubControllerTests {

	@org.mockito.InjectMocks
	private TruckHubController impl;
	
	@Mock
	TruckInfoRepository truckInfoRepository;
	
	
	@Test
	void findByLocationIdTest() {
		TruckInfo info = createTruckInfo1();
		when(truckInfoRepository.findByLocationId("111")).thenReturn(info);
					
		TruckInfo result = impl.getByID("111");
		assertEquals("555 MISSION ST",result.getAddress());
		assertEquals("Flavors of Africa",result.getApplicant());
		assertEquals("3721",result.getBlock());
		assertEquals("3721120",result.getBlocklot());
		
	}
	
	@Test
	void findByBlockTest() {
		List<TruckInfo> list = createTruckInfo2();
		when(truckInfoRepository.findByBlock("3721")).thenReturn(list);
					
		List<TruckInfo> result = impl.getByBlock("3721");
		assertEquals(2,result.size());
		
	}



	private TruckInfo createTruckInfo1() {
		TruckInfo info = new TruckInfo();
		info.setLocationid("111");
		info.setAddress("555 MISSION ST");
		info.setApplicant("Flavors of Africa");
		info.setBlock("3721");
		info.setBlocklot("3721120");
		info.setFoodItems("Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.");
		return info;
	}
	
	private List<TruckInfo> createTruckInfo2() {
		List<TruckInfo> infoList = new ArrayList<>();
		
		TruckInfo info = new TruckInfo();
		info.setLocationid("222");
		info.setAddress("222 MISSION ST");
		info.setApplicant("Flavors of India");
		info.setBlock("3721");
		info.setBlocklot("25252");
		info.setFoodItems("drinks and juices.");
		
		infoList.add(createTruckInfo1());
		infoList.add(info);
		return infoList;
	}


}
