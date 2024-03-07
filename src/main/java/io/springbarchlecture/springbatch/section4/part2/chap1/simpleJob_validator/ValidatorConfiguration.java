package io.springbarchlecture.springbatch.section4.part2.chap1.simpleJob_validator;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ValidatorConfiguration {
    @Bean
    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2, Step step3) {
        return new JobBuilder("batchJob1", jobRepository)
//                .validator(new CustomJobParameterValidator())
                .validator(new DefaultJobParametersValidator(new String[]{"name","date"},new String[]{"count"}))
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step1", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step1 was executed");
                        return RepeatStatus.FINISHED;
                    }
                },ptm)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 was executed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step3 was executed");
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }
}
