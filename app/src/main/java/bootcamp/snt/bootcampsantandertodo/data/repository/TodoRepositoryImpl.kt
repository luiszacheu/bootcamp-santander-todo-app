package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.model.Todo

class TodoRepositoryImpl : TodoRepository {
    override suspend fun getAll() : List<Todo> {
        return DataSourceRemote().getAll()

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