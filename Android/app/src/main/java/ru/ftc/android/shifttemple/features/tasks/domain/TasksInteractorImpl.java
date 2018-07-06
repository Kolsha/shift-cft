package ru.ftc.android.shifttemple.features.tasks.domain;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepository;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.network.Carry;

public final class TasksInteractorImpl implements TasksInteractor {

    private final TasksRepository repository;

    private final UsersLocalRepository repositoryUsersLocal;

    public TasksInteractorImpl(TasksRepository repository, UsersLocalRepository repositoryUsersLocal) {
        this.repository = repository;
        this.repositoryUsersLocal = repositoryUsersLocal;
    }

    private Boolean checkUserToken(final Carry carry){
        if(repositoryUsersLocal.getUserToken().isEmpty()){
            carry.onFailure(new NotAuthorizedException());
            return false;
        }
        return true;
    }

    @Override
    public void loadTasks(Carry<List<Task>> carry) {
        if(!checkUserToken(carry)){
            return;
        }
        repository.loadTasks(carry);
    }

    @Override
    public void loadTask(String id, Carry<Task> carry) {
        if(!checkUserToken(carry)){
            return;
        }
        repository.loadTask(id, carry);
    }

    @Override
    public void createTask(Task task, Carry<ArrayList<Task>> carry) {
        repositoryUsersLocal.setUserToken("");
        if(!checkUserToken(carry)){
            return;
        }
        repository.createTask(task, carry);
    }

    @Override
    public void deleteTask(String id, Carry<Success> carry) {
        if(!checkUserToken(carry)){
            return;
        }
        repository.deleteTask(id, carry);
    }

    @Override
    public void createTaskBid(String id, Bid bid, Carry<Bid> carry) {
        if(!checkUserToken(carry)){
            return;
        }
        repository.createTaskBid(id, bid, carry);
    }

    @Override
    public void loadTaskBids(String id, Carry<List<Bid>> carry) {
        if(!checkUserToken(carry)){
            return;
        }
        repository.loadTaskBids(id, carry);
    }
}
