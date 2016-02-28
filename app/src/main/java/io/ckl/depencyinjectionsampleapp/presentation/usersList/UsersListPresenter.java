package io.ckl.depencyinjectionsampleapp.presentation.usersList;

import java.util.List;

import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import io.ckl.depencyinjectionsampleapp.data.model.GitHubUserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class UsersListPresenter implements UsersListContract.ActionInteractor {

	private static final String TAG = "UsersListPresenter";

	private final UsersListContract.View mView;
	private final GitHubUserModel mUserModel;

	public UsersListPresenter(UsersListContract.View view, GitHubUserModel userModel) {
		mUserModel = userModel;
		mView = view;
	}

	@Override
	public void loadUsersList(boolean forceFetch) {

		final Call<List<GitHubUser>> usersCall = mUserModel.fetchUsers();
		if (forceFetch) {
			executeUsersRequest(usersCall);
		} else {
			final List<GitHubUser> gitHubUsers = mUserModel.loadAll();
			if (gitHubUsers.isEmpty()) {
				executeUsersRequest(usersCall);
			} else {
				mView.showUsers(mUserModel.loadAll());
			}
		}
	}

	@Override
	public void loadUsers(int page) {
		mUserModel.fetchUsers(page).enqueue(new Callback<List<GitHubUser>>() {
			@Override
			public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
				if (response.isSuccess()) {
					mUserModel.saveAll(response.body());
					mView.showUsers(mUserModel.loadAll());
				} else {
					mView.showRetry();
				}
			}

			@Override
			public void onFailure(Call<List<GitHubUser>> call, Throwable t) {
				mView.showRetry();
			}
		});
	}

	private void executeUsersRequest(Call<List<GitHubUser>> usersCall) {
		usersCall.enqueue(new Callback<List<GitHubUser>>() {
			@Override
			public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
				mView.hideProgress();
				if (response.isSuccess()) {
					mUserModel.saveAll(response.body());
					mView.showUsers(mUserModel.loadAll());
				} else {
					mView.showRetry();
				}
			}

			@Override
			public void onFailure(Call<List<GitHubUser>> call, Throwable t) {
				mView.hideProgress();
			}
		});
	}
}
