package it.test.nyc.domain.usecase;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import it.test.nyc.domain.model.SatResult;
import it.test.nyc.domain.model.School;
import it.test.nyc.domain.model.SchoolWithSatResult;
import it.test.nyc.domain.repo.SchoolsRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Combines NYC Schools with SAT results by DBN
 */
public class GetSchoolWithSatResultsUseCase {

  @NonNull
  private final SchoolsRepo repo;

  @Inject
  public GetSchoolWithSatResultsUseCase(@NonNull final SchoolsRepo repo) {
    this.repo = repo;
  }

  public Single<List<SchoolWithSatResult>> invoke() {
    return Single.zip(
        repo.getResults(),
        repo.getSchools(),
        ((satResults, schools) -> {
          // associate sat results by dbn field
          final Map<String, SatResult> satResultsMap = associateByDbn(satResults);
          return getResults(schools, satResultsMap);
        }));
  }

  @NonNull
  private ArrayList<SchoolWithSatResult> getResults(final List<School> schools,
      final Map<String, SatResult> satResultsMap) {
    ArrayList<SchoolWithSatResult> schoolsWithResult = new ArrayList<>(schools.size());
    for (final School school : schools) {
      schoolsWithResult.add(
          new SchoolWithSatResult(school, satResultsMap.get(school.getDbn()))
      );
    }
    return schoolsWithResult;
  }

  private Map<String, SatResult> associateByDbn(final List<SatResult> satResults) {
    Map<String, SatResult> satResultsMap = new HashMap<>();
    for (final SatResult satResult : satResults) {
      satResultsMap.put(satResult.getDbn(), satResult);
    }
    return satResultsMap;
  }
}
