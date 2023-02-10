package it.test.nyc.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.util.Objects;

public class SatResult implements Parcelable {

  @NonNull
  private final String dbn;
  @NonNull
  private final String math;
  @NonNull
  private final String reading;
  @NonNull
  private final String writing;

  public SatResult(@NonNull final String dbn, @NonNull final String math,
      @NonNull final String reading,
      @NonNull final String writing) {
    this.dbn = dbn;
    this.math = math;
    this.reading = reading;
    this.writing = writing;
  }

  @NonNull
  public String getDbn() {
    return dbn;
  }

  @NonNull
  public String getMath() {
    return math;
  }

  @NonNull
  public String getReading() {
    return reading;
  }

  @NonNull
  public String getWriting() {
    return writing;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.dbn);
    dest.writeString(this.math);
    dest.writeString(this.reading);
    dest.writeString(this.writing);
  }

  protected SatResult(Parcel in) {
    this.dbn = in.readString();
    this.math = in.readString();
    this.reading = in.readString();
    this.writing = in.readString();
  }

  public static final Creator<SatResult> CREATOR = new Creator<SatResult>() {
    @Override
    public SatResult createFromParcel(Parcel source) {
      return new SatResult(source);
    }

    @Override
    public SatResult[] newArray(int size) {
      return new SatResult[size];
    }
  };

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SatResult satResult = (SatResult) o;
    return dbn.equals(satResult.dbn) && math.equals(satResult.math) && reading.equals(
        satResult.reading) && writing.equals(satResult.writing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dbn, math, reading, writing);
  }
}
