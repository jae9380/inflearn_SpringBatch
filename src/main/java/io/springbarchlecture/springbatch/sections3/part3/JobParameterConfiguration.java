package io.springbarchlecture.springbatch.sections3.part3;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration
public class JobParameterConfiguration {

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

//                  contribution 을 이용한 방식
                    JobParameters jobParameters =
                            contribution.getStepExecution().getJobExecution().getJobParameters(); // JobParameters 값 얻기 (아래 설명)
                    jobParameters.getString("name");
                    jobParameters.getLong("seq");
                    jobParameters.getDate("date");
                    jobParameters.getDouble("age");
                    
//                  chunkContext 을 이용한 방식
//                  위 방법과 달리 chunkContext 방법은 Map 형태로 반환 받는다.
//                  이 방법은 해당 시점의 값을 받아서 바로 반환해주는 방법이 아닌 Map으로 만들어 준다.

                    Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

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

//    StepContribution, ChunkContext 두개의 클래스에서 JobParameter 의 값을 참조할 수 있다.
//    StepContribution 클래스는 StepExecution 클래스를 참조하고 있고 해당 클래스 안에 JobExecution 있는데
//    JobExecution 내부에 JobParameters 있기 때문에 참조가 가능하다.
//    ChunkContext 또한 내부에 StepContext 가 있고 StepContext 내부에 StepExecution 있기 때문에 JobParameters 참조가 가능하다

//    jar 파일을 만들고 실행 시킬 때 인자를 주어지게 된다면
//    스프링 배치가 값을 받아서 설정돠 동일한 구성을 만들어 Job 을 실행 시킨다
//    $ java -jar springBatch-0.0.1-SNAPSHOT.jar name=user1 seq=2L date=2021/01/01 age=16.5
//    위 형태로 명령어를 날리니 모두 다 String 타입으로 저장되는 문제가 생겼다.
//    $ java -jar springBatch-0.0.1-SNAPSHOT.jar name=user1 seq\(long\)=2L date\(date\)=2021/01/01 age\(double\)=16.5
//    이렇게 수정하여 명령어를 날리니 의도한 대로 저장이 되었다.

//    또한 스프링 부트 설정에 값을 전달하는 방법도 있다.
}
