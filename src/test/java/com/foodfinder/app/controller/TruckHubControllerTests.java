package com.foodfinder.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodfinder.app.batch.DBWriter;
import com.foodfinder.app.model.TruckInfo;
import com.foodfinder.app.repository.TruckInfoRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TruckHubControllerTests {

	@org.mockito.InjectMocks
	private TruckHubController impl;
	
	@Mock
	DBWriter dbwriter;
	
	@Test
	void findByLocationIdTest() {
		
		Map<String, TruckInfo> info = createTruckInfoByLocationID();
		when(dbwriter.getMapByLocationId()).thenReturn(info);
					
		TruckInfo result = impl.getByID("111");
		assertEquals("555 MISSION ST",result.getAddress());
		assertEquals("Flavors of Africa",result.getApplicant());
		assertEquals("3721",result.getBlock());
		assertEquals("3721120",result.getBlocklot());
		
	}
	
	@Test
	void findByBlockTest() {
		Map<String, TruckInfo> info = createTruckInfoByLocationID();
		Map<String, List<String>> blockMap =  createTruckInfoByBlock();
		
		when(dbwriter.getMapByLocationId()).thenReturn(info);
		when(dbwriter.getmapByBlock()).thenReturn(blockMap);
		
		List<TruckInfo> result = impl.getByBlock("3721");
		assertEquals(2,result.size());
		
		assertEquals("111", result.get(0).getLocationid());
		assertEquals("3721", result.get(0).getBlock());
		
		assertEquals("222", result.get(1).getLocationid());
		assertEquals("3721", result.get(1).getBlock());
		
	}


	@Test
	void addbyTruckInfoTest() {
		Map<String, TruckInfo> truckInfo = createTruckInfoByLocationID();
		assertEquals(2, truckInfo.size());
		when(dbwriter.getMapByLocationId()).thenReturn(truckInfo);
		
		TruckInfo info = new TruckInfo();
		info.setLocationid("333");
		info.setAddress("333 MISSION ST");
		info.setApplicant("Flavors of America");
		info.setBlock("3721");
		info.setBlocklot("3721120");
		info.setFoodItems("added");
		
		impl.addbyTruckInfo(info);
		assertEquals(3, truckInfo.size());
		
		
	}

	private Map<String, TruckInfo>  createTruckInfoByLocationID() {
		Map<String, TruckInfo> map = new HashMap<>();
		
		
		TruckInfo info = new TruckInfo();
		info.setLocationid("111");
		info.setAddress("555 MISSION ST");
		info.setApplicant("Flavors of Africa");
		info.setBlock("3721");
		info.setBlocklot("3721120");
		info.setFoodItems("Meat and vegi rice bowls: meat and vegi salad bowls: meat and vegi wraps: drinks and juices.");
		
		TruckInfo info1 = new TruckInfo();
		info1.setLocationid("222");
		info1.setAddress("222 MISSION ST");
		info1.setApplicant("Flavors of America");
		info1.setBlock("3721");
		info1.setBlocklot("3535353");
		info1.setFoodItems("drinks and juices.");
		
		map.put(info.getLocationid(), info);
		map.put(info1.getLocationid(), info1);
	
		return map;
	}
	
	private Map<String, List<String>> createTruckInfoByBlock() {
		Map<String, List<String>> blockMap = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("111");
		list.add("222");
		
		blockMap.put("3721", list);
		
		return blockMap;
	}


}
