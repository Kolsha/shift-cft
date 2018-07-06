package ru.ftc.android.shifttemple.features.tasks.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

final class NewTaskPresenter extends MvpPresenter<NewTaskView> {
    private final TasksInteractor interactor;

    private String titleText;
    private String descriptionText;


    NewTaskPresenter(TasksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        view.hideProgress();
        view.showError("New task");
    }


    public void onCreateButtonClicked() {
        view.showError("Your input:\n" + titleText + "\n" + descriptionText);
    }

    public void onTitleTextChanged(final String s) {
        titleText = s;
    }

    public void onDescriptionTextChanged(final String s) {
        descriptionText = s;
    }
}
