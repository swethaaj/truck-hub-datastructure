package com.foodfinder.app.batch;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodfinder.app.model.TruckInfo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DBWriterTest {

	@org.mockito.InjectMocks
	private DBWriter dbwriter;
	
	
	@Test
	public void writeTest() throws Exception {
		
		//DBWriter impl = new DBWriter();
		
		List<TruckInfo> list = new ArrayList<>();
		TruckInfo info = createTruckInfo1();
		list.add(info);
		
		dbwriter.write(list);
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
