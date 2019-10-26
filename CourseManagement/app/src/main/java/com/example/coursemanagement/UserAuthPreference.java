package com.example.coursemanagement;

import android.content.Context;
import android.content.SharedPreferences;

public class UserAuthPreference {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserAuthPreference(Context context)
    {
        preferences = context.getSharedPreferences("UserAuthentication",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void SetLoginStatus(boolean status)
    {
        editor.putBoolean("loginStatus",status);
        editor.commit();
    }
    public boolean getLoginStatus()
    {
        return preferences.getBoolean("loginStatus",false);
    }

}
