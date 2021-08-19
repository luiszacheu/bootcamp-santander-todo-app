package bootcamp.snt.bootcampsantandertodo.features.addTodo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityCreateTodoBinding
import bootcamp.snt.bootcampsantandertodo.model.StateView
import bootcamp.snt.bootcampsantandertodo.utils.Constants
import java.util.*

class CreateTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTodoBinding

    private val viewModel: CreateTodoViewModel by lazy {
        CreateTodoViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setToolbar()

        handleObservers()

        binding.btnCreate.setOnClickListener {
            val title = binding.tietTitleTodo.text.toString()
            val description = binding.tietDescriptionTodo.text.toString()

            //Tratamento para mostrar um erro quando nenhum campo foi preenchido
            setErrorIfFieldsNotFill(title, description).apply {
                if (this) {
                    viewModel.createNewTodo(title, description)
                }
            }
        }
    }

    private fun handleObservers() {
        viewModel.stateView.observe(this, {  stateView ->
            when (stateView) {
                is StateView.Loading -> { }
                is StateView.DataLoaded -> {
                    setResult(Constants.CODE_RESULT_CREATE_SUCCESS)
                    finish()
                }
                is StateView.Error -> {
                    Toast.makeText(this@CreateTodoActivity, stateView.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setErrorIfFieldsNotFill(title: String, description: String): Boolean {
        if (title.isBlank()) {
            binding.tilTitleTodo.error = getString(R.string.message_required_field)
            return false
        } else {
            binding.tilTitleTodo.error = null
        }

        if (description.isBlank()) {
            binding.tilDescriptionTodo.error = getString(R.string.message_required_field)
            return false
        } else {
            binding.tilDescriptionTodo.error = null
        }

        return true
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        toolbar.title = getString(R.string.create_todo_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
}

