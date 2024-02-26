package io.springbarchlecture.springbatch.sections3.part4;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class JobExecutionConfiguration {
    @Bean
    public Job job(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("job",jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("ste1",jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 was executed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("ste2",jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 was executed");
//                    throw new RuntimeException("step2 has failed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }
//          JobExecution 최종적인 status가 성공을 끝나면 Job 실행이 불가능하고
//          실패로 끝날 경우 계속적인 실행이 되고 실행될 때 마다 JobExecution 생성되고 테이블에 저장이 된다.
}
