//package io.springbarchlecture.springbatch.sections3.part10;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//
//@RestController
//public class JobLauncherController {
//
//    @Autowired
//    private Job job;
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private DefaultBatchConfiguration defaultBatchConfiguration;
//
//    @PostMapping("/batch")
//    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addDate("date", new Date())
//                .toJobParameters();
////        JobLauncher jobLauncher1 = (SimpleJobLauncher)defaultBatchConfiguration.jobLauncher();
//        jobLauncher.run(job, jobParameters);
//
//        return "batch completed";
//    }
//}
