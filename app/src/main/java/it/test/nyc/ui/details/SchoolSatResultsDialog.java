package it.test.nyc.ui.details;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import it.test.nyc.R;
import it.test.nyc.databinding.SchoolSatResultsDialogBinding;
import it.test.nyc.domain.model.SatResult;


public class SchoolSatResultsDialog extends BottomSheetDialogFragment {

  public static final String KEY_SAT_RESULT = "sat_result";

  public SchoolSatResultsDialog() {
    super(R.layout.school_sat_results_dialog);
  }

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SchoolSatResultsDialogBinding binding = SchoolSatResultsDialogBinding.bind(view);
    SatResult result = requireArguments().getParcelable(KEY_SAT_RESULT);
    binding.mathResultView.setText(result.getMath());
    binding.writingResultView.setText(result.getWriting());
    binding.readingResultView.setText(result.getReading());
  }
}
