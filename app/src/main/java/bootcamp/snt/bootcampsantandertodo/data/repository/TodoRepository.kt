package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.model.Todo

interface TodoRepository {
    fun getAll(callback: RepositoryCallback<List<Todo>>)

}