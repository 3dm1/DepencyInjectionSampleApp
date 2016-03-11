package io.ckl.depencyinjectionsampleapp.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.data.api.GitHubService;
import io.ckl.depencyinjectionsampleapp.data.model.GitHubUserModel;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@PerApp
@Module
public class AppModule {

	private final Context mApplicationContext;

	public AppModule(Context applicationContext) {
		mApplicationContext = applicationContext;
	}

	@Provides
	@PerApp
	public Context provideApplicationContext() {
		return mApplicationContext;
	}
}
