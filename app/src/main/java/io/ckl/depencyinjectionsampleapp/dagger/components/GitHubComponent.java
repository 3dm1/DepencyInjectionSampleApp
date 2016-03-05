package io.ckl.depencyinjectionsampleapp.dagger.components;

import dagger.Component;
import dagger.Subcomponent;
import io.ckl.depencyinjectionsampleapp.dagger.modules.GitHubModule;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.presentation.usersList.UsersListFragment;

/**
 * Created by edsonmenegatti on 3/4/16.
 */
@PerApp
@Subcomponent(modules = GitHubModule.class)
public interface GitHubComponent {

	void inject(UsersListFragment fragment);

}
