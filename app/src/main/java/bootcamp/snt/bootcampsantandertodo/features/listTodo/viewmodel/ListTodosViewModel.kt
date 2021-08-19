package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo
import kotlinx.coroutines.launch
import bootcamp.snt.bootcampsantandertodo.model.Result

class ListTodosViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _stateView = MutableLiveData<StateView<List<Todo>>>()
    val stateView: LiveData<StateView<List<Todo>>>
        get() = _stateView

    fun getAllTodos() {
        viewModelScope.launch {
            _stateView.value = StateView.Loading

            val result = try {
                 repository.getAll()
            } catch (ex: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            _stateView.value = when(result) {
                is Result.Success -> StateView.DataLoaded(result.data)
                else -> StateView.Error(Exception("Ocorreu um erro ao recuperar os dados!"))
            }
        }
    }
}