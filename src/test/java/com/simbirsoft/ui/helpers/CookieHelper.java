package com.simbirsoft.ui.helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CookieHelper {

    private static final File COOKIES_FILE = new File("cookies.ser");

    public static void saveCookies(WebDriver driver) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIES_FILE))) {
            oos.writeObject(new ArrayList<>(driver.manage().getCookies()));
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean loadCookies(WebDriver driver) throws IOException {
        if (!COOKIES_FILE.exists()) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COOKIES_FILE))) {
            List<Cookie> cookies = (List<Cookie>) ois.readObject();
            cookies.forEach(cookie -> driver.manage().addCookie(cookie));
            return !cookies.isEmpty();
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to deserialize cookies", e);
        }
    }
}
