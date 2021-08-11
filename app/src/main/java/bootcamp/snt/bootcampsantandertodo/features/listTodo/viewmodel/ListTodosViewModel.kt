package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.snt.bootcampsantandertodo.data.DataSourceRemote
import bootcamp.snt.bootcampsantandertodo.data.TodosCallback
import bootcamp.snt.bootcampsantandertodo.model.Todo
import kotlinx.coroutines.launch

class ListTodosViewModel : ViewModel() {

    private val _listTodos = MutableLiveData<List<Todo>?>()
    val listTodos: LiveData<List<Todo>?>
        get() = _listTodos

    fun getAllTodos() {
        Log.e("Passou aui", "Passou teste")
//      Local
//      todoListAdapter.updateList(DataSourceLocal.getAllTodos())

//      Remoto
        DataSourceRemote().getAll(object : TodosCallback {
            override fun onSucesso(todos: List<Todo>?) {
                todos?.let {
                    _listTodos.value = it
                }
            }

            override fun onFalha(t: Throwable) {
                _listTodos.value = null
            }
        })
    }
}