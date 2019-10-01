package com.le2t.prod.user.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

  private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
          Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  private static int MAX_LENGTH_VALUE = 120;

  public static boolean checkSingleInfo(String value, String type) {
    if ("email".equals(type)) {
      CharSequence input;
      Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
      return matcher.find();
    }

    return value.length() < MAX_LENGTH_VALUE;
  }

  public static boolean valueNotEmpty(String value) {
    return (value != null && !value.trim().isEmpty());
  }
}
