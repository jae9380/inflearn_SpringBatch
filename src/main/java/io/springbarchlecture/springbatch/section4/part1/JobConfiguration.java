//package io.springbarchlecture.springbatch.section4.part1;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class JobConfiguration {
//
//    @Bean
//    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2) {
//        return new JobBuilder("batchJob11",jobRepository)
//                .start(step1)
//                .next(step2)
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("step1", jobRepository)
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
//    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 was executed");
//                    return RepeatStatus.FINISHED;
//                },ptm)
//                .build();
//    }
//}
