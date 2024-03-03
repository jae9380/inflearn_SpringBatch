package io.springbarchlecture.springbatch.sections3.part8;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ExecutionContextConfiguration {

    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;

    @Bean
    public Job job(JobRepository jobRepository, Step step1, Step step2, Step step3, Step step4) {
        return new JobBuilder("job",jobRepository)
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step1",jobRepository)
                .tasklet(executionContextTasklet1,ptm)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step2",jobRepository)
                .tasklet(executionContextTasklet2,ptm)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step3",jobRepository)
                .tasklet(executionContextTasklet3,ptm)
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step4",jobRepository)
                .tasklet(executionContextTasklet4,ptm)
                .build();
    }


}
