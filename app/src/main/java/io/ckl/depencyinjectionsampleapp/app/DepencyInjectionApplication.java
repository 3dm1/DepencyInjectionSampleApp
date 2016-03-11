package io.ckl.depencyinjectionsampleapp.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.FlowManager;

import io.ckl.depencyinjectionsampleapp.dagger.components.AppComponent;
import io.ckl.depencyinjectionsampleapp.dagger.components.DaggerAppComponent;
import io.ckl.depencyinjectionsampleapp.dagger.modules.AppModule;
import io.ckl.depencyinjectionsampleapp.dagger.modules.NetworkModule;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
public class DepencyInjectionApplication extends Application {

	private AppComponent mAppComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);

		setupDaggerAppComponent();
	}

	private void setupDaggerAppComponent() {
		mAppComponent = prepareAppComponent().build();
	}

	@NonNull
	protected DaggerAppComponent.Builder prepareAppComponent() {
		return DaggerAppComponent.builder()
				.appModule(new AppModule(this));

		// Daggers automatically creates this module for you
//				.networkModule(new NetworkModule());
	}

	public static AppComponent getAppComponent(Context context) {
		return ((DepencyInjectionApplication) context.getApplicationContext()).mAppComponent;
	}
}
