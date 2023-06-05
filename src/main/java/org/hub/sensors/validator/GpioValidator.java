package org.hub.sensors.validator;

import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.Sensor;
import org.hub.sensors.service.GpioPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

@Component
public class GpioValidator implements Validator {

    @Autowired
    private GpioPinService gpioPinService;



    @Override
    public boolean supports(Class<?> aClass) {
        return GpioPin.class.equals(aClass);
    }

    @Override
    public void validate(Object o,  Errors errors) {
        GpioPin gpioPin = (GpioPin) o;

        ValidationUtils.rejectIfEmpty(errors,"gpio", "NotEmpty");


    }


}
