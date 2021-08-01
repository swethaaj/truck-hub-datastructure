package com.foodfinder.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.foodfinder.app.model.TruckInfo;

@Transactional(readOnly = true) 
public interface TruckInfoRepository extends JpaRepository<TruckInfo, Integer>{

	@Query(value = "select * from truck_info info where info.locationid = ?1", nativeQuery = true)
	TruckInfo findByLocationId(String LocationId); 

	@Query(value = "select * from truck_info info where info.block = ?1", nativeQuery = true)
	List<TruckInfo> findByBlock(String Block); 
	
//	Enhancement feature if needed later on....
	@Query(value = "select * from truck_info info where info.locationid = ?1 and info.facility_type = ?2", nativeQuery = true)
	List<TruckInfo> findByLocationIdAndTruckType(String LocationId, String type); 
	
}
