package io.springbarchlecture.springbatch.sections3.part2;

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
public class JobInstanceConfiguration {

//    지금 구성한 Job과 JobParameters의 값이 동일 할 경우와 동일하지 않을 경우 테스트

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
                    return RepeatStatus.FINISHED;
                },ptm)
                .build();
    }

//    최초 실핼 시
//    batch_job_instance, batch_job_execution_params 테이블에 해당 정보가 잘 저장이 되었다.

//    동일한 내용으로 재 실핼 시
//     A job instance already exists and
//     is complete for identifying parameters={'name':'{value=user1, type=class java.lang.String, identifying=true}'}.
//     If you want to run this job again, change the parameters.
//    위 내용처럼 한 줄로 오류에 대한 메세지와 함께 오류를 발생 시킨다.

//    기존 user1 -> user2로 변경하여 실행
//    batch_job_instance 테이블 조회 시 Job_Key 값이 다른 2개의 데이터가 저장
//    batch_job_execution_params 테이블에는 user2에 대한 데이터 저장

}
