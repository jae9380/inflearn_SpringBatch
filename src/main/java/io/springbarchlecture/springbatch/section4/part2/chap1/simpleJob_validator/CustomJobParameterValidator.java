//package io.springbarchlecture.springbatch.section4.part2.chap1.simpleJob_validator;
//
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.JobParametersValidator;
//
//public class CustomJobParameterValidator implements JobParametersValidator {
//    @Override
//    public void validate(JobParameters parameters) throws JobParametersInvalidException {
//        if (parameters.getString("name") == null) {
//            throw new JobParametersInvalidException("name parameters is not found");
//        }
//    }
//}
