package it.test.nyc.data.response;

import com.google.gson.annotations.SerializedName;

public class SchoolResponse {

  @SerializedName("school_name")
  private String schoolName;

  @SerializedName("dbn")
  private String dbn;

  @SerializedName("school_email")
  private String schoolEmail;

  @SerializedName("location")
  private String location;


  public String getSchoolName() {
    return schoolName;
  }

  public String getDbn() {
    return dbn;
  }

  public String getSchoolEmail() {
    return schoolEmail;
  }

  public String getLocation() {
    return location;
  }
}