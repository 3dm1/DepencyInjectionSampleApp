package io.ckl.depencyinjectionsampleapp.dagger.components;

import dagger.Component;
import io.ckl.depencyinjectionsampleapp.dagger.modules.AppModule;
import io.ckl.depencyinjectionsampleapp.dagger.modules.NetworkModule;
import retrofit2.Retrofit;

@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetComponent {

	Retrofit retrofit();
}
