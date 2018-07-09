package ru.ftc.android.shifttemple.features.tasks.presentation;


import android.content.Context;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

interface TasksListView extends MvpView {

    void showProgress();

    void hideProgress();

    void showTaskList(List<Task> list);

    void showError(String message);

    void showLoginForm();

    void showNewTaskForm();

    void showTask(Task task);

    void showUserInfo(User user);

}