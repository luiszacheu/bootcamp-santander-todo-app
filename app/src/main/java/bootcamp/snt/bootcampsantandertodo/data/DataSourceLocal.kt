package bootcamp.snt.bootcampsantandertodo.data

import bootcamp.snt.bootcampsantandertodo.model.Todo

object DataSourceLocal {
    private val todoList = initialList()

    fun getAllTodos() : List<Todo> {
        return todoList
    }

    fun removeTodo(todo: Todo) {
        todoList.remove(todo)
    }

    fun getTodoById(id: Int): Todo {
        return todoList.single { it.id == id }
    }

    fun createTodo(newTodo: Todo){
        todoList.add(0, newTodo)
    }

    private fun initialList() : MutableList<Todo> {
        return mutableListOf(
            Todo(1, "Titulo 1", "Conteúdo 1", false),
            Todo(2, "Titulo 2", "Conteúdo 2", false),
            Todo(3, "Titulo 3", "Conteúdo 3", false),
            Todo(4, "Titulo 4", "Conteúdo 4", false),
            Todo(5, "Titulo 5", "Conteúdo 5", false)
        )
    }
}