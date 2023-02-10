package it.test.nyc.domain.formatter;

public interface ErrorFormatter {

  String displayName(Throwable throwable);
}
