package com.foodfinder.app.batch;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodfinder.app.model.TruckInfo;
import com.foodfinder.app.repository.TruckInfoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DBWriterTest {

	@Autowired
	private DBWriter impl;
	
	@Mock
	private TruckInfoRepository truckInfoRepository;
	
	@Mock
	private ItemWriter itemwriter;

	@Test
	public void writeTest() throws Exception {
//		List<TruckInfo> list = new ArrayList<>();
//		TruckInfo info = createTruckInfo1();
//		list.add(info);
//		
//		impl.write(list);
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
	
}
