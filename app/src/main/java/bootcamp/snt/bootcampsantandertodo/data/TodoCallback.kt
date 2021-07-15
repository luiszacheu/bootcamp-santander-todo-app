package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.model.Todo

interface TodoCallback {
    fun onSucesso(todos: Todo?)
    fun onFalha(t: Throwable)
}