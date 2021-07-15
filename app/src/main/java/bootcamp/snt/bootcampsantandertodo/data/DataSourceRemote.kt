package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.data.network.NetworkClient
import bootcamp.snt.bootcampsantandertodo.model.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceRemote {

    private val service = NetworkClient().service()

    fun getAll(callback: TodosCallback) {

        val call: Call<List<Todo>> = service.getAllTodos()
        call.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun getById(id: Int, callback: TodoCallback) {
        val call: Call<Todo> = service.getTodoById(id)

        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun create(todo: Todo, callback: TodoCallback) {
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

    fun remove(id: Int, callback: TodoCallback) {
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