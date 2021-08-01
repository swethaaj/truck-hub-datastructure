package com.foodfinder.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.foodfinder.app.model.TruckInfo;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig{

	@Bean
	public Job job(JobBuilderFactory jobBuildFactory, StepBuilderFactory stepBuilderFactory,
					ItemReader<TruckInfo> itemReader,
					ItemProcessor<TruckInfo, TruckInfo> itemProcessor,
					ItemWriter<TruckInfo> itemWriter) {
		
		//processor, reader, writer - so we need step builder factory
		Step step = stepBuilderFactory.get("ETL-File-Loader")
				.<TruckInfo, TruckInfo>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build(); 
		
		return jobBuildFactory.get("ETL-Load")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();	
	}
	
	@Bean
	public FlatFileItemReader<TruckInfo> fileItemReader(@Value("${input}")Resource resource){
		// reader to read from csv file
		FlatFileItemReader<TruckInfo> flatFileItemReader = new FlatFileItemReader<TruckInfo>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-READER");
		flatFileItemReader.setLinesToSkip(1); //skip header
		flatFileItemReader.setLineMapper(lineMapper());// map csv records to User class
	
		return flatFileItemReader;
		
	}
	
	@Bean
	public LineMapper<TruckInfo> lineMapper() {
		// mapper to map the csv records to user object
		DefaultLineMapper<TruckInfo> defaultLineMapper = new DefaultLineMapper<TruckInfo>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[]{"locationid", "Applicant", "FacilityType", 
				"LocationDescription", "Address", "blocklot", "block", "lot", "permit", "Status", "FoodItems", "Location", "ZipCodes"});
		
		//this basically maps each column in the csv file to entity object
		BeanWrapperFieldSetMapper<TruckInfo> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(TruckInfo.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldMapper);
		
		return defaultLineMapper;
	}

	
}
