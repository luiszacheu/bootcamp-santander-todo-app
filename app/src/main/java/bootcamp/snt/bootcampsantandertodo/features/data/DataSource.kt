package bootcamp.snt.bootcampsantandertodo.features.data

import bootcamp.snt.bootcampsantandertodo.features.model.Todo

interface DataSource {

    fun getAllTodos() : MutableList<Todo>

    fun removeTodo(todo: Todo)

    fun getTodoById(id: Int): Todo

    fun createTodo(newTodo: Todo)
}