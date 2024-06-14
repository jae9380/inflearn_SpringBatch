//package io.springbarchlecture.springbatch.section4.part2.chap2.taskletStep;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class TaskletStepConfiguration {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager ptm;
//
//    @Bean
//    public Job batchJob() {
//        return new JobBuilder("batchJob", jobRepository)
////                .start(taskStep())
//                .start(chunkStep())
//                .build();
//    }
//
//    @Bean
//    public Step taskStep() {
//        return new StepBuilder("taskStep", jobRepository)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step1 was executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                },ptm)
//                .build();
//    }
//
//    @Bean
//    public Step chunkStep() {
//        return new StepBuilder("chunkStep",jobRepository)
//                .<String, String>chunk(10,ptm)
//                .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5")))
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        return item.toUpperCase();
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(Chunk<? extends String> chunks) throws Exception {
//                     chunks.forEach(System.out::println);
//                    }
//                })
//                .build();
//    }
//}
