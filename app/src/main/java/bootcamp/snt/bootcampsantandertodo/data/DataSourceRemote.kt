package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.data.network.NetworkClient
import bootcamp.snt.bootcampsantandertodo.data.repository.RepositoryCallback
import bootcamp.snt.bootcampsantandertodo.model.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceRemote {

    private val service = NetworkClient().service()

    suspend fun getAll() = service.getAllTodos()

    suspend fun getById(id: Int) : Todo {
        return service.getTodoById(id)
    }

    fun create(todo: Todo, callback: RepositoryCallback<Todo>) {
        val call = service.createTodo(todo)
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun remove(id: Int, callback: RepositoryCallback<Todo>) {
        val call = service.deleteTodoById(id)
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }
}