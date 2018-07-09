package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

final class TasksListPresenter extends MvpPresenter<TasksListView> {
    private final TasksInteractor interactor;

    TasksListPresenter(TasksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadUserInfo();
        loadTasks();
    }

    private void loadUserInfo() {
        view.showProgress();
        interactor.loadLocalUser(new Carry<User>() {
            @Override
            public void onSuccess(User result) {
                view.showUserInfo(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void loadTasks() {
        view.showProgress();
        interactor.loadTasks(new Carry<List<Task>>() {

            @Override
            public void onSuccess(List<Task> result) {
                view.showTaskList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());

                if (throwable instanceof NotAuthorizedException) {
                    view.showLoginForm();
                }

            }

        });
    }

    void onRefreshTasks() {
        loadTasks();

    }

    void onTaskSelected(Task task) {
        view.showProgress();
        view.showTask(task);
        view.hideProgress();
    }

    void onTaskLongClicked(Task task) {
        view.showError("May be added to favorite.. May be no;)"); // TODO: favorite
    }


    public void onCreateTaskClicked() {
        view.showNewTaskForm();
        loadTasks();
    }
}
