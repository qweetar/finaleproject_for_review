package ru.myfirstwebsite.service.validator;

import ru.myfirstwebsite.domain.to.Bill;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillValidator implements ValidatorInterface<Bill> {

    private static final ValidatorInterface<Bill> instance = new BillValidator();

    private BillValidator(){}

    public static ValidatorInterface<Bill> getInstance(){
        return instance;
    }

    private static final String REGEX_ID = "\\d{1,10}";
    private static final String REGEX_PRICE = "\\d+(\\.\\d{0,2})?";

    private static final Pattern patternId = Pattern.compile(REGEX_ID);
    private static final Pattern patternPrice = Pattern.compile(REGEX_PRICE);

    @Override
    public boolean isValid(Bill bill) {


        String billId = String.valueOf(bill.getBillId());
        String price = String.valueOf(bill.getPrice());
        String applicationId = String.valueOf(bill.getApplicationId());
        String userId = String.valueOf(bill.getUserId());

        Matcher matcherBillId = patternId.matcher(billId);
        Matcher matcherPrice = patternPrice.matcher(price);
        Matcher matcherApplicationId = patternId.matcher(applicationId);
        Matcher matcherUserId = patternId.matcher(userId);

        return matcherBillId.matches() &
                matcherPrice.matches() &
                matcherApplicationId.matches() &
                matcherUserId.matches();
    }
}
