package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

final class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    private final List<Task> Tasks = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectTaskListener selectTaskListener;

    TasksAdapter(Context context, SelectTaskListener selectTaskListener) {
        inflater = LayoutInflater.from(context);
        this.selectTaskListener = selectTaskListener;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView, selectTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.bind(Tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    public void setTasks(List<Task> TaskList) {
        Tasks.clear();
        Tasks.addAll(TaskList);
        notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        private final TextView taskTitleView;
        private final TextView taskDescriptionView;
        private final TextView taskDateView;
        private final SelectTaskListener selectTaskListener;

        TaskHolder(View view, SelectTaskListener selectTaskListener) {
            super(view);
            this.selectTaskListener = selectTaskListener;
            taskTitleView = view.findViewById(R.id.task_item_title);
            taskDescriptionView = view.findViewById(R.id.task_item_description);
            taskDateView = view.findViewById(R.id.task_item_date);
        }

        void bind(final Task task) {
            taskTitleView.setText(task.getTitle());
            taskDescriptionView.setText(task.getShortDescription()); // TODO: constant define .substring(0, 100)
            taskDateView.setText(task.getDate());


            ((View) taskTitleView.getParent())
                    .setBackgroundColor((task.getTaskIsMine() != null && task.getTaskIsMine()) ? Color.GREEN : Color.WHITE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectTaskListener.onTaskSelect(task);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectTaskListener.onTaskLongClick(task);
                    return true;
                }
            });

        }

    }

    interface SelectTaskListener {

        void onTaskSelect(Task Task);

        void onTaskLongClick(Task Task);

    }
}
