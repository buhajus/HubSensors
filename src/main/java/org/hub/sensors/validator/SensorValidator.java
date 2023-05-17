package org.hub.sensors.validator;

import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.User;
import org.hub.sensors.service.SensorService;
import org.hub.sensors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SensorValidator  implements Validator {

    @Autowired
    private SensorService sensorService;

     @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }
     @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;
//TODO:: validacijos
        // Validacijos priemonių klasė (tuščių simbolių validavimui)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sensorName", "NotEmpty");
      if (sensor.getSensorName().length() < 3 || sensor.getSensorName().length() > 32) {
            errors.rejectValue("sensorName", "Size.userForm.username");
        }
//        if (sensor.getSensorName().equals("")) {
//            errors.rejectValue("sensorName", "NotEmpty");
//        }
//
//
//
//        if (!user.getPasswordConfirm().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
    }


}
