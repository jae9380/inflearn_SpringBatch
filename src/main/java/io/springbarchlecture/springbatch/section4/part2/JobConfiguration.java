package io.springbarchlecture.springbatch.section4.part1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

//    @Bean
//    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2) {
//        return new JobBuilder("batchJob1",jobRepository)
//                .start(step1)
//                .next(step2)
//                .build();
//    }

    // Flow 실행
//    start할 때 인자가 Step이 아닌 Flow
    @Bean
    public Job batchJob2(JobRepository jobRepository, Step step5 ,Flow flow) {
        return new JobBuilder("batchJob2",jobRepository)
                .start(flow)
                .next(step5)
                .end()
                .build();
    }

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

    @Bean
    public Flow flow(Step step3, Step step4) {
        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flow");
        flowFlowBuilder.start(step3)
                .next(step4)
                .end();

        return flowFlowBuilder.build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step3", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step3 was executed");
                        return RepeatStatus.FINISHED;
                    }
                },ptm)
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step4", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step4 was executed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }

    @Bean
    public Step step5(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step5", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step5 was executed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }
}
