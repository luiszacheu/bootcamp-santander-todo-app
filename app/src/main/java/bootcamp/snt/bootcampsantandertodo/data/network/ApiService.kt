package bootcamp.snt.bootcampsantandertodo.data.network

import bootcamp.snt.bootcampsantandertodo.model.Todo
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("todos")
    fun createTodo(@Body todo: Todo) : Call<Todo>

    @GET("todos")
    fun getAllTodos() : Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodoById(@Path("id") id: Int) : Call<Todo>

    @PUT("todos/{id}")
    fun updateTodoById(@Path("id") id: Int, @Body novoTodo: Todo) : Call<Todo>

    @DELETE("todos/{id}")
    fun deleteTodoById(@Path("id") id: Int) : Call<Todo>

}