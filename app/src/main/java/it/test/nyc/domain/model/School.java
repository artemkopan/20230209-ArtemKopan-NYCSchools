package it.test.nyc.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.util.Objects;

public class School implements Parcelable {

  @NonNull
  private final String dbn;
  @NonNull
  private final String title;
  @NonNull
  private final String email;
  @NonNull
  private final String location;

  public School(@NonNull final String dbn, @NonNull final String title, @NonNull final String email,
      @NonNull final String location) {
    this.dbn = dbn;
    this.title = title;
    this.email = email;
    this.location = location;
  }

  @NonNull
  public String getDbn() {
    return dbn;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  @NonNull
  public String getLocation() {
    return location;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.dbn);
    dest.writeString(this.title);
    dest.writeString(this.email);
    dest.writeString(this.location);
  }

  protected School(Parcel in) {
    this.dbn = in.readString();
    this.title = in.readString();
    this.email = in.readString();
    this.location = in.readString();
  }

  public static final Creator<School> CREATOR = new Creator<School>() {
    @Override
    public School createFromParcel(Parcel source) {
      return new School(source);
    }

    @Override
    public School[] newArray(int size) {
      return new School[size];
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
    final School school = (School) o;
    return dbn.equals(school.dbn) && title.equals(school.title) && email.equals(school.email)
        && location.equals(school.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dbn, title, email, location);
  }
}
