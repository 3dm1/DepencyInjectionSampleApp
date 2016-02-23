package io.ckl.depencyinjectionsampleapp.presentation.usersList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import io.ckl.depencyinjectionsampleapp.R;
import io.ckl.depencyinjectionsampleapp.data.entities.GitHubUser;
import io.ckl.depencyinjectionsampleapp.data.model.GitHubUserModel;
import io.ckl.depencyinjectionsampleapp.presentation.adapter.GitHubUserAdapter;
import io.ckl.depencyinjectionsampleapp.presentation.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersListFragment extends BaseFragment
		implements UsersListContract.View {

	@Bind(R.id.user_list_recycler) RecyclerView mUsersRecycler;
	@Bind(R.id.user_list_refresh_layout) SwipeRefreshLayout mRefreshLayout;
	@Bind(R.id.user_list_empty_view) TextView mEmptyView;

	private UsersListPresenter mUsersListPresenter;
	private GitHubUserAdapter mGuestAdapter;
	private GitHubUserModel mUserModel;

	public static UsersListFragment newInstance() {

		Bundle args = new Bundle();

		UsersListFragment fragment = new UsersListFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected int getFragmentLayoutResource() {
		return R.layout.users_list_fragment;
	}

	@Override
	protected void initViews(@NonNull View view) {
		setupRefreshLayout();
		setupRecyclerView();
		prepareRecyclerViewForData();
	}

	private void setupRecyclerView() {
		mUsersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

	}

	private void setupRefreshLayout() {
		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				mUsersListPresenter.refreshData();
			}
		});
	}

	@Override
	protected void prepareData() {
		mUserModel = new GitHubUserModel(getActivity());
		mUsersListPresenter = new UsersListPresenter(this, mUserModel);
		mUsersListPresenter.loadUsersList();

	}

	private void prepareRecyclerViewForData() {
		mGuestAdapter = new GitHubUserAdapter();

		showEmptyView();
		mUsersRecycler.setAdapter(mGuestAdapter);
	}

	private void showEmptyView() {
		mEmptyView.setVisibility(View.VISIBLE);
		mUsersRecycler.setVisibility(View.INVISIBLE);
	}

	private void hideEmptyView() {
		mEmptyView.setVisibility(View.INVISIBLE);
		mUsersRecycler.setVisibility(View.VISIBLE);
	}

	@Override
	public void showUsers(List<GitHubUser> users) {
		if (users.size() > 0) {
			hideEmptyView();
		} else {
			showEmptyView();
		}

		mGuestAdapter.setData(users);
	}

	@Override
	public void hideProgress() {
		mRefreshLayout.setRefreshing(false);
	}

	@Override
	public void showRetry() {

	}
}
