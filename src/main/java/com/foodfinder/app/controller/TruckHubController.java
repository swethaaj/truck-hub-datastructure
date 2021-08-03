package com.foodfinder.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import 	org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodfinder.app.batch.DBWriter;
import com.foodfinder.app.model.TruckInfo;
import com.foodfinder.app.repository.TruckInfoRepository;

@RestController
@RequestMapping(value = "/truckhub")
public class TruckHubController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	DBWriter dbwriter;
	
	@GetMapping
	@RequestMapping(value = "/truckInfo/load")
	public BatchStatus load() throws JobExecutionAlreadyRunningException,
	JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		
		Map<String,JobParameter> maps = new HashMap<>();
		maps.put("time",new JobParameter(System.currentTimeMillis()));
		
		JobParameters params = new JobParameters(maps);
		JobExecution execution = jobLauncher.run(job, params);
		
		while(execution.isRunning()) {
			System.out.print("....");
		}
		return execution.getStatus();
	}
	
	@GetMapping
	@RequestMapping(value = "/truckInfo/locationId/{id}",method = RequestMethod.GET)
	public TruckInfo getByID(@PathVariable("id") String id) {
		
		if(id.isEmpty()) return null;
		
		Map<String, TruckInfo> map = dbwriter.getMapByLocationId();
		if(map.containsKey(id.toString())) {
			TruckInfo info = map.get(id);
			return info;
		}
		
		return null;
	}


	@GetMapping
	@RequestMapping(value = "/truckInfo/block/{block}",method = RequestMethod.GET)
	public List<TruckInfo> getByBlock(@PathVariable("block") String block) {

		if(block.isEmpty()) return null;
		
		List<TruckInfo> infoList= new ArrayList<TruckInfo>();
		Map<String, Set<String>> blockMap = dbwriter.getmapByBlock();
		Map<String, TruckInfo> locationMap = dbwriter.getMapByLocationId();
		
		if(blockMap.containsKey(block.toString())) {
			Set<String> locationIds = blockMap.get(block.toString());
			
			for(String id: locationIds) {
				TruckInfo info = locationMap.get(id.toString());
				infoList.add(info);
			}
			
		}
		
		return infoList;
	}
	

	
	@PostMapping
	@RequestMapping(value = "/truckInfo/add",method = RequestMethod.POST)
	public String addbyTruckInfo(@RequestBody TruckInfo info) {
		
		Map<String, TruckInfo> map = dbwriter.getMapByLocationId();
		
		if(!map.containsKey(info.getLocationid())) {
			map.put(info.getLocationid(), info);
		}
		return info.getLocation();
		
	}
	
}
