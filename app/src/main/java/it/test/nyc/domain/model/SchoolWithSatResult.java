package it.test.nyc.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class SchoolWithSatResult {

  @NonNull
  private final School school;
  @Nullable
  private final SatResult satResult;

  public SchoolWithSatResult(@NonNull final School school,
      @Nullable final SatResult satResult) {
    this.school = school;
    this.satResult = satResult;
  }

  @NonNull
  public School getSchool() {
    return school;
  }

  @Nullable
  public SatResult getSatResult() {
    return satResult;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SchoolWithSatResult that = (SchoolWithSatResult) o;
    return school.equals(that.school) && Objects.equals(satResult, that.satResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(school, satResult);
  }
}
