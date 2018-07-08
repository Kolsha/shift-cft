package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.presentation.UserLoginLoginActivity;

public final class TaskActivity extends BaseActivity implements TaskView {

    private static final String TASK_ID = "taskId";

    private RecyclerView recyclerView;
    private TaskDetailAdapter adapter;
    private TaskPresenter presenter;
    private Button responceButton;

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

        //TODO test code
//        Task task = new Task("asda", "Titles dfd sf sdf sd fs", "asd d sa dsa j fghfjk dgh jkfdhgh jkfdhjkg fhdkjg hfdkjg hfdkjjg hfdjkg hfjkghdfjk h gjkfdhgjkfgh kjfdhgjk fdhgjk hdgjkdf hdsa ");
//        adapter.setTask(task);
//
//        ArrayList<Bid> bids = new ArrayList<>(10);
//
//        for(int i = 0; i < 10; i++) {
//            bids.add(new Bid("qweq", "asd sadas das dssdf ds fdsa"));
//        }
//
//        adapter.setBids(bids);
        //TODO test code
    }


    private void initView() {
        recyclerView = findViewById(R.id.dataView);

        adapter = new TaskDetailAdapter(this, new TaskDetailAdapter.SelectBidListener() {
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

        responceButton = findViewById(R.id.responseButton);

        responceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);

                final EditText addCommentaryView = new EditText(TaskActivity.this);


                builder.setTitle(R.string.response_label)
                        .setMessage(R.string.add_comment)
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(true)
                        .setView(addCommentaryView)
                        .setNegativeButton(R.string.cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(R.string.respond,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        presenter.sendBid(task_id, addCommentaryView.getText().toString());


                                        dialog.cancel();
                                    }
                                });

                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });

    }

    @Override
    public void showProgress() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
//        mSwipeRefreshLayout.setRefreshing(false);
//        recyclerView.setVisibility(View.VISIBLE);
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
        adapter.setTask(task);
        if (task.getTaskIsMine()) {
            responceButton.setVisibility(View.GONE);
        } else {
            responceButton.setVisibility(View.VISIBLE);
        }
    }




    @Override
    //TODO: ask
    public void showConfirmationDialog(final Bid bid) {
        /*AlertDialog.Builder builder;

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
                .show();*/
    }

    @Override
    public void showResponseSuccess() {
        Toast.makeText(TaskActivity.this, R.string.respond_toast,
                Toast.LENGTH_LONG).show();
    }
}
