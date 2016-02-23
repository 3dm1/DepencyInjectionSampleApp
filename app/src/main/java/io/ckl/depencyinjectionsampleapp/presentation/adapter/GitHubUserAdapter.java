package io.ckl.depencyinjectionsampleapp.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.ckl.depencyinjectionsampleapp.R;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import io.ckl.depencyinjectionsampleapp.presentation.adapter.holders.UserViewHolder;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class GitHubUserAdapter extends RecyclerView.Adapter {
	private List<GitHubUser> mUsers = new ArrayList<>();

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		Context context = parent.getContext();
		final View view = LayoutInflater.from(context).inflate(R.layout.user_view_holder, parent, false);
		return new UserViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((UserViewHolder)holder).updateOnData(mUsers.get(position));
	}

	@Override
	public int getItemCount() {
		return mUsers.size();
	}

	public void setData(List<GitHubUser> users) {
		mUsers = users;
		notifyDataSetChanged();
	}
}
