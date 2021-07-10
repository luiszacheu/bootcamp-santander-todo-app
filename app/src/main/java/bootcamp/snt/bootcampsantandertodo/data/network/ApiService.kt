package bootcamp.snt.bootcampsantandertodo.data.network

import bootcamp.snt.bootcampsantandertodo.model.Todo
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("todos")
    fun createTodo()

    @GET("todos")
    fun getAllTodos() : Call<Todo>

    @GET("todos/{id}")
    fun getTodoById(@Path("id") id: Int)

    @PUT("todos/{id}")
    fun updateTodoById(@Path("id") id: Int)

    @DELETE("todos/{id}")
    fun deleteTodoById(@Path("id") id: Int)

}