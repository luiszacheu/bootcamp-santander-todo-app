package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.model.Todo

interface TodosCallback {
    fun onSucesso(todos: List<Todo>?)
    fun onFalha(t: Throwable)
}