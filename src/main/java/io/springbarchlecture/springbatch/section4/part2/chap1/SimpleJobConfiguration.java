//package io.springbarchlecture.springbatch.section4.part2.chap1;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
//public class SimpleJobConfiguration {
//
//    @Bean
//    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2, Step step3) {
//        return new JobBuilder("batchJob1",jobRepository)
//                .start(step1)
//                .next(step2)
//                .next(step3)
//                .incrementer(new RunIdIncrementer())
//                .validator(new JobParametersValidator() {
//                    @Override
//                    public void validate(JobParameters parameters) throws JobParametersInvalidException {
//
//                    }
//                })
//                .preventRestart()
//                .listener(new JobExecutionListener() {
//                    @Override
//                    public void beforeJob(JobExecution jobExecution) {
////                        JobExecutionListener.super.beforeJob(jobExecution);
//                    }
//
//                    @Override
//                    public void afterJob(JobExecution jobExecution) {
////                        JobExecutionListener.super.afterJob(jobExecution);
//                    }
//                })
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
//
//    @Bean
//    public Step step3(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("step3", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
//                    contribution.setExitStatus(ExitStatus.STOPPED);
////                    step1,2는 성공적으로 끝나지만
////                    배치상태는 실패, 종료코드는 STOPPED
//                    System.out.println("step3 was executed");
//                    return RepeatStatus.FINISHED;
//                },ptm)
//                .build();
//    }
//}
