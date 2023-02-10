package it.test.nyc.ui.list;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import it.test.nyc.domain.model.SchoolWithSatResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchoolListState implements Parcelable {

  private boolean isLoading = false;
  @Nullable
  private String error = null;
  private boolean showRetry = false;
  @NonNull
  private List<SchoolWithSatResult> schools = Collections.emptyList();

  public SchoolListState() {
  }

  public SchoolListState(final boolean isLoading) {
    this.isLoading = isLoading;
  }

  public SchoolListState(@Nullable final String error, final boolean showRetry) {
    this.error = error;
    this.showRetry = showRetry;
  }

  public SchoolListState(@NonNull final List<SchoolWithSatResult> schools) {
    this.schools = schools;
  }

  public boolean isLoading() {
    return isLoading;
  }

  @Nullable
  public String getError() {
    return error;
  }

  public boolean isShowRetry() {
    return showRetry;
  }

  @NonNull
  public List<SchoolWithSatResult> getSchools() {
    return schools;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeByte(this.isLoading ? (byte) 1 : (byte) 0);
    dest.writeString(this.error);
    dest.writeByte(this.showRetry ? (byte) 1 : (byte) 0);
    dest.writeList(this.schools);
  }

  public void readFromParcel(Parcel source) {
    this.isLoading = source.readByte() != 0;
    this.error = source.readString();
    this.showRetry = source.readByte() != 0;
    this.schools = new ArrayList<SchoolWithSatResult>();
    source.readList(this.schools, SchoolWithSatResult.class.getClassLoader());
  }

  protected SchoolListState(Parcel in) {
    this.isLoading = in.readByte() != 0;
    this.error = in.readString();
    this.showRetry = in.readByte() != 0;
    this.schools = new ArrayList<SchoolWithSatResult>();
    in.readList(this.schools, SchoolWithSatResult.class.getClassLoader());
  }

  public static final Creator<SchoolListState> CREATOR = new Creator<SchoolListState>() {
    @Override
    public SchoolListState createFromParcel(Parcel source) {
      return new SchoolListState(source);
    }

    @Override
    public SchoolListState[] newArray(int size) {
      return new SchoolListState[size];
    }
  };
}
