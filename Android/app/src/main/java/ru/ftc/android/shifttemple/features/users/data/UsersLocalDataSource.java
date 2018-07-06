package ru.ftc.android.shifttemple.features.users.data;

import ru.ftc.android.shifttemple.features.users.domain.model.User;

public interface UsersLocalDataSource {

    String getString(final String key);

    void putString(final String key, final String value);

    User getUser(final String key);

    void putUser(final String key, User user);

}
