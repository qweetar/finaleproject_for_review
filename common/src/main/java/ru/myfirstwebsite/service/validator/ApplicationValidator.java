package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.Application;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationValidator implements ValidatorInterface<Application> {

    private static final ValidatorInterface<Application> instance = new ApplicationValidator();

    private ApplicationValidator(){}

    public static ValidatorInterface<Application> getInstance(){
        return instance;
    }

    private static final String REGEX_APPLICATION_ID = "\\d{1,10}";
    private static final String REGEX_ROOM_CLASS = "[A-D]{1}";
    private static final String REGEX_ROOM_SIZE = "[1-4]{1}";
    private static final String REGEX_DATE = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"; //"^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$";
    private static final String REGEX_USER_ID = "\\d{1,10}";
    private static final String REGEX_ROOM_ID = "\\d{1,10}";

    private static final Pattern patternApplicationId = Pattern.compile(REGEX_APPLICATION_ID);
    private static final Pattern patternRoomClass = Pattern.compile(REGEX_ROOM_CLASS);
    private static final Pattern patternRoomSize = Pattern.compile(REGEX_ROOM_SIZE);
    private static final Pattern patterDate = Pattern.compile(REGEX_DATE);
    private static final Pattern patternUserId = Pattern.compile(REGEX_USER_ID);
    private static final Pattern patternRoomId = Pattern.compile(REGEX_ROOM_ID);

    @Override
    public boolean isValid(Application application) {

        String applicationId = String.valueOf(application.getApplicationId());
        String roomClass = String.valueOf(application.getRoomClass());
        String roomSize = String.valueOf(application.getRoomSize());
        String dateFrom = String.valueOf(application.getDateFrom());
        String dateTo = String.valueOf(application.getDateTo());
        String userId = String.valueOf(application.getUserId());
        //String roomId = String.valueOf(application.getRoomId());

        Matcher matcherApplicationId = patternApplicationId.matcher(applicationId);
        Matcher matcherRoomClass = patternRoomClass.matcher(roomClass);
        Matcher matcherRoomSize = patternRoomSize.matcher(roomSize);
        Matcher matcherDateFrom = patterDate.matcher(dateFrom);
        Matcher matcherDateTo = patterDate.matcher(dateTo);
        Matcher matcherUserId = patternUserId.matcher(userId);
        //Matcher matcherRoomId = patternRoomId.matcher(roomId);

        Date dFrom = application.getDateFrom();
        Date dTo = application.getDateTo();

        if (getDateDiff(dFrom,dTo,TimeUnit.HOURS) < 24) {
            return false;
        }

        return matcherApplicationId.matches() &
                matcherRoomClass.matches() &
                matcherRoomSize.matches() &
                matcherDateFrom.matches() &
                matcherDateTo.matches() &
                matcherUserId.matches();
                //matcherRoomId.matches();
    }

    /**
     * Get a diff between two dates
     * @param dateFrom the oldest date
     * @param dateTo the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date dateFrom, Date dateTo, TimeUnit timeUnit) {
        long diffInHours = dateTo.getTime() - dateFrom.getTime();
        return timeUnit.convert(diffInHours,TimeUnit.HOURS);
    }


}
