package io.ckl.depencyinjectionsampleapp.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by edsonmenegatti on 2/28/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
