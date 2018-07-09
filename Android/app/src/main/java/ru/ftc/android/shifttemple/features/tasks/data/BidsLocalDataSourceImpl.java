package ru.ftc.android.shifttemple.features.tasks.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

import ru.ftc.android.shifttemple.R;

public class BidsLocalDataSourceImpl implements BidsLocalDataSource {
    public static final String KEY_ARRAYLIST_OF_STRING = "key_arraylist_of_string";
    private SharedPreferences sharedPreferences;

    public BidsLocalDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.bid_settings_key),
                Context.MODE_PRIVATE);
    }

    @Override
    public Set<String> getBidIds() {
        return sharedPreferences.getStringSet(KEY_ARRAYLIST_OF_STRING, Collections.EMPTY_SET);
    }

    @Override
    public void putBidId(String id) {

        Set<String> bidIds = getBidIds();
        bidIds.add(id);

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        prefsEditor.putStringSet(KEY_ARRAYLIST_OF_STRING, bidIds);
        prefsEditor.apply();
    }
}
