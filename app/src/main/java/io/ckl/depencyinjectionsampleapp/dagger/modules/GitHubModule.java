package io.ckl.depencyinjectionsampleapp.dagger.modules;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.ckl.depencyinjectionsampleapp.BuildConfig;
import io.ckl.depencyinjectionsampleapp.dagger.scope.PerApp;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edsonmenegatti on 3/3/16.
 */
@Module
public class GitHubModule {

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

	@Provides
	public GitHubService provideGitHubService(Retrofit retrofit) {
		return retrofit.create(GitHubService.class);
	}
}
