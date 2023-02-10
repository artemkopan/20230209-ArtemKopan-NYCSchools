package it.test.nyc.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StringUtils {

  private StringUtils() {
  }

  @NonNull
  public static String getOrEmpty(@Nullable String value) {
    return value == null ? "" : value;
  }
}
