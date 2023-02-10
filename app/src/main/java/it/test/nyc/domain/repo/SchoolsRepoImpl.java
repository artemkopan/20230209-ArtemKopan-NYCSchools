package it.test.nyc.domain.repo;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import it.test.nyc.data.rest.RestService;
import it.test.nyc.domain.model.SatResult;
import it.test.nyc.domain.model.School;

import java.util.List;

import javax.inject.Inject;

public class SchoolsRepoImpl implements SchoolsRepo {

  @NonNull
  private final RestService restService;

  @Inject
  public SchoolsRepoImpl(@NonNull final RestService restService) {
    this.restService = restService;
  }

  @Override
  public Single<List<School>> getSchools() {
    return restService.getSchools();
  }

  @Override
  public Single<List<SatResult>> getResults() {
    return restService.getSatResults();
  }
}
