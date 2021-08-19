package bootcamp.snt.bootcampsantandertodo.data.repository

import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.model.Todo
import java.lang.Exception

class TodoRepositoryImpl : TodoRepository {
    override suspend fun getAll(): ResultRepository<List<Todo>> {
        val listTodos = DataSourceRemote().getAll()
        return if (listTodos.isNotEmpty()) {
            ResultRepository.Success(listTodos)
        } else {
            ResultRepository.Error(Exception("Erro!"))
        }

    }

}