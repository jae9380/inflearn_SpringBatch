//package io.springbarchlecture.springbatch.sections3.part10;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class JobLauncherConfiguration {
//
//    @Bean
//    public Job job(JobRepository jobRepository, Step step1, Step step2) {
//        return new JobBuilder("Job", jobRepository)
//                .start(step1)
//                .next(step2)
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("Step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 was executed");
//                    return RepeatStatus.FINISHED;
//                }, ptm)
//                .build();
//    }
//
//    @Bean
//    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("Step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> null, ptm)
//                .build();
//    }
//
////    동기적 비봉기적 실행을 위해서 spring boot가 자동 실행이 되어서는 안된다.
////
//}
