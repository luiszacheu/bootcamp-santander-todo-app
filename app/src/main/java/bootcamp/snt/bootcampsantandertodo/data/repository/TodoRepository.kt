package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.model.Todo

interface TodoRepository {
    suspend fun getAll() : List<Todo>

}