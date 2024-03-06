package io.springbarchlecture.springbatch.section4.part1;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
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
@RequiredArgsConstructor
public class JobConfiguration2 {

    @Bean
    public Job batchJob2(JobRepository jobRepository, Step step3, Step step4) {
        return new JobBuilder("batchJob22",jobRepository)
                .start(step3)
                .next(step4)
                .build();
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
}
