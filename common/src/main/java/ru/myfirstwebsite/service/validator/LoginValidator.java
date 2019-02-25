package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Validator for User entity.
        * Checks the correctness of User object fields using regular expressions.
        * Regular expression for login "[а-яА-ЯёЁa-zA-Z]{2,25}" .
        * Regular expression for password "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$".
        */
public class LoginValidator implements ValidatorInterface<User> {

    private static final ValidatorInterface<User> instance = new LoginValidator();

    private LoginValidator(){}

    public static ValidatorInterface<User> getInstance(){
        return instance;
    }

    private static final String REGULAR_EXP_LOGIN = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGULAR_EXP_PASSWORD = "[а-яА-Яa-zA-Z0-9]{2,25}";

    private static final Pattern patternLogin = Pattern.compile(REGULAR_EXP_LOGIN);
    private static final Pattern patternPassword = Pattern.compile(REGULAR_EXP_PASSWORD);

            /** Validates fields of parameter object for correct values.
            *
            * @param user User object which is need to be validated for correctness fields.
            * @return true if validation fields of object was successfully: login contains letters of the Russian and English
            * and no longer than 25 characters,
            * password contains uppercase and lowercase letters of the English language and figures;
            * else false
             */
    @Override
    public boolean isValid(User user) {
        String login = String.valueOf(user.getLogin());
        String password = String.valueOf(user.getPassword());

        Matcher matcherLogin = patternLogin.matcher(login);
        Matcher matcherPassword = patternPassword.matcher(password);

        return matcherLogin.matches() &
                matcherPassword.matches();
    }
}
