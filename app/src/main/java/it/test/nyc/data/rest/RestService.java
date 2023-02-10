package it.test.nyc.data.rest;

import io.reactivex.rxjava3.core.Single;
import it.test.nyc.domain.model.SatResult;
import it.test.nyc.domain.model.School;

import java.util.List;

public interface RestService {

  Single<List<School>> getSchools();

  Single<List<SatResult>> getSatResults();
}
