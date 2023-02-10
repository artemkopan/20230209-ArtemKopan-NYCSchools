package it.test.nyc.ui.list;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy;
import dagger.hilt.android.AndroidEntryPoint;
import it.test.nyc.R;
import it.test.nyc.databinding.SchoolListFragmentBinding;
import it.test.nyc.domain.model.SchoolWithSatResult;
import it.test.nyc.ui.details.SchoolSatResultsDialog;
import timber.log.Timber;

@AndroidEntryPoint
public class SchoolListFragment extends Fragment {

  private SchoolListViewModel viewModel;
  @Nullable
  private SchoolListFragmentBinding binding;
  private SchoolListAdapter adapter;

  public SchoolListFragment() {
    super(R.layout.school_list_fragment);
  }

  @Override
  public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(this).get(SchoolListViewModel.class);
  }

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding = SchoolListFragmentBinding.bind(view);
    // init adapter
    adapter = new SchoolListAdapter(this::showDetails);
    adapter.setStateRestorationPolicy(StateRestorationPolicy.PREVENT_WHEN_EMPTY);

    // setup views
    binding.retryButton.setOnClickListener(v -> {viewModel.reloadList();});
    binding.itemListView.setAdapter(adapter);
    binding.toolbar.setOnMenuItemClickListener(this::onToolbarItemClicked);

    // subscribe on viewModel
    viewModel.stateLiveData.observe(getViewLifecycleOwner(), this::renderState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  boolean onToolbarItemClicked(MenuItem item) {
    if (item.getItemId() == R.id.refresh) {
      viewModel.reloadList();
    }
    return true;
  }

  void showDetails(SchoolWithSatResult item) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(SchoolSatResultsDialog.KEY_SAT_RESULT, item.getSatResult());
    Navigation.findNavController(requireView()).navigate(R.id.satResultDialog, bundle);
  }

  void renderState(SchoolListState state) {
    if (binding == null) {
      Timber.e("Unacceptable case. Check lifecycle owner on subscription");
      return;
    }
    binding.errorContentLayout.setVisibility(state.getError() == null ? View.GONE : View.VISIBLE);
    binding.errorMessageView.setText(state.getError());
    binding.progressBar.setVisibility(state.isLoading() ? View.VISIBLE : View.GONE);
    binding.toolbar.getMenu().findItem(R.id.refresh).setVisible(!state.isLoading());
    adapter.submitList(state.getSchools());
  }
}
