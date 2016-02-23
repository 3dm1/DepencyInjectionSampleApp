package io.ckl.depencyinjectionsampleapp.presentation.usersList;

import java.util.List;

import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class UsersListContract {
	public interface View {

		void showUsers(List<GitHubUser> users);

		void hideProgress();

		void showRetry();
	}

	public interface ActionInteractor {

		void refreshData();

		void loadUsersList();
	}
}
