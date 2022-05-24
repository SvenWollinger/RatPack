package io.wollinger.ratpack;

public class Utils {
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(Exception exception) {
            return false;
        }
    }
}
