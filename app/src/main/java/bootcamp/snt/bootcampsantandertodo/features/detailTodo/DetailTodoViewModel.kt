package bootcamp.snt.bootcampsantandertodo.features.detailTodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.data.repository.RepositoryCallback
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo

class DetailTodoViewModel : ViewModel() {

    private val _getStateView = MutableLiveData<StateView<Todo>>()
    val getStateView: LiveData<StateView<Todo>>
        get() = _getStateView

    private val _removeStateView = MutableLiveData<StateView<Todo?>>()
    val removeStateView: LiveData<StateView<Todo?>>
        get() = _removeStateView

    fun showDataFromDataSource(idTodo: Int) {

        DataSourceRemote().getById(idTodo, object : RepositoryCallback<Todo> {
            override fun onSucesso(todos: Todo?) {
                todos?.let {
                    _getStateView.value = StateView.DataLoaded(todos)
                } ?: run {
                    _getStateView.value = StateView.Error(Exception("Item retornado vazio!"))
                }
            }

            override fun onFalha(t: Throwable) {
                _getStateView.value = StateView.Error(Exception("Ocorreu falha ao obter o item!"))
            }
        })
    }

    fun removeTodo(todo: Todo) {
        DataSourceRemote().remove(todo.id, object : RepositoryCallback<Todo> {
            override fun onSucesso(todos: Todo?) {
                _removeStateView.value = StateView.DataLoaded(todos)
            }

            override fun onFalha(t: Throwable) {
                _removeStateView.value = StateView.Error(Exception("Ocorreu falha ao remover o item!"))
            }
        })
    }
}