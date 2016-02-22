package io.ckl.depencyinjectionsampleapp.data.api;

/**
 * Created by edsonmenegatti on 2/22/16.
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.ckl.depencyinjectionsampleapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edsonmenegatti on 5/23/15.
 */
public class GitHubApi {
	private static final String TAG = "GitHubApi";

	public static <S> S createService(Class<S> serviceClass) {

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		final Gson gson = gsonBuilder.create();

		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.readTimeout(30, TimeUnit.SECONDS)
				.connectTimeout(30, TimeUnit.SECONDS)
				.build();
		​
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create(gson))
				.baseUrl(BuildConfig.API_URL)
				.client(client)
				.build();
		​
		return retrofit.create(serviceClass);
	}
}