package it.test.nyc.data.response;

import com.google.gson.annotations.SerializedName;

public class SatResultResponse {

	@SerializedName("dbn")
	private String dbn;

	@SerializedName("sat_writing_avg_score")
	private String satWritingAvgScore;

	@SerializedName("sat_critical_reading_avg_score")
	private String satCriticalReadingAvgScore;

	@SerializedName("sat_math_avg_score")
	private String satMathAvgScore;

	public String getDbn(){
		return dbn;
	}

	public String getSatWritingAvgScore(){
		return satWritingAvgScore;
	}

	public String getSatCriticalReadingAvgScore(){
		return satCriticalReadingAvgScore;
	}

	public String getSatMathAvgScore(){
		return satMathAvgScore;
	}
}