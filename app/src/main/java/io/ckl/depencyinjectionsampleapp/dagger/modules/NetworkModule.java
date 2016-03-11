package io.ckl.depencyinjectionsampleapp.dagger.modules;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.ckl.depencyinjectionsampleapp.BuildConfig;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.data.api.GitHubService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@Module(includes = AppModule.class)
public class NetworkModule {

	public static final int CACHE_SIZE = 5 * 1024 * 1024;

	@Provides
	@PerApp
	public GitHubService provideGitHubService(Retrofit retrofit) {
		return retrofit.create(GitHubService.class);
	}

	@Provides
	@PerApp
	public Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
		return new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create(gson))
				.baseUrl(BuildConfig.API_URL)
				.client(client)
				.build();
	}

	@Provides
	@PerApp
	public OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
		return new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.cache(cache)
				.connectTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}

	@Provides
	@PerApp
	public Cache provideCache(Context context) {
		return new Cache(context.getCacheDir(), CACHE_SIZE);
	}

	@Provides
	@PerApp
	public HttpLoggingInterceptor provideHttpInterceptor() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return interceptor;
	}

	@Provides
	@PerApp
	public Gson provideGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}

}
