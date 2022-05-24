package io.wollinger.ratpack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Utils {
    private static RatPack plugin;

    public static void init(RatPack plugin) {
        Utils.plugin = plugin;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(Exception exception) {
            return false;
        }
    }

    public static String loadFileAsString(String filename) {
        File dataFolder = plugin.getDataFolder();
        File fileToLoad = new File(dataFolder.getAbsolutePath() + File.separator + filename);
        try {
            return new String(Files.readAllBytes(fileToLoad.toPath()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
