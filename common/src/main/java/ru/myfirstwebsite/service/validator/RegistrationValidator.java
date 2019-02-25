package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationValidator implements ValidatorInterface<User> {

    private static final ValidatorInterface<User> instance = new RegistrationValidator();

    private RegistrationValidator(){}

    public static ValidatorInterface<User> getInstance(){
        return instance;
    }

    private static final String REGEX_LOGIN = "[а-яА-ЯёЁa-zA-Z]{2,25}";
    private static final String REGEX_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private static final String REGEX_NAME = "[а-яА-ЯёЁa-zA-Z]{2,25}";
    private static final String REGEX_SURNAME = "[а-яА-ЯёЁa-zA-Z]{2,25}";
    private static final String REGEX_EMAIL = "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";

    private static final Pattern patternLogin = Pattern.compile(REGEX_LOGIN);
    private static final Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
    private static final Pattern patternName = Pattern.compile(REGEX_NAME);
    private static final Pattern patternSurname = Pattern.compile(REGEX_SURNAME);
    private static final Pattern patternEmail = Pattern.compile(REGEX_EMAIL);

    @Override
    public boolean isValid(User user) {
        String name = user.getUserName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String login = user.getLogin();
        String password = user.getPassword();

        Matcher matcherLogin = patternLogin.matcher(login);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherName = patternName.matcher(name);
        Matcher matcherSurname = patternSurname.matcher(surname);
        Matcher matcherEmail = patternEmail.matcher(email);

        return matcherLogin.matches() &
                matcherPassword.matches() &
                matcherName.matches() &
                matcherSurname.matches() &
                matcherEmail.matches();
    }
}
