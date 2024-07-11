//package io.springbarchlecture.springbatch.section4.part2.chap2.taskletStep.Limit_Allow;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@RequiredArgsConstructor
//@Configuration
//public class Limit_AllowConfiguration {
//
//    @Bean
//    public Job batchJob1(JobRepository jobRepository, Step step1, Step step2) {
//        return new JobBuilder("batchJob1",jobRepository)
//                .start(step1)
//                .next(step2)
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("stepContribution = "+ contribution+ "chunkContext = "+chunkContext);
//                        return RepeatStatus.FINISHED;
//                    }
//                },ptm)
//                .allowStartIfComplete(true)
//                /*
//                 원래는 해당 스탭은 성공했으므로 다음 실행에는 실행되지 않는데, allowStartIfComplete 설정으로
//                 step1은 프로그램 실행 시 성공 여부와 상관없이 항상 실행되도록 설정을 함
//                 */
//                .build();
//    }
//
//    @Bean
//    public Step step2(JobRepository jobRepository, PlatformTransactionManager ptm) {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("StepContribution = "+ contribution+ "ChunkContext = "+chunkContext);
//                        throw new RuntimeException("step2 was failed");
////                        return RepeatStatus.FINISHED;
//                    }
//                },ptm)
//                .startLimit(3) // 3번째 까지는 실행이 가능, 4번째 부터는 불가능
//                .build();
//    }
//}
///*
//    위 step1 에서의 .allowStartIfComplete(true) 설정과  step2 에서의 .startLimit(3)설정으로
//    step1은 항상 프로그램 실행 시 성공한 이력이 있더라도 항상 실행이 되지만, step2는 3번까지는 실행이 가능하고 그 이상의 실행에서는
//    실행불가능한 스탭이 된다.
//
//    총 4번의 실행을 한다면 step1은 4번까지 실행이 되겠지만, step2는 3번까지만 실행이 된다.
// */