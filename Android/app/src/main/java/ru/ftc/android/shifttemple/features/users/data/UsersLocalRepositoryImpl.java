package ru.ftc.android.shifttemple.features.users.data;

import ru.ftc.android.shifttemple.features.users.domain.model.User;

public final class UsersLocalRepositoryImpl implements UsersLocalRepository {
    private final UsersLocalDataSource dataSource;

    public UsersLocalRepositoryImpl(UsersLocalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getUserToken() {
        return dataSource.getString("token");
    }

    @Override
    public User getUser() {
        return dataSource.getUser("local_user_object");
    }

    @Override
    public void setUser(User user) {
        dataSource.putUser("local_user_object", user);
    }

    @Override
    public void setUserToken(String token) {
        dataSource.putString("token", token);
    }
}
