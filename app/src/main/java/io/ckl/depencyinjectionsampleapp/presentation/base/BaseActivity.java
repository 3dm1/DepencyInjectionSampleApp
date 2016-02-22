package io.ckl.depencyinjectionsampleapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getResouceLayout());

		ButterKnife.bind(this);
	}

	@LayoutRes
	protected abstract int getResouceLayout();
}
