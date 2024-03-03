package io.springbarchlecture.springbatch.sections3.part8;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet2 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step2 was executed");

        ExecutionContext jobEexecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();

        System.out.println("jobName : " + jobEexecutionContext.get("jobName"));
//        JobName 공유
        System.out.println("stepName : " + stepExecutionContext.get("stepName"));
//        각 각의 Step 공유 불가능

        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        if (stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName",stepName);
        }

        return RepeatStatus.FINISHED;
    }
}
