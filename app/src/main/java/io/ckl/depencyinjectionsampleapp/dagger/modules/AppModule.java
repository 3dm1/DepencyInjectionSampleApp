package io.ckl.depencyinjectionsampleapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@Module
public class AppModule {

	private final Application mApplication;

	public AppModule(Application application) {
		mApplication = application;
	}

	@Provides
	public Application provideApplication() {
		return mApplication;
	}
}
