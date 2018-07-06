package ru.ftc.android.shifttemple.features.users.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

public final class UsersLocalDataSourceImpl implements UsersLocalDataSource {

    private SharedPreferences sharedPreferences;

    public UsersLocalDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_settings_key),
                Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    @Override
    public void putUser(final String key, User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(key, json);
    }

    @Override
    public User getUser(final String key) {
        Gson gson = new Gson();
        String json = getString(key);
        User user = gson.fromJson(json, User.class);
        return user;
    }
}
