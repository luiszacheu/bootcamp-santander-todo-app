package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository

class ListTodosViewModel(repository: TodoRepository) : ViewModel() {

    val todos = repository.getAllTodos().asLiveData()
}