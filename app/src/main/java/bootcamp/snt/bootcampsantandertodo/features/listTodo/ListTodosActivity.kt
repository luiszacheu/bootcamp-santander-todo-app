package bootcamp.snt.bootcampsantandertodo.features.listTodo

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityListTodoBinding
import bootcamp.snt.bootcampsantandertodo.data.DataSourceLocal
import bootcamp.snt.bootcampsantandertodo.features.addTodo.CreateTodoActivity
import bootcamp.snt.bootcampsantandertodo.features.detailTodo.DetailTodoActivity
import bootcamp.snt.bootcampsantandertodo.utils.Constants

class ListTodosActivity : AppCompatActivity() {

    // Referência da classe criada pelo ViewBinding para inflarmos o layout na activity
    private lateinit var binding: ActivityListTodoBinding

    private lateinit var todoListAdapter: TodoListAdapter

    private val createActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

        when(result.resultCode) {
            Constants.CODE_RESULT_CREATE_SUCCESS -> todoListAdapter.notifyDataSetChanged()
            Constants.CODE_RESULT_REMOVE_SUCCESS -> {
                result.data?.getIntExtra(Constants.KEY_EXTRA_TODO_INDEX, 0)?.let {
                    todoListAdapter.removeTodo(it)
                }
            }
        }
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
            todoListAdapter.updateList(DataSourceLocal.getAllTodos())
        }
    }

    private fun detailTodo(todoId: Int, position: Int){
        val intent = Intent(this, DetailTodoActivity::class.java)
        intent.putExtra(Constants.KEY_EXTRA_TODO_ID, todoId)
        intent.putExtra(Constants.KEY_EXTRA_TODO_INDEX, position)
        createActivityLauncher.launch(intent)
    }

}