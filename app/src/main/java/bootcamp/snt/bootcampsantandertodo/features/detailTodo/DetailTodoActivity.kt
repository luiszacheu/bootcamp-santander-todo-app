package bootcamp.snt.bootcampsantandertodo.features.detailTodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityDetailTodoBinding
import bootcamp.snt.bootcampsantandertodo.data.DataSourceLocal
import bootcamp.snt.bootcampsantandertodo.model.Todo
import bootcamp.snt.bootcampsantandertodo.utils.Constants

class DetailTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTodoBinding

    private lateinit var todo: Todo

    private var positionOfTodo: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        toolbar.title = getString(R.string.detail_todo_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        positionOfTodo = intent.extras?.getInt(Constants.KEY_EXTRA_TODO_INDEX)
        intent.extras?.getInt(Constants.KEY_EXTRA_TODO_ID)?.let { todoId ->
            showDataFromDataSource(todoId)
        }

        binding.btnRemove.setOnClickListener {
            removeTodo(todo, positionOfTodo)
        }
    }

    private fun removeTodo(todo: Todo, position: Int?) {
        DataSourceLocal.removeTodo(todo)
        val data = Intent()
        data.putExtra(Constants.KEY_EXTRA_TODO_INDEX, position)
        setResult(Constants.CODE_RESULT_REMOVE_SUCCESS, data)
        finish()
    }

    private fun showDataFromDataSource(idTodo: Int) {
        todo = DataSourceLocal.getTodoById(idTodo)
        binding.apply {
            tvHeader.text =
                String.format(getString(R.string.detail_todo_tv_header_text, todo.title, todo.id))
            tvTitleTodoValue.text = todo.title
            tvDescriptionTodoValue.text = todo.description
        }
    }

}