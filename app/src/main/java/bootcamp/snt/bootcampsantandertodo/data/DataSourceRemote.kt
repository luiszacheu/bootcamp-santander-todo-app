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
        val call: Call<List<Todo>> = client.service().getAllTodos()
        call.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                Log.i("TESTE", "SUCESSO")
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
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