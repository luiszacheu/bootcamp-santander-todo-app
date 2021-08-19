package bootcamp.snt.bootcampsantandertodo.features.detailTodo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityDetailTodoBinding
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.model.Todo
import bootcamp.snt.bootcampsantandertodo.utils.Constants

class DetailTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTodoBinding

    private lateinit var todo: Todo

    private var positionOfTodo: Int? = -1

    private val viewModel: DetailTodoViewModel by lazy {
        DetailTodoViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setToolbar()

        handleObservers()

        positionOfTodo = intent.extras?.getInt(Constants.KEY_EXTRA_TODO_INDEX)
        intent.extras?.getInt(Constants.KEY_EXTRA_TODO_ID)?.let { todoId ->
            viewModel.showDataFromDataSource(todoId)
        }

        binding.btnRemove.setOnClickListener {
            viewModel.removeTodo(todo)
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        toolbar.title = getString(R.string.detail_todo_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun handleObservers() {
        viewModel.getStateView.observe(this, {  stateView ->
            when (stateView) {
                is StateView.Loading -> { }
                is StateView.DataLoaded -> {
                    todo = stateView.data

                    binding.apply {
                        tvHeader.text =
                            String.format(getString(R.string.detail_todo_tv_header_text, todo.title, todo.id))
                        tvTitleTodoValue.text = todo.title
                        tvDescriptionTodoValue.text = todo.description
                    }
                }
                is StateView.Error -> {
                    Toast.makeText(this@DetailTodoActivity, stateView.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.removeStateView.observe(this, {  stateView ->
            when (stateView) {
                is StateView.Loading -> { }
                is StateView.DataLoaded -> {
                    val data = Intent()
                    data.putExtra(Constants.KEY_EXTRA_TODO_INDEX, positionOfTodo)
                    setResult(Constants.CODE_RESULT_REMOVE_SUCCESS, data)
                    finish()
                }
                is StateView.Error -> {
                    Toast.makeText(this@DetailTodoActivity, stateView.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}