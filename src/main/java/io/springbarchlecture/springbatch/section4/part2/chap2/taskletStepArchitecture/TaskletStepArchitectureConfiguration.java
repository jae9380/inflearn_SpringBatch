package io.springbarchlecture.springbatch.section4.part2.chap2.taskletStepArchitecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TaskletStepArchitectureConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager ptm;

    @Bean
    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("batchJob1",jobRepository)
                .start(step1)
                .next(step2)
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        StepExecutionListener.super.beforeStep(stepExecution);
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return StepExecutionListener.super.afterStep(stepExecution);
                    }
                })
                .build();
    }

    /*
        심플잡이 스탭들을 반복적으로 호출한다

     */

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step1", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContribution = "+ contribution+ "chunkContext = "+chunkContext);
                        return RepeatStatus.FINISHED;
                    }
                },ptm)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("step2", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("StepContribution = "+ contribution+ "ChunkContext = "+chunkContext);
                        return RepeatStatus.FINISHED;
                    }
                },ptm)
                .build();
    }
}

}
