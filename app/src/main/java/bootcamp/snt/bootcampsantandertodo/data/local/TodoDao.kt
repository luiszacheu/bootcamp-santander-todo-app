package bootcamp.snt.bootcampsantandertodo.data.local

import androidx.room.*
import bootcamp.snt.bootcampsantandertodo.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<Todo>)

    @Query("DELETE FROM todos")
    suspend fun deleteAllTodos()
}