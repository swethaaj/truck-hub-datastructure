package com.foodfinder.app.config;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodfinder.app.model.TruckInfo;



@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class SpringBatchConfigTest {

	@org.mockito.InjectMocks
	private SpringBatchConfig impl;
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	ItemReader itemReader;
	
	@Autowired
	ItemProcessor itemProcessor;
	
	@Autowired
	ItemWriter itemWriter;
	
	private static final String EXPECTED_FILE = "C:/Users/Swetha/eclipse-workspace/take-home-engineering-challenge-boot/src/main/resources/user.csv";

	
	@Test
	void jobTest() {
		Job res = impl.job(jobBuilderFactory, stepBuilderFactory, itemReader, itemProcessor, itemWriter);
		assertNotNull(res);
		
	}
	
	@Test
	void fileItemReaderTest() {
		FlatFileItemReader<TruckInfo> itemReader = impl.fileItemReader(new FileSystemResource(EXPECTED_FILE));
		assertNotNull(itemReader);
	}
	
	
	@Test
	void lineMapperTest() {
		LineMapper<TruckInfo> mapper = impl.lineMapper();
		assertNotNull(mapper);
	}
	


}
