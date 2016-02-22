package io.ckl.depencyinjectionsampleapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public abstract class BaseFragment extends Fragment {

	@CallSuper
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		prepareData();
	}

	@CallSuper
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(getFragmentLayoutResource(), container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@LayoutRes
	protected abstract int getFragmentLayoutResource();

	protected abstract void initViews(@NonNull View view);

	protected abstract void prepareData();

	@CallSuper
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
