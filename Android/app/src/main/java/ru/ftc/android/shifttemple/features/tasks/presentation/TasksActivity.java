package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.presentation.UserLoginLoginActivity;

public final class TasksActivity extends BaseActivity implements TasksListView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton createTaskButton;
    private TasksAdapter adapter;

    private TasksListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.tasks_activity);

        initView();
    }


    private void initView() {
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.tasks_recycle_view);
        createTaskButton = findViewById(R.id.create_task_button);

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateTaskClicked();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                presenter.onRefreshTasks();
            }
        });

        adapter = new TasksAdapter(this, new TasksAdapter.SelectTaskListener() {
            @Override
            public void onTaskSelect(Task task) {
                presenter.onTaskSelected(task);
            }

            @Override
            public void onTaskLongClick(Task task) {

                presenter.onTaskLongClicked(task);



            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTaskList(List<Task> list) {
        adapter.setTasks(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<TasksListView> getPresenter() {
        presenter = PresenterFactory.createTaskListPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showLoginForm() {
        Intent intent = new Intent(TasksActivity.this, UserLoginLoginActivity.class);

        startActivity(intent);
    }

    @Override
    public void showTask(Task task) {
        TaskActivity.start(this, task.getId());
    }
}
