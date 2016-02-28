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

	public static final int INFINITE_SCROLL_THRESHOLD = 10;
	@Bind(R.id.user_list_recycler) RecyclerView mUsersRecycler;
	@Bind(R.id.user_list_refresh_layout) SwipeRefreshLayout mRefreshLayout;
	@Bind(R.id.user_list_empty_view) TextView mEmptyView;

	private UsersListPresenter mActionInteractor;
	private GitHubUserAdapter mUserAdapter;
	private LinearLayoutManager mLayoutManager;
	private boolean isLoading;

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
		mLayoutManager = new LinearLayoutManager(getActivity());
		mUsersRecycler.setLayoutManager(mLayoutManager);
		mUsersRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				final boolean isScrollDown = dy > 0;
				if (isScrollDown) {
					final int visibleItemCount = mLayoutManager.getChildCount();
					final int totalItemCount = mLayoutManager.getItemCount();
					final int lastVisibleItem = visibleItemCount +
							mLayoutManager.findFirstVisibleItemPosition();

					final boolean triggerRequest =
							totalItemCount - lastVisibleItem > INFINITE_SCROLL_THRESHOLD;

					if (!isLoading && triggerRequest) {
						isLoading = true;
						mActionInteractor.loadUsers(
								mUserAdapter.getUser(totalItemCount - 1).id
						);
					}

				}
			}
		});

	}

	private void setupRefreshLayout() {
		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				mActionInteractor.loadUsersList(true);
			}
		});
	}

	@Override
	protected void prepareData() {
		GitHubUserModel userModel = new GitHubUserModel(getActivity());
		mActionInteractor = new UsersListPresenter(this, userModel);
		mActionInteractor.loadUsersList(false);

	}

	private void prepareRecyclerViewForData() {
		mUserAdapter = new GitHubUserAdapter();

		showEmptyView();
		mUsersRecycler.setAdapter(mUserAdapter);
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
			isLoading = false;
			hideEmptyView();
		} else {
			showEmptyView();
		}

		mUserAdapter.setData(users);
	}

	@Override
	public void hideProgress() {
		mRefreshLayout.setRefreshing(false);
	}

	@Override
	public void showRetry() {

	}
}
