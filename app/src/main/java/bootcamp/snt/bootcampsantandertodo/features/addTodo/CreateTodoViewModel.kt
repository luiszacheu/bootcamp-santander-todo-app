package bootcamp.snt.bootcampsantandertodo.features.addTodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.data.repository.RepositoryCallback
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo
import java.util.*

class CreateTodoViewModel : ViewModel() {

    private val _stateView = MutableLiveData<StateView<Todo>>()
    val stateView: LiveData<StateView<Todo>>
        get() = _stateView

    fun createNewTodo(title: String, description: String) {
        if (_stateView.value != null) return
        _stateView.value = StateView.Loading

        val todo = Todo(Random(100L).nextInt(), title, description, false)

        DataSourceRemote().create(todo, object : RepositoryCallback<Todo> {
            override fun onSucesso(todos: Todo?) {
                todos?.let {
                    _stateView.value = StateView.DataLoaded(todos)
                } ?: run {
                    _stateView.value = StateView.Error(Exception("Item criado vazio!"))
                }
            }

            override fun onFalha(t: Throwable) {
                _stateView.value = StateView.Error(Exception("Ocorreu falha na criação!"))
            }
        })
    }
}