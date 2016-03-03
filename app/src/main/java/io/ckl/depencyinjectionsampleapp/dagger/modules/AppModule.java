package io.ckl.depencyinjectionsampleapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.data.model.GitHubUserModel;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@PerApp
@Module
public class AppModule {

	private final Application mApplication;

	public AppModule(Application application) {
		mApplication = application;
	}

	@Provides
	Application providesApplication() {
		return mApplication;
	}
}
