package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractor;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractorImpl;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public class UserPresenter extends MvpPresenter<UserView> {
    private UsersInteractor interactor;


    UserPresenter(UsersInteractor interactor) {
        this.interactor = interactor;
    }


    void loadUser(String id) {
        interactor.loadUser(id, new Carry<User>() {
            @Override
            public void onSuccess(User result) {
                view.showUser(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }




}
