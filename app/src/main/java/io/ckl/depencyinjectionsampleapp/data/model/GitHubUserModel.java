package io.ckl.depencyinjectionsampleapp.data.model;

import java.util.List;

import io.ckl.depencyinjectionsampleapp.data.api.GitHubApi;
import io.ckl.depencyinjectionsampleapp.data.api.GitHubService;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import retrofit2.Call;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class GitHubUserModel {

	private final GitHubService mService;

	public GitHubUserModel() {
		mService = GitHubApi.createService(GitHubService.class);
	}

	public Call<List<GitHubUser>> fetchUsers() {
		return mService.getUsers();
	}
}
