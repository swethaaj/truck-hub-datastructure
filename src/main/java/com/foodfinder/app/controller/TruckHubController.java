package com.foodfinder.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	TruckInfoRepository truckInfoRepository;
	
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
		TruckInfo info = truckInfoRepository.findByLocationId(id);
		return info;	
	}


	@GetMapping
	@RequestMapping(value = "/truckInfo/block/{block}",method = RequestMethod.GET)
	public List<TruckInfo> getByBlock(@PathVariable("block") String block) {
		List<TruckInfo> infoList= truckInfoRepository.findByBlock(block);
		return infoList;	
	}
	
	@GetMapping
	@RequestMapping(value = "/truckInfo/locationIdAndType",method = RequestMethod.GET)
	public List<TruckInfo> getByLocationIdAndTruckType(@RequestBody String locationId, String type ) {
		List<TruckInfo> info = truckInfoRepository.findByLocationIdAndTruckType(locationId, type);
		return info;	
	}

	
	@PostMapping
	@RequestMapping(value = "/truckInfo/add",method = RequestMethod.POST)
	public String addbyTruckInfo(@RequestBody TruckInfo info) {
		TruckInfo infoList= truckInfoRepository.save(info);
		return infoList.getLocationid();	
	}
	
}
