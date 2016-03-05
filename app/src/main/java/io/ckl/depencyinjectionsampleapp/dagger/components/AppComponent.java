package io.ckl.depencyinjectionsampleapp.dagger.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.ckl.depencyinjectionsampleapp.dagger.modules.AppModule;
import io.ckl.depencyinjectionsampleapp.dagger.modules.GitHubModule;
import io.ckl.depencyinjectionsampleapp.dagger.modules.NetworkModule;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

	Application getApplication();

	GitHubComponent plus(GitHubModule gitHubModule);
}
