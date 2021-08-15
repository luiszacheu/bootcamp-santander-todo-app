package bootcamp.snt.bootcampsantandertodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import bootcamp.snt.bootcampsantandertodo.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}