package bootcamp.snt.bootcampsantandertodo.data.repository

import androidx.room.withTransaction
import bootcamp.snt.bootcampsantandertodo.data.local.TodoDatabase
import bootcamp.snt.bootcampsantandertodo.data.network.ApiService
import bootcamp.snt.bootcampsantandertodo.utils.networkBoundResource

class TodoRepositoryImpl(private val db: TodoDatabase, private val api: ApiService) :
    TodoRepository {

    private val dao = db.todoDao() // will automatically be singleton

    override fun getAllTodos() = networkBoundResource(
        query = {
            dao.getAllTodos()
        },
        fetch = {
            api.getAllTodos()
        },
        saveFetchResult = { todos ->
            db.withTransaction {
                dao.deleteAllTodos()
                dao.insertTodos(todos)
            }
        }
    )
}