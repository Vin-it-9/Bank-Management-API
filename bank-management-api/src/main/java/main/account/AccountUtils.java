package main.account;

import java.util.Random;

public class AccountUtils {

    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ACCOUNT_NUMBER_LENGTH = 8;

    public static String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int index = random.nextInt(ALPHANUMERIC_CHARACTERS.length());
            accountNumber.append(ALPHANUMERIC_CHARACTERS.charAt(index));
        }
        return accountNumber.toString();
    }

}
