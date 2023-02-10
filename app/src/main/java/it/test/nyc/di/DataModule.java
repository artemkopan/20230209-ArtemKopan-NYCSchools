package it.test.nyc.di;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import it.test.nyc.data.environment.EnvironmentProvider;
import it.test.nyc.data.formatter.ErrorFormatterImpl;
import it.test.nyc.data.rest.RestAdapter;
import it.test.nyc.data.rest.RestService;
import it.test.nyc.data.rest.RestServiceImpl;
import it.test.nyc.domain.formatter.ErrorFormatter;
import it.test.nyc.domain.repo.SchoolsRepo;
import it.test.nyc.domain.repo.SchoolsRepoImpl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

  @Provides
  public Gson provideGson() {
    return new Gson();
  }

  @Provides
  public EnvironmentProvider provideEnvironment() {
    //returns api url for the current resource. Url can be injected via any way.
    return () -> "https://data.cityofnewyork.us/";
  }

  @Provides
  public HttpLoggingInterceptor providerHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor(createLogger()).setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Provides
  public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @Provides
  public Retrofit provideRetrofit(
      OkHttpClient okHttpClient,
      Gson gson,
      EnvironmentProvider environmentProvider
  ) {
    return new Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(environmentProvider.getApiUrl())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  @Provides
  public RestAdapter provideRestAdapter(Retrofit retrofit) {
    return retrofit.create(RestAdapter.class);
  }

  @Provides
  public RestService provideRestService(RestServiceImpl impl) {
    return impl;
  }

  @Provides
  public SchoolsRepo provideSchoolsRepo(SchoolsRepoImpl impl) {
    return impl;
  }

  @Provides
  public ErrorFormatter provideErrorFormatter(ErrorFormatterImpl impl) {
    return impl;
  }

  private static HttpLoggingInterceptor.Logger createLogger() {
    return s -> Timber.tag("OkHttp").d(s);
  }
}
