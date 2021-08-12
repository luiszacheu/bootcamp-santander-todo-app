package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.model.Todo

class TodoRepositoryImpl : TodoRepository {
    override fun getAll(callback: TodosCallback) {
        //      Local
//      todoListAdapter.updateList(DataSourceLocal.getAllTodos())

//      Remote
        DataSourceRemote().getAll(object : TodosCallback {
            override fun onSucesso(todos: List<Todo>?) {
                todos?.let {
                    callback.onSucesso(todos)
                }
            }

            override fun onFalha(t: Throwable) {
                callback.onFalha(Exception("Ocorreu um erro ao recuperar os dados!"))

            }
        })
    }

}