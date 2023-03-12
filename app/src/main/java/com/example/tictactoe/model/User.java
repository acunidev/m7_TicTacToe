package com.example.tictactoe.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String username;
    private String email;
    private String password;
    private int profilePicResource;

    public User(String username, String email, String password, int profilePicResource) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicResource = profilePicResource;
    }

    public User(String usernameString) {
        this.username = usernameString;
    }

    protected User(Parcel in) {
        username = in.readString();
        email = in.readString();
        password = in.readString();
        profilePicResource = in.readInt();
    }

    public User(String username, int profilePicResource) {
        this.username = username;
        this.profilePicResource = profilePicResource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", email='" + email + '\'' + ", password='" + password +
                '\'' + '}';
    }

    public int getProfilePicResource() {
        return profilePicResource;
    }

    public void setProfilePicResource(int profilePicResource) {
        this.profilePicResource = profilePicResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeInt(profilePicResource);
    }
}
