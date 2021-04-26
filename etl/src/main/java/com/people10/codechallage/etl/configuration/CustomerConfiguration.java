package com.people10.codechallage.etl.configuration;

import com.people10.codechallage.etl.model.Customer;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class CustomerConfiguration {

    @Autowired
    private ItemReader<Customer> itemReader1;

    @Autowired
    private ItemReader<Customer> itemReader2;


    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemProcessor<Customer, Customer> itemProcessor,
                   ItemWriter<Customer> itemWriter) {

        Step step1 = stepBuilderFactory.get("LOAD-DATA1").<Customer, Customer>chunk(100).reader(itemReader1)
                .processor(itemProcessor).writer(itemWriter).build();
        Step step2 = stepBuilderFactory.get("LOAD-DATA2").<Customer, Customer>chunk(100).reader(itemReader2)
                .processor(itemProcessor).writer(itemWriter).build();
        return jobBuilderFactory.get("CUSTOMER-ETL").incrementer(new RunIdIncrementer()).start(step1).next(step2).build();
    }

    @Bean
    public LineMapper<Customer> lineMapperData1Csv() {
        return getLineMapperFactory("created_at", "first_name", "last_name", "email", "latitude", "longitude", "ip" );
    }

    @Bean
    public LineMapper<Customer> lineMapperData2Csv() {
        return getLineMapperFactory("created_at","ip","atitude","longitude","first_name","last_name","email");
    }

    private LineMapper<Customer> getLineMapperFactory(String... str) {
        DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(str);

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public FlatFileItemReader<Customer> itemReader1() {
        return getFlatFileItemReaderFactory(new ClassPathResource("data1.csv"), lineMapperData1Csv());
    }

    @Bean
    public FlatFileItemReader<Customer> itemReader2() {
        return getFlatFileItemReaderFactory(new ClassPathResource("data2.csv"), lineMapperData2Csv());
    }



    private FlatFileItemReader<Customer> getFlatFileItemReaderFactory(ClassPathResource classPathResource, LineMapper<Customer> lineMapper) {
        FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(classPathResource);
        flatFileItemReader.setName("CSV-READER");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }
}
