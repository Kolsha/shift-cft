package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.tasks.data.TasksApi;
import ru.ftc.android.shifttemple.features.tasks.data.TasksDataSource;
import ru.ftc.android.shifttemple.features.tasks.data.TasksDataSourceImpl;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepository;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepositoryImpl;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractorImpl;
import ru.ftc.android.shifttemple.features.users.data.UserLocalRepositoryFactory;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalDataSource;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalDataSourceImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepositoryImpl;

final class PresenterFactory {

    private static TasksInteractor createTasksInteractor(Context context) {

        final TasksApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(TasksApi.class);

        final UsersLocalRepository usersRepositoryLocal = UserLocalRepositoryFactory.create(context);

        final TasksDataSource dataSource = new TasksDataSourceImpl(api);
        final TasksRepository repository = new TasksRepositoryImpl(dataSource);
        return new TasksInteractorImpl(repository, usersRepositoryLocal);
    }

    static TasksListPresenter createTaskListPresenter(Context context) {
        return new TasksListPresenter(createTasksInteractor(context));
    }


    static TaskPresenter createTaskPresenter(Context context) {
        return new TaskPresenter(createTasksInteractor(context));
    }


    static NewTaskPresenter createNewTaskPresenter(Context context) {
        return new NewTaskPresenter(createTasksInteractor(context));
    }


}
