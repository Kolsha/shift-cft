package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

public interface UserView extends MvpView {

    void showUser(User user);

    void showError(String message);
}
