package com.spring.printer.validator;

import com.spring.printer.model.User;
import com.spring.printer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
// @Component (ir @Service ir @Repository) naudojami automatiškai aptikti ir automatiškai konfigūruoti beans naudojant klasės kelio nuskaitymą.
// Tai reiškia, kad mums nereikia apsirašyti, šiuo atveju, @Bean UserService @Configuration SpringConfig klasėje.
// @Component automatiškai aptinka ir sukonfigūruoja beans naudodamas klasės kelio nuskaitymą,
// kai tuo tarpu @Bean anotacija aiškiai deklaruoja tik vieną bean, o ne leidžia Spring tai padaryti automatiškai.
// @Component yra klasės lygio anotacija, o @Bean yra metodo lygio anotacija kur metodo pavadinimas naudojamas kaip bean pavadinimas.
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        // Validacijos priemonių klasė (tuščių simbolių validavimui)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
