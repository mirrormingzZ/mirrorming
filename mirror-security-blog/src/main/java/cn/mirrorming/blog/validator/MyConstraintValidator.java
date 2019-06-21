package cn.mirrorming.blog.validator;

import cn.mirrorming.blog.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @author: mirrorming
 * @create: 2019-06-16 14:58
 **/
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    private TestService testService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        log.info("init MyConstraintValidator!s");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        testService.testMessage("MyConstraintValidator");
        return false;
    }
}