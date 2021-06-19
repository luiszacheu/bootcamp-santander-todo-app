package bootcamp.snt.bootcampsantandertodo.features.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.snt.bootcampsantandertodo.databinding.AdapterTodoBinding
import bootcamp.snt.bootcampsantandertodo.features.model.Todo

class TodoListAdapter(private val todoList: List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: AdapterTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.binding.apply {
            tvTitle.text = todo.title
            tvContent.text = todo.content
        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}