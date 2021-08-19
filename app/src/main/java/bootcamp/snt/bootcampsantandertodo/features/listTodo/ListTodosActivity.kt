package bootcamp.snt.bootcampsantandertodo.features.listTodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepositoryImpl
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityListTodoBinding
import bootcamp.snt.bootcampsantandertodo.features.addTodo.CreateTodoActivity
import bootcamp.snt.bootcampsantandertodo.features.detailTodo.DetailTodoActivity
import bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel.ListTodosViewModel
import bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel.ListTodosViewModelFactory
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.utils.Constants

class ListTodosActivity : AppCompatActivity() {

    // Referência da classe criada pelo ViewBinding para inflarmos o layout na activity
    private lateinit var binding: ActivityListTodoBinding

    private lateinit var todoListAdapter: TodoListAdapter

    private val repository: TodoRepository by lazy {
        TodoRepositoryImpl()
    }

    private val viewModel: ListTodosViewModel by lazy {
        ViewModelProvider(this, ListTodosViewModelFactory(repository)).get(ListTodosViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Preparando e vinculando layout(XML) usando View Binding
        binding = ActivityListTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.title = getString(R.string.app_name)

        //Adicionando evento de click no botão para adicionar nova tarefa
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@ListTodosActivity, CreateTodoActivity::class.java)
            createActivityLauncher.launch(intent)
        }

        // Instanciando o adapter para setarmos na instancia de recyclerView
        todoListAdapter = TodoListAdapter { todo, position ->
            detailTodo(todo.id, position)
        }

        // Instanciando RecyclerView e passando uma lista de itens iniciais
        binding.rvListTodos.apply {
            adapter = todoListAdapter
            layoutManager = LinearLayoutManager(this@ListTodosActivity)
        }

        viewModel.stateView.observe(this, Observer {  stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is StateView.DataLoaded -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    todoListAdapter.updateList(stateView.data)
                }
                is StateView.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    Toast.makeText(this@ListTodosActivity, stateView.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getAllTodos()
    }

    private fun detailTodo(todoId: Int, position: Int){
        val intent = Intent(this, DetailTodoActivity::class.java)
        intent.putExtra(Constants.KEY_EXTRA_TODO_ID, todoId)
        intent.putExtra(Constants.KEY_EXTRA_TODO_INDEX, position)
        createActivityLauncher.launch(intent)
    }

    private val createActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

        when(result.resultCode) {
            Constants.CODE_RESULT_CREATE_SUCCESS -> {
                viewModel.getAllTodos()
            }
            Constants.CODE_RESULT_REMOVE_SUCCESS -> {
                result.data?.getIntExtra(Constants.KEY_EXTRA_TODO_INDEX, 0)?.let {
                    viewModel.getAllTodos()
                }
            }
        }
    }
}