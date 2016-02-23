package io.ckl.depencyinjectionsampleapp.data.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import io.ckl.depencyinjectionsampleapp.data.storage.DepencyInjectionDatabase;
import io.ckl.depencyinjectionsampleapp.helpers.Validation;
import io.ckl.depencyinjectionsampleapp.helpers.ValidationFailedException;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
@Table(database = DepencyInjectionDatabase.class)
public class GitHubUser extends BaseModel implements Parcelable, Validation {

	@PrimaryKey
	public int id;

	@Column
	public String login;

	@Column
	public String avatarUrl;

	@Column
	public String name;

	public String getDescription() {
		final StringBuilder stringBuilder = new StringBuilder(login);
		if (!TextUtils.isEmpty(name)) stringBuilder.append(" / ").append(name);
		return stringBuilder.toString();
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.login);
		dest.writeInt(this.id);
		dest.writeString(this.avatarUrl);
		dest.writeString(this.name);
	}

	public GitHubUser() {}

	protected GitHubUser(Parcel in) {
		this.login = in.readString();
		this.id = in.readInt();
		this.avatarUrl = in.readString();
		this.name = in.readString();
	}

	public static final Parcelable.Creator<GitHubUser> CREATOR = new Parcelable.Creator<GitHubUser>() {
		public GitHubUser createFromParcel(Parcel source) {return new GitHubUser(source);}

		public GitHubUser[] newArray(int size) {return new GitHubUser[size];}
	};

	@Override
	public void validate() {
		if (id < 0) {
			throw new ValidationFailedException("Invalid id");
		} else if (TextUtils.isEmpty(login)) {
			throw new ValidationFailedException("Invalid login");
		}
	}
}
