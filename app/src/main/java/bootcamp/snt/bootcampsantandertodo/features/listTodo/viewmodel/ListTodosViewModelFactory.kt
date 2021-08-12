package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.snt.bootcampsantandertodo.data.TodoRepository

class ListTodosViewModelFactory(private val repository: TodoRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListTodosViewModel(repository) as T
    }
}