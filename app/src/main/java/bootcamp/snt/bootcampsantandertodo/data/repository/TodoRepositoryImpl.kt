package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.model.Todo
import bootcamp.snt.bootcampsantandertodo.model.Result

class TodoRepositoryImpl : TodoRepository {
    override suspend fun getAll() : Result<List<Todo>> {
        val result = DataSourceRemote().getAll()
        return if (result.isNotEmpty()) {
            Result.Success(result)
        } else {
            Result.Error("Falha")
        }

//        object : RepositoryCallback<List<Todo>> {
//            override fun onSucesso(todos: List<Todo>?) {
//                todos?.let {
//                    callback.onSucesso(todos)
//                }
//            }
//
//            override fun onFalha(t: Throwable) {
//                callback.onFalha(Exception("Ocorreu um erro ao recuperar os dados!"))
//
//            }
//        })
    }

}