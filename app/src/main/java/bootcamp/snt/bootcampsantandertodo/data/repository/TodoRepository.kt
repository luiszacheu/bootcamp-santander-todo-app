package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.model.Todo
import bootcamp.snt.bootcampsantandertodo.model.Result

interface TodoRepository {
    suspend fun getAll() : Result<List<Todo>>

}