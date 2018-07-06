package ru.ftc.android.shifttemple.features.tasks.data;


import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

public interface TasksDataSource {
    void getTasks(Carry<List<Task>> carry);

    void getTask(String id, Carry<Task> carry);

    void createTask(Task task, Carry<Task> carry);

    void deleteTask(String id, Carry<Success> carry);


    void getTaskBids(String id, Carry<List<Bid>> carry);

    void createTaskBid(String id, Bid bid, Carry<Bid> carry);
}
