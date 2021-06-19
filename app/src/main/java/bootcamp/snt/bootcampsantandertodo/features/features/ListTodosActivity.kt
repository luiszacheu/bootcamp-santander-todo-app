package bootcamp.snt.bootcampsantandertodo.features.features

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityListTodoBinding
import bootcamp.snt.bootcampsantandertodo.features.model.Todo

class ListTodosActivity : AppCompatActivity() {

    // Referência da classe criada pelo ViewBinding para inflarmos o layout na activity
    private lateinit var binding: ActivityListTodoBinding

    private lateinit var todoListAdapter: TodoListAdapter

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
            startActivity(intent)
        }

        todoListAdapter = TodoListAdapter(getTodoList())
        binding.rvListTodos.apply {
            adapter = todoListAdapter
            layoutManager = LinearLayoutManager(this@ListTodosActivity)
        }
    }

    fun getTodoList() : List<Todo> {
        return listOf(
            Todo(1, "Titulo 1", "Conteúdo 1", false),
            Todo(2, "Titulo 2", "Conteúdo 2", false),
            Todo(3, "Titulo 3", "Conteúdo 3", false),
            Todo(4, "Titulo 4", "Conteúdo 4", false),
            Todo(5, "Titulo 5", "Conteúdo 5", false)
        )
    }


}