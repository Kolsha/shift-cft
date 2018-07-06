package ru.ftc.android.shifttemple.features.users.data;

import ru.ftc.android.shifttemple.features.users.domain.model.User;

public interface UsersLocalRepository {
    String getUserToken();
    void setUserToken(String token);

    User getUser();
    void setUser(User user);
}
