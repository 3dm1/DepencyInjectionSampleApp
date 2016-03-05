package io.ckl.depencyinjectionsampleapp.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.ckl.depencyinjectionsampleapp.BuildConfig;
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
	public Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
		return new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create(gson))
				.baseUrl(BuildConfig.API_URL)
				.client(client)
				.build();
	}

	@Provides
	public OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
		return new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.cache(cache)
				.connectTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}

	@Provides
	public Cache provideCache(Application application) {
		return new Cache(application.getCacheDir(), CACHE_SIZE);
	}

	@Provides
	public HttpLoggingInterceptor provideHttpInterceptor() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return interceptor;
	}

	@Provides
	public Gson provideGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}

}
