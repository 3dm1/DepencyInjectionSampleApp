package io.ckl.depencyinjectionsampleapp.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import io.ckl.depencyinjectionsampleapp.dagger.modules.GitHubModule;
import io.ckl.depencyinjectionsampleapp.presentation.usersList.UsersListFragment;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@Singleton
@Component(dependencies = NetComponent.class, modules = GitHubModule.class)
public interface AppComponent {

	void inject(UsersListFragment fragment);
}
