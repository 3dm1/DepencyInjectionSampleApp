package io.ckl.depencyinjectionsampleapp.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edsonmenegatti on 2/22/16.
 */
public class GitHubUser implements Parcelable {

	public String login;
	public int id;
	public String avatarUrl;
	public String name;

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
}
