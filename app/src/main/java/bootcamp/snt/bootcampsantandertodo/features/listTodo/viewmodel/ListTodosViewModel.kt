package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.snt.bootcampsantandertodo.data.repository.RepositoryCallback
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo

class ListTodosViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _stateView = MutableLiveData<StateView<List<Todo>>>()
    val stateView: LiveData<StateView<List<Todo>>>
        get() = _stateView

    fun getAllTodos() {
        _stateView.value = StateView.Loading

        repository.getAll(object : RepositoryCallback<List<Todo>> {
            override fun onSucesso(todos: List<Todo>?) {
                todos?.let {
                    _stateView.value = StateView.DataLoaded(it)
                }
            }

            override fun onFalha(t: Throwable) {
                _stateView.value = StateView.Error(Exception("Ocorreu um erro ao recuperar os dados!"))
            }
        })
    }
}