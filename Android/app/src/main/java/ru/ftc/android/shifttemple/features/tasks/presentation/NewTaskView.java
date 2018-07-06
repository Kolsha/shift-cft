package ru.ftc.android.shifttemple.features.tasks.presentation;

import ru.ftc.android.shifttemple.features.MvpView;

interface NewTaskView  extends MvpView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void hideActivity();
}
