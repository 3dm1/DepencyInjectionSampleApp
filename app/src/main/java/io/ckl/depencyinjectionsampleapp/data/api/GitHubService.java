package io.ckl.depencyinjectionsampleapp.data.api;

import java.util.List;

import io.ckl.depencyinjectionsampleapp.BuildConfig;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public interface GitHubService {

	@GET("/users")
	@Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
	Call<List<GitHubUser>> getUsers();

	@GET("/users/{login}")
	@Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
	Call<GitHubUser> getUser(@Path("login") String login);

	@GET("/users")
	@Headers("Authorization: token " + BuildConfig.GITHUB_TOKEN)
	Call<List<GitHubUser>> getUsers(@Query("since") int page);
}
