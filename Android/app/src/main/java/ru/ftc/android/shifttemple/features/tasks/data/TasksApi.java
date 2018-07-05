package ru.ftc.android.shifttemple.features.tasks.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.Wrapper;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

public interface TasksApi {


    @GET("tasks")
    Call<Wrapper<List<Task>>> getTaskList();

    @GET("tasks/{id}")
    Call<Wrapper<Task>> getTask(@Path("id") String id);

    @POST("tasks")
    Call<Wrapper<Task>> createTask(@Body Task task);

    @DELETE("tasks/{id}")
    Call<Wrapper<Success>> deleteTask(@Path("id") String id);



    @GET("/tasks/{id}/bids")
    Call<Wrapper<List<Bid>>> getTaskBids(@Path("id") String id);


    @GET("/tasks/{id}/bids")
    Call<Wrapper<Bid>> createTaskBid(@Path("id") String id, @Body Bid bid);

}
