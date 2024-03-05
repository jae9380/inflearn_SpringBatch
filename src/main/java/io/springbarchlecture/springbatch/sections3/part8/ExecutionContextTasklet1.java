//package io.springbarchlecture.springbatch.sections3.part8;
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ExecutionContextTasklet1 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        System.out.println("step1 was executed");
//
////        스탭 익스큐선에서 익스큐선 컨텍스트를 뽑아내고,
////        스탭 익스큐션 안에 있는 잡 익스큐션을 뽑아서
////        잡 익스큐션에 있는 컨텍스트도 뽑아 보겠다.
//
//        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
//        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();
//
////        잡 익스큐션 컨텍스트 안 잡 네임을 저장
////        스탭 익스큐션 컨텍스트 네임을 저장
//
////        contribution, chunkContext 둘 중 하나를 사용해도 각각의 익스큐션은 동일하게 참조가 가능하다.
//
//        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
//        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();
//
//        if (jobExecutionContext.get("jobName") == null) {
//            jobExecutionContext.put("jobName",jobName);
//        }
//
//        if (stepExecutionContext.get("stepName") == null) {
//            stepExecutionContext.put("stepName",stepName);
//        }
//
//        System.out.println("jobName : " + jobExecutionContext.get("jobName"));
//        System.out.println("stepName : " + stepExecutionContext.get("stepName"));
//
//        return RepeatStatus.FINISHED;
//    }
//}
