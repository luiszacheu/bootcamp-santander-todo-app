package bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.snt.bootcampsantandertodo.data.repository.RepositoryCallback
import bootcamp.snt.bootcampsantandertodo.data.repository.ResultRepository
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListTodosViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _stateView = MutableLiveData<StateView<List<Todo>>>()
    val stateView: LiveData<StateView<List<Todo>>>
        get() = _stateView

    fun getAllTodos() {
        if (_stateView.value != null) return

        viewModelScope.launch {
            _stateView.value = StateView.Loading
            val result = try {
                repository.getAll()
            } catch (e: Exception) {
                ResultRepository.Error(java.lang.Exception("error"))
            }

            when(result) {
                is ResultRepository.Success -> {
                    _stateView.value = StateView.DataLoaded(result.data)
                }
                is ResultRepository.Error -> {
                    _stateView.value =
                        StateView.Error(Exception("Ocorreu um erro ao recuperar os dados!"))
                }
            }
        }
    }
}