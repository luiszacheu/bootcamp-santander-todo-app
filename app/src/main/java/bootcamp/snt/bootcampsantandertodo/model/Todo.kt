package bootcamp.snt.bootcampsantandertodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class Todo(
        @SerializedName("id") @PrimaryKey val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val description: String,
        @SerializedName("done") val done: Boolean
)