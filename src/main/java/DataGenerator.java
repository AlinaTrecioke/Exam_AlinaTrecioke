import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataGenerator {

    static String[] names = {
            "Olivia", "Liam", "Emma", "Noah", "Ava",
            "Sophia", "Jackson", "Isabella", "Aiden", "Mia",
            "Lucas", "Amelia", "Ethan", "Harper", "Mason",
            "Evelyn", "Logan", "Abigail", "James", "Charlotte"
    };


    private static String storedUserName;
    private static String storedPassword;

    private static String storedShortPassword;

    private static String storedLongPassword;


    public static String getRandomUserName() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(names.length);
        storedUserName = names[randomIndex] + " " + RandomStringUtils.randomAlphanumeric(7);
        return storedUserName;
    }

    public static String getStoredUserName() {
        return storedUserName;
    }

    public static String getRandomPassword() {
        storedPassword = RandomStringUtils.randomAlphanumeric(8) + "Aa1*";
        return storedPassword;
    }

    public static String getStoredPassword() {
        return storedPassword;
    }

    public static String getRandomPasswordOf2Symbols() {
        storedShortPassword = RandomStringUtils.randomAlphanumeric(2);
        return storedShortPassword;
    }

    public static String getStoredShortPassword() {
        return storedShortPassword;
    }


    public static String getRandomPasswordOf33Symbols() {
        storedLongPassword = RandomStringUtils.randomAlphanumeric(33);
        return storedLongPassword;
    }

    public static String getStoredLongPassword() {
        return storedLongPassword;
    }


}
