package io.ckl.depencyinjectionsampleapp.data.storage;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by edsonmenegatti on 2/23/16.
 */
@Database(name = DepencyInjectionDatabase.NAME, version = DepencyInjectionDatabase.VERSION)
public class DepencyInjectionDatabase extends BaseModel {
	public static final String NAME = "Colonies";

	public static final int VERSION = 1;
}
