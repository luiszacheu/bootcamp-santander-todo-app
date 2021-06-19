package bootcamp.snt.bootcampsantandertodo.features.model

data class Todo(
    val id: Long,
    val title: String,
    val content: String,
    val done: Boolean
)