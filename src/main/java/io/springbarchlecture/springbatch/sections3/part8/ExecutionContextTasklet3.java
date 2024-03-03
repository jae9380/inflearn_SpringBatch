package io.springbarchlecture.springbatch.sections3.part8;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step3 was executed");

//        일종의 예외를 발생 시킬 계획
//        예외가 발생되면 다음 단계는 실행이 안된다.
//        그렇게 되면 잡은 전체적인 실패로 다시 실행이 가능하다
//        잡을 재시작 했을 때, 실패한 지점의 데이터를 갖고 올 수 있는지
//        갖고온다고 한다면 실패한 데이터를 재 시작 했을 경우 성공적으로 이루어진다고 했을 때
//        실패 데이터를 재사용이 가능한지 테스트

        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");
//         스탭간의 전체 공유를 하기위해서는 잡 익스큐션 안에 있는 컨텍스트를 활용해야 한다.
        if (name == null) {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name","user1");
            throw new RuntimeException("step3 was failed");
        }

        return RepeatStatus.FINISHED;
    }
}
