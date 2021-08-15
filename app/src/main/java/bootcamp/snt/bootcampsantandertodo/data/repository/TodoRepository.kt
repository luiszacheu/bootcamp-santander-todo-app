package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.model.Todo
import bootcamp.snt.bootcampsantandertodo.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAll(callback: RepositoryCallback<List<Todo>>)

    fun getAllTodos(): Flow<Resource<List<Todo>>>
}