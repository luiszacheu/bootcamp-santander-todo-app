package bootcamp.snt.bootcampsantandertodo.features

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bootcamp.snt.bootcampsantandertodo.R
import bootcamp.snt.bootcampsantandertodo.databinding.ActivityListTodoBinding

class ListTodosActivity : AppCompatActivity() {

    // Referência da classe criada pelo ViewBinding para inflarmos o layout na activity
    private lateinit var binding: ActivityListTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Preparando e vinculando layout(XML) usando View Binding
        binding = ActivityListTodoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.title = getString(R.string.app_name)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@ListTodosActivity, CreateTodoActivity::class.java)
            startActivity(intent)
        }
    }

    fun listTodosList() {

    }


}