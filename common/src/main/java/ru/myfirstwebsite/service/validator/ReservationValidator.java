package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.Reservation;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationValidator implements ValidatorInterface<Reservation> {

    private static final ValidatorInterface<Reservation> instance = new ReservationValidator();

    private ReservationValidator(){}

    public static ValidatorInterface<Reservation> getInstance(){
        return instance;
    }

    private static final String REGEX_ID = "\\d{1,10}";
    private static final String REGEX_DATE = "^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$";

    private static final Pattern patternId = Pattern.compile(REGEX_ID);;
    private static final Pattern patterDate = Pattern.compile(REGEX_DATE);

    @Override
    public boolean isValid(Reservation reservation) {
        String reservationId = String.valueOf(reservation.getReservationId());
        String roomId = String.valueOf(reservation.getRoomId());
        String dateFrom = String.valueOf(reservation.getDateFrom());
        String dateTo = String.valueOf(reservation.getDateTo());

        Matcher matcherReservationId = patternId.matcher(reservationId);
        Matcher matcherRoomId = patternId.matcher(roomId);
        Matcher matcherDateFrom = patterDate.matcher(dateFrom);
        Matcher matcherDateTo = patterDate.matcher(dateTo);

        Date dFrom = reservation.getDateFrom();
        Date dTo = reservation.getDateTo();

        if (getDateDiff(dFrom,dTo, TimeUnit.DAYS) < 24) {
            return false;
        }

        return matcherReservationId.matches() &
                matcherRoomId.matches() &
                matcherDateFrom.matches() &
                matcherDateTo.matches();
    }

    /**
     * Get a diff between two dates
     * @param dateFrom the oldest date
     * @param dateTo the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date dateFrom, Date dateTo, TimeUnit timeUnit) {
        long diffInHours = dateFrom.getTime() - dateTo.getTime();
        return timeUnit.convert(diffInHours,TimeUnit.HOURS);
    }
}
