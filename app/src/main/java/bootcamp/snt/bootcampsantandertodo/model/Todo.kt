package bootcamp.snt.bootcampsantandertodo.model

import com.google.gson.annotations.SerializedName

data class Todo(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val description: String,
        @SerializedName("done") val done: Boolean
)