package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;

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

    private static final String TASK_ID = "taskId";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    //private FloatingActionButton createTaskButton;
    private BidsAdapter adapter;

    private TextView titleView;

    private TextView descriptionView;

    private  TextView dateView;

    private TaskPresenter presenter;

    private String task_id;

    public static void start(Context context, final String task_id) {
        Intent intent = new Intent(context, TaskActivity.class);

        Bundle b = new Bundle();
        b.putString(TASK_ID, task_id);
        intent.putExtras(b);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);



        Bundle b = getIntent().getExtras();
        if (b != null)
            task_id = b.getString(TASK_ID);
        presenter.setTaskId(task_id);
        initView();
    }


    private void initView() {
        //showError(task_id);
        mSwipeRefreshLayout = findViewById(R.id.bids_swipeRefreshLayout);
        recyclerView = findViewById(R.id.bids_recycle_view);
        //createTaskButton = findViewById(R.id.create_task_button);

        /*createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateTaskClicked();
            }
        });
        */

        titleView = findViewById(R.id.task_title);
        descriptionView = findViewById(R.id.task_description);
        dateView = findViewById(R.id.task_date);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                presenter.onRefreshTask();
            }
        });

        adapter = new BidsAdapter(this, new BidsAdapter.SelectBidListener() {
            @Override
            public void onBidSelect(Bid bid) {
                presenter.onBidSelected(bid);
            }

            @Override
            public void onBidLongClick(Bid bid) {
                presenter.onBidLongClicked(bid);
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
    public void showBidList(List<Bid> list) {
        adapter.setBids(list);
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

    @Override
    public void showTask(Task task) {
        //TODO: show task
        titleView.setText(task.getTitle());
        descriptionView.setText(task.getDescription());
        dateView.setText(task.getDate());
    }

    @Override
    //TODO: ask
    public void showConfirmationDialog(final Bid bid) {
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose bid")
                .setMessage("Are you sure you want to choose this bid?\n" + bid.getText())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.onBidSelected(bid);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
