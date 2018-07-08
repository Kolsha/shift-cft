package ru.ftc.android.shifttemple.features.users.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.databinding.UserActivityBinding;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.presentation.TaskActivity;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

public class UserActivity extends BaseActivity implements UserView {
    private static final String USER_ID = "userId";

    private UserPresenter presenter;

    private String userId;

    private UserActivityBinding binding;


    public static void start(Context context, final String userId) {
        Intent intent = new Intent(context, UserActivity.class);

        Bundle b = new Bundle();
        b.putString(USER_ID, userId);
        intent.putExtras(b);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_activity);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            userId = b.getString(USER_ID);
        }

        presenter.loadUser(userId);
    }

    @Override
    protected MvpPresenter<UserView> getPresenter() {
        presenter = PresenterFactory.createUserPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showUser(User user) {
        binding.nameView.setText(user.getName());
        binding.surnameView.setText(user.getSurname());
        binding.phoneView.setText(user.getPhone());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
