package bootcamp.snt.bootcampsantandertodo.features.listTodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.snt.bootcampsantandertodo.databinding.AdapterTodoBinding
import bootcamp.snt.bootcampsantandertodo.model.Todo

class TodoListAdapter(
    private val onClick: (Todo, Int) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private var todoList = mutableListOf<Todo>()

    inner class ViewHolder(private val binding: AdapterTodoBinding, val onClick: (Todo, Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo, position: Int){
            binding.root.setOnClickListener {
                onClick(todo, position)
            }

            binding.apply {
                tvTitle.text = todo.title
                tvContent.text = todo.description
            }
        }
    }

    fun updateList(listOfTodos: MutableList<Todo>) {
        todoList = listOfTodos
    }

    fun removeTodo(position: Int) {
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo, position)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}