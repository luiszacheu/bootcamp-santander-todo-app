package bootcamp.snt.bootcampsantandertodo.model

data class Todo(
    val id: Int,
    val title: String,
    val description: String,
    val done: Boolean
)