package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.profile.ProfileActivity;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.presentation.UserLoginLoginActivity;

public final class TaskActivity extends BaseActivity implements TaskView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    //private FloatingActionButton createTaskButton;
    private BidsAdapter adapter;

    private TaskPresenter presenter;

    private String task_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);



        Bundle b = getIntent().getExtras();
        if(b != null)
            task_id = b.getString("task_id");
        initView();
    }


    private void initView() {
        showError(task_id);
        //mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        //recyclerView = findViewById(R.id.tasks_recycle_view);
        //createTaskButton = findViewById(R.id.create_task_button);

        /*createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateTaskClicked();
            }
        });
        */

       /* mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                presenter.onRefreshTasks();
            }
        });

        adapter = new BidsAdapter(this, new BidsAdapter.SelectBidListener() {
            @Override
            public void onBidSelect(Bid bid) {
                //
            }

            @Override
            public void onBidLongClick(Bid bid) {
                //
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */
    }

    @Override
    public void showProgress() {
        //mSwipeRefreshLayout.setRefreshing(true);
        //recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        //mSwipeRefreshLayout.setRefreshing(false);
        //recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBidList(List<Bid> list) {
        //adapter.setBids(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<TaskView> getPresenter() {
        presenter = PresenterFactory.createTaskPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showLoginForm() {
        Intent intent = new Intent(TaskActivity.this, UserLoginLoginActivity.class);
        startActivity(intent);
    }
}
