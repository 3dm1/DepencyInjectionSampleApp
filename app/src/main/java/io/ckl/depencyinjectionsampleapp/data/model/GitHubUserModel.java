package io.ckl.depencyinjectionsampleapp.data.model;

import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import javax.inject.Inject;

import io.ckl.depencyinjectionsampleapp.dagger.modules.GitHubModule;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import io.ckl.depencyinjectionsampleapp.helpers.ValidationHelper;
import retrofit2.Call;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class GitHubUserModel {

	private final GitHubModule.GitHubService mService;

	@Inject
	public GitHubUserModel(GitHubModule.GitHubService service) {
		mService = service;
	}

	public Call<List<GitHubUser>> fetchUsers(int page) {
		return mService.getUsers(page);
	}

	public Call<List<GitHubUser>> fetchUsers() {
		return mService.getUsers();
	}

	public List<GitHubUser> loadAll() {
		return new Select()
				.from(GitHubUser.class)
				.queryList();
	}

	public void saveAll(List<GitHubUser> users) {
		ValidationHelper.pruneInvalid(users);
		if (users.isEmpty()) {
			return;
		}

		ProcessModelInfo<GitHubUser> itemProcessModelInfo = ProcessModelInfo.withModels(users);
		TransactionManager.getInstance().addTransaction(new SaveModelTransaction<>(itemProcessModelInfo));
	}
}
