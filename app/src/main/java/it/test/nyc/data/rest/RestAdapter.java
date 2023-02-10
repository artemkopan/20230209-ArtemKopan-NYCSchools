package it.test.nyc.data.rest;

import io.reactivex.rxjava3.core.Single;
import it.test.nyc.data.response.SatResultResponse;
import it.test.nyc.data.response.SchoolResponse;
import retrofit2.http.GET;

import java.util.List;

public interface RestAdapter {

  @GET("resource/s3k6-pzi2.json")
  Single<List<SchoolResponse>> getSchools();

  @GET("resource/f9bf-2cp4.json")
  Single<List<SatResultResponse>> getSatResults();

}
