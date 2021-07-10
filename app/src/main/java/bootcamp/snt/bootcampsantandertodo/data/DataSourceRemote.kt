package bootcamp.snt.bootcampsantandertodo.data

import android.util.Log
import bootcamp.snt.bootcampsantandertodo.data.network.NetworkClient
import bootcamp.snt.bootcampsantandertodo.model.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceRemote {

    private val client = NetworkClient()

    fun getAll() {
        Log.i("TESTE", "get all")
        val call: Call<Todo> = client.service().getAllTodos()
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                Log.i("TESTE", "SUCESSO")
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Log.i("TESTE", "FALHA")
            }
        })
    }

    fun getById(id: Int) {
        client.service().getTodoById(id)
    }

    fun create(todo: Todo) {
        client.service().createTodo()
    }

    fun removeTodo(id: Int) {
        client.service().deleteTodoById(id)
    }
}