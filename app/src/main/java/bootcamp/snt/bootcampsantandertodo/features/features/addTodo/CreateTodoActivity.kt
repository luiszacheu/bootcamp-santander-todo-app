package bootcamp.snt.bootcampsantandertodo.features.features.addTodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityCreateTodoBinding
import bootcamp.snt.bootcampsantandertodo.features.data.DataSourceLocal
import bootcamp.snt.bootcampsantandertodo.features.model.Todo
import bootcamp.snt.bootcampsantandertodo.features.utils.Constants
import java.util.*

class CreateTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        toolbar.title = getString(R.string.create_todo_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.btnCreate.setOnClickListener {
            val title = binding.tietTitleTodo.text.toString()
            val description = binding.tietDescriptionTodo.text.toString()

            //Tratamento para mostrar um erro quando nenhum campo foi preenchido
            setErrorIfFieldsNotFill(title, description).apply {
                if (this) {
                    createNewTodo(title, description)
                }
            }
        }
    }

    private fun setErrorIfFieldsNotFill(title: String, description: String): Boolean {
        if (title.isBlank()) {
            binding.tilTitleTodo.error = "Campo obrigatório!"
            return false
        } else {
            binding.tilTitleTodo.error = null
        }

        if (description.isBlank()) {
            binding.tilDescriptionTodo.error = "Campo obrigatório"
            return false
        } else {
            binding.tilDescriptionTodo.error = null
        }

        return true
    }

    private fun createNewTodo(title: String, description: String) {
        val todo = Todo(Random(1000L).nextInt(), title, description, false)
        DataSourceLocal.createTodo(todo)
        setResult(Constants.CODE_RESULT_CREATE_SUCCESS)
        finish()
    }
}

