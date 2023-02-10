package it.test.nyc.data.rest;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import it.test.nyc.data.mapper.SatResultMapper;
import it.test.nyc.data.mapper.SchoolMapper;
import it.test.nyc.domain.model.SatResult;
import it.test.nyc.domain.model.School;

import java.util.List;

import javax.inject.Inject;

public class RestServiceImpl implements RestService {

  @NonNull
  private final RestAdapter adapter;
  @NonNull
  private final SatResultMapper satResultMapper;
  @NonNull
  private final SchoolMapper schoolMapper;

  @Inject
  public RestServiceImpl(
      @NonNull final RestAdapter adapter,
      @NonNull final SatResultMapper satResultMapper,
      @NonNull final SchoolMapper schoolMapper
  ) {
    this.adapter = adapter;
    this.satResultMapper = satResultMapper;
    this.schoolMapper = schoolMapper;
  }

  @Override
  public Single<List<School>> getSchools() {
    return adapter.getSchools()
        .toObservable()
        .flatMapIterable(list -> list)
        .map(schoolMapper::toModel)
        .toList();
  }

  @Override
  public Single<List<SatResult>> getSatResults() {
    return adapter.getSatResults()
        .toObservable()
        .flatMapIterable(list -> list)
        .map(satResultMapper::toModel)
        .toList();
  }
}
