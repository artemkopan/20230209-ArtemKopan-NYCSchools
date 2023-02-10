package it.test.nyc.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.test.nyc.domain.formatter.ErrorFormatter;
import it.test.nyc.domain.model.SchoolWithSatResult;
import it.test.nyc.domain.usecase.GetSchoolWithSatResultsUseCase;
import timber.log.Timber;

import java.util.List;

import javax.inject.Inject;

@HiltViewModel
public class SchoolListViewModel extends ViewModel {

  private static final String KEY_STATE = "state";

  @NonNull
  final LiveData<SchoolListState> stateLiveData;

  @NonNull
  private final ErrorFormatter errorFormatter;
  @NonNull
  private final GetSchoolWithSatResultsUseCase getSchoolWithSatResultsUseCase;
  @NonNull
  private final SavedStateHandle savedStateHandle;
  @NonNull
  private final CompositeDisposable disposable = new CompositeDisposable();

  @Inject
  public SchoolListViewModel(
      @NonNull final ErrorFormatter errorFormatter,
      @NonNull final GetSchoolWithSatResultsUseCase getSchoolWithSatResultsUseCase,
      @NonNull final SavedStateHandle savedStateHandle
  ) {
    this.errorFormatter = errorFormatter;
    this.getSchoolWithSatResultsUseCase = getSchoolWithSatResultsUseCase;
    this.savedStateHandle = savedStateHandle;
    this.stateLiveData = savedStateHandle.getLiveData(KEY_STATE, new SchoolListState());
    reloadList();
  }

  public void reloadList() {
    mutateState(new SchoolListState(true));
    disposable.add(
        getSchoolWithSatResultsUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumerSuccess(), consumeError())
    );
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    disposable.clear();
  }

  @NonNull
  private Consumer<Throwable> consumeError() {
    return throwable -> {
      Timber.e(throwable);
      mutateState(new SchoolListState(errorFormatter.displayName(throwable), true));
    };
  }

  @NonNull
  private Consumer<List<SchoolWithSatResult>> consumerSuccess() {
    return result -> mutateState(new SchoolListState(result));
  }

  private void mutateState(SchoolListState state) {
    savedStateHandle.set(KEY_STATE, state);
  }

}
