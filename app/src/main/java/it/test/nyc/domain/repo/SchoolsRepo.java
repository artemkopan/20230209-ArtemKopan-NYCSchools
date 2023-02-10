package it.test.nyc.domain.repo;

import androidx.annotation.CheckResult;
import io.reactivex.rxjava3.core.Single;
import it.test.nyc.domain.model.SatResult;
import it.test.nyc.domain.model.School;

import java.util.List;

public interface SchoolsRepo {

  @CheckResult
  Single<List<School>> getSchools();

  @CheckResult
  Single<List<SatResult>> getResults();
}
