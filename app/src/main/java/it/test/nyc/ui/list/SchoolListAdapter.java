package it.test.nyc.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import it.test.nyc.R;
import it.test.nyc.databinding.ItemSchoolBinding;
import it.test.nyc.domain.model.School;
import it.test.nyc.domain.model.SchoolWithSatResult;

import javax.inject.Inject;

public class SchoolListAdapter
    extends ListAdapter<SchoolWithSatResult, SchoolListAdapter.SchoolViewHolder> {

  private static final DiffUtil.ItemCallback<SchoolWithSatResult> DIFF_CALLBACK
      = new SchoolListDiffCallback();

  @NonNull
  private final Consumer<SchoolWithSatResult> showResultAction;

  public SchoolListAdapter(@NonNull final Consumer<SchoolWithSatResult> showResultAction) {
    super(DIFF_CALLBACK);
    this.showResultAction = showResultAction;
  }

  @NonNull
  @Override
  public SchoolViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
    final View view = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.item_school, parent, false
    );
    return new SchoolViewHolder(ItemSchoolBinding.bind(view), showResultAction);
  }

  @Override
  public void onBindViewHolder(@NonNull final SchoolViewHolder holder, final int position) {
    holder.bind(getItem(position));
  }

  public class SchoolViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final ItemSchoolBinding binding;
    @NonNull
    private final Consumer<SchoolWithSatResult> showResultAction;

    public SchoolViewHolder(final ItemSchoolBinding binding,
        @NonNull final Consumer<SchoolWithSatResult> showResultAction) {
      super(binding.getRoot());
      this.binding = binding;
      this.showResultAction = showResultAction;
    }

    void bind(SchoolWithSatResult item) {
      final School school = item.getSchool();
      binding.emailView.setText(school.getEmail());
      binding.locationView.setText(school.getLocation());
      binding.titleView.setText(school.getTitle());
      binding.satResultsButton.setEnabled(item.getSatResult() != null);
      binding.satResultsButton.setOnClickListener(v -> {
        showResultAction.accept(getItem(getBindingAdapterPosition()));
      });
    }
  }
}

