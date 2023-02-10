package it.test.nyc.data.mapper;

import it.test.nyc.data.response.SchoolResponse;
import it.test.nyc.domain.model.School;

import javax.inject.Inject;

import static it.test.nyc.utils.StringUtils.getOrEmpty;

public class SchoolMapper {

  @Inject
  public SchoolMapper() {
  }

  public School toModel(SchoolResponse response) {
    return new School(
        getOrEmpty(response.getDbn()),
        getOrEmpty(response.getSchoolName()),
        getOrEmpty(response.getSchoolEmail()),
        getOrEmpty(response.getLocation())
    );
  }
}
