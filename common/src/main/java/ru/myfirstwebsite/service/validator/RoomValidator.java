package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.Room;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomValidator implements ValidatorInterface<Room> {

    private static final ValidatorInterface<Room> instance = new RoomValidator();

    private RoomValidator(){}

    public static ValidatorInterface<Room> getInstance(){
        return instance;
    }

    private static final String REGEX_ROOM_ID = "\\d{1,4}";
    private static final String REGEX_PRICE_PER_DAY = "\\d+(\\.\\d{0,2})?";
    private static final String REGEX_ROOM_CLASS = "[A-D]{1}";
    private static final String REGEX_ROOM_SIZE = "[1-4]{1}";
    private static final String REGEX_ROOM_STATUS = "[а-яА-Яa-zA-Z]{2,25}";

    private static final Pattern patternRoomId = Pattern.compile(REGEX_ROOM_ID);
    private static final Pattern patternPricePerDay = Pattern.compile(REGEX_PRICE_PER_DAY);
    private static final Pattern patternRoomClass = Pattern.compile(REGEX_ROOM_CLASS);
    private static final Pattern patternSize = Pattern.compile(REGEX_ROOM_SIZE);
    private static final Pattern patternRoomStatus = Pattern.compile(REGEX_ROOM_STATUS);

    @Override
    public boolean isValid(Room room) {

        String roomId = String.valueOf(room.getRoomId());
        String pricePerDay = String.valueOf(room.getRoomPricePerDay());
        String roomClass = String.valueOf(room.getRoomClass());
        String roomSize = String.valueOf(room.getRoomSize());
        String roomStatus = String.valueOf(room.getRoomStatus());

        Matcher matcherRoomId = patternRoomId.matcher(roomId);
        Matcher matcherPricePerDay = patternPricePerDay.matcher(pricePerDay);
        Matcher matcherRoomClass = patternRoomClass.matcher(roomClass);
        Matcher matcherRoomSize = patternSize.matcher(roomSize);
        Matcher matcherRoomStatus = patternRoomStatus.matcher(roomStatus);

        return matcherRoomId.matches() &
                matcherPricePerDay.matches() &
                matcherRoomClass.matches() &
                matcherRoomSize.matches() &
                matcherRoomStatus.matches();
    }
}
