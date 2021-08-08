package com.foodfinder.app.batch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.foodfinder.app.model.TruckInfo;

@Component
public class TruckHubCache implements ItemWriter<TruckInfo>{

	private Map<Integer, TruckInfo> mapByLocationID;
	private Map<String, Set<Integer>> mapByBlock;

	public TruckHubCache() {
        System.out.println("Writer created ");
        mapByLocationID =  new HashMap<Integer,TruckInfo>();
        mapByBlock = new HashMap<String, Set<Integer>>();
    }

	

	@Override
	public void write(List<? extends TruckInfo> truckInfo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Data saved for truckInfo" + truckInfo);
		
		if (truckInfo != null && truckInfo.size() > 0) {
			for (TruckInfo data : truckInfo) {
				mapByLocationID.put(data.getLocationid(), data);
				
				if(!mapByBlock.containsKey(data.getBlock())) {
					mapByBlock.put(data.getBlock(), new HashSet<Integer>());
				}
				mapByBlock.get(data.getBlock()).add(data.getLocationid());
			
			}		
		}
	}
	
	public Map<Integer, TruckInfo> getMapByLocationId() {
	      return mapByLocationID;
	 }
	
	public Map<String, Set<Integer>> getmapByBlock() {
	      return mapByBlock;
	 }
	
}
