package io.ckl.depencyinjectionsampleapp.presentation.usersList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import io.ckl.depencyinjectionsampleapp.R;
import io.ckl.depencyinjectionsampleapp.presentation.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersListFragment extends BaseFragment {

	public static UsersListFragment newInstance() {

		Bundle args = new Bundle();

		UsersListFragment fragment = new UsersListFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected int getFragmentLayoutResource() {
		return R.layout.fragment_users_list;
	}

	@Override
	protected void initViews(@NonNull View view) {

	}

	@Override
	protected void prepareData() {

	}
}
