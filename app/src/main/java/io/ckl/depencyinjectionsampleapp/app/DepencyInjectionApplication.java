package io.ckl.depencyinjectionsampleapp.app;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import io.ckl.depencyinjectionsampleapp.dagger.components.AppComponent;
import io.ckl.depencyinjectionsampleapp.dagger.components.DaggerAppComponent;
import io.ckl.depencyinjectionsampleapp.dagger.components.DaggerNetComponent;
import io.ckl.depencyinjectionsampleapp.dagger.components.NetComponent;
import io.ckl.depencyinjectionsampleapp.dagger.modules.AppModule;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
public class DepencyInjectionApplication extends Application {

	private AppComponent mAppComponent;
	private NetComponent mNetComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);

		setupDaggerAppComponent();
	}

	private void setupDaggerAppComponent() {
		mNetComponent = DaggerNetComponent.builder()
				.appModule(new AppModule(this))
				// .networkModule(new NetworkModule()) is free
				.build();

		mAppComponent = DaggerAppComponent.builder()
				// .gitHubModule(new GitHubModule()) is free
				.netComponent(mNetComponent)
				.build();
	}

	public static AppComponent getAppComponent(Context context) {
		return ((DepencyInjectionApplication) context.getApplicationContext()).mAppComponent;
	}
}
