package it.test.nyc.ui.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import it.test.nyc.domain.model.SchoolWithSatResult;

public class SchoolListDiffCallback extends DiffUtil.ItemCallback<SchoolWithSatResult> {

  @Override
  public boolean areItemsTheSame(
      @NonNull final SchoolWithSatResult oldItem,
      @NonNull final SchoolWithSatResult newItem
  ) {
    return oldItem.getSchool().getDbn().equals(newItem.getSchool().getDbn());
  }

  @Override
  public boolean areContentsTheSame(@NonNull final SchoolWithSatResult oldItem,
      @NonNull final SchoolWithSatResult newItem) {
    return oldItem.equals(newItem);
  }
}
