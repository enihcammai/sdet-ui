package com.simbirsoft.ui.helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CookieHelper {

    private static final File COOKIES_FILE = new File("cookies.ser");

    public static void saveCookies(WebDriver driver){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIES_FILE))) {
            oos.writeObject(new ArrayList<>(driver.manage().getCookies()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean loadCookies(WebDriver driver){
        if (!COOKIES_FILE.exists()) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COOKIES_FILE))) {
            List<Cookie> cookies = (List<Cookie>) ois.readObject();
            cookies.forEach(cookie -> {
                try {
                    driver.manage().addCookie(cookie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return !cookies.isEmpty();
        } catch (IOException | ClassNotFoundException e) {
            if (COOKIES_FILE.exists()) {
                COOKIES_FILE.delete();
            }
            return false;
        }
    }

    public static void deleteCookiesFile() {
        if (COOKIES_FILE.exists()) {
            COOKIES_FILE.delete();
        }
    }
}
