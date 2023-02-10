package it.test.nyc.data.formatter;

import android.content.Context;
import androidx.annotation.NonNull;
import dagger.hilt.android.qualifiers.ApplicationContext;
import it.test.nyc.R;
import it.test.nyc.domain.formatter.ErrorFormatter;

import java.net.UnknownHostException;

import javax.inject.Inject;

/**
 * Formats throwable to readable name.
 */
public class ErrorFormatterImpl implements ErrorFormatter {

  @NonNull
  private final Context context;

  @Inject
  public ErrorFormatterImpl(@ApplicationContext @NonNull final Context context) {
    this.context = context;
  }

  @Override
  public String displayName(final Throwable throwable) {
    final String message = throwable.getMessage();
    //fixme add checking type and show user friendly error for some errors.
    if (throwable instanceof UnknownHostException) {
      return context.getString(R.string.error_no_internet_connection);
    }
    return message == null || message.isEmpty()
        ? context.getString(R.string.error_unknown)
        : message;
  }
}
