package ru.ftc.android.shifttemple.features.users.data;

import android.content.Context;

public final class UserLocalRepositoryFactory {
    public static UsersLocalRepository create(final Context context){
        final UsersLocalDataSource dataSource = new UsersLocalDataSourceImpl(context);
        return new UsersLocalRepositoryImpl(dataSource);
    }
}
