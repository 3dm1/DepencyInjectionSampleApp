package io.ckl.depencyinjectionsampleapp.presentation.adapter.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.ckl.depencyinjectionsampleapp.R;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

	@Bind(R.id.user_avatar) ImageView mUserAvatar;
	@Bind(R.id.user_name) TextView mUserName;
	private GitHubUser mGitHubUser;

	public UserViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void updateOnData(GitHubUser gitHubUser) {
		mGitHubUser = gitHubUser;

		mUserName.setText(gitHubUser.getDescription());
	}
}
