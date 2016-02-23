package io.ckl.depencyinjectionsampleapp.app;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
public class DepencyInjectionApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);
	}
}
