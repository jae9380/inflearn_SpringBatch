package io.springbarchlecture.springbatch.sections3.part5;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//      구현하고자 하는 비지니스 로직을 구성하면 된다.
        System.out.println("step1 was executed");

        return RepeatStatus.FINISHED;
    }
}
