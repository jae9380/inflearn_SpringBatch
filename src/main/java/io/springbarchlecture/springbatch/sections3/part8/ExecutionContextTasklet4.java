//package io.springbarchlecture.springbatch.sections3.part8;
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ExecutionContextTasklet4 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
////        전 단계에서 잡 익스큐션 컨텍스트에 저장한 네임이라는 키를 갖고와서 출력
//        System.out.println("name : "+ chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name"));
//        System.out.println("step4 was executed");
//
//        return RepeatStatus.FINISHED;
//    }
//}