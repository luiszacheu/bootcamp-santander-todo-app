package bootcamp.snt.bootcampsantandertodo.data.repository

interface RepositoryCallback<T> {
    fun onSucesso(todos: T?)
    fun onFalha(t: Throwable)
}