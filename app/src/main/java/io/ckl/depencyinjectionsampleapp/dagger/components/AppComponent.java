package io.ckl.depencyinjectionsampleapp.dagger.components;

import android.content.Context;

import dagger.Component;
import io.ckl.depencyinjectionsampleapp.dagger.modules.AppModule;
import io.ckl.depencyinjectionsampleapp.dagger.modules.NetworkModule;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.presentation.usersList.UsersListFragment;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@PerApp
@Component(
		modules = {
				AppModule.class,
				NetworkModule.class
		}
)
public interface AppComponent {

	void inject(UsersListFragment fragment);

	Context getApplicationContext();
}
