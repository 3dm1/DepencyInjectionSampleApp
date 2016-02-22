package io.ckl.depencyinjectionsampleapp.data.api;

import java.util.List;

import io.ckl.depencyinjectionsampleapp.BuildConfig;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public interface GitHubService {

	@GET("/users")
	@Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
	List<GitHubUser> getUsers();

	@GET("/users/{login}")
	@Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
	GitHubUser getUser(@Path("login") String login);

	@GET("/users?since={page}")
	GitHubUser getUsers(@Path("page") int page);
}
