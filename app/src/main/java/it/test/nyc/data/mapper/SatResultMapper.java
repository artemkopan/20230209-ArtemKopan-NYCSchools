package it.test.nyc.data.mapper;

import it.test.nyc.data.response.SatResultResponse;
import it.test.nyc.domain.model.SatResult;

import javax.inject.Inject;

import static it.test.nyc.utils.StringUtils.getOrEmpty;

public class SatResultMapper {

  @Inject
  public SatResultMapper() {
  }

  public SatResult toModel(SatResultResponse response) {
    return new SatResult(
        getOrEmpty(response.getDbn()),
        getOrEmpty(response.getSatMathAvgScore()),
        getOrEmpty(response.getSatCriticalReadingAvgScore()),
        getOrEmpty(response.getSatWritingAvgScore())
    );
  }
}
