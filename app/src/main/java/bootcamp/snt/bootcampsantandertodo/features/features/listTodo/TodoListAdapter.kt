package bootcamp.snt.bootcampsantandertodo.features.features.listTodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.snt.bootcampsantandertodo.databinding.AdapterTodoBinding
import bootcamp.snt.bootcampsantandertodo.features.data.DataSourceLocal
import bootcamp.snt.bootcampsantandertodo.features.model.Todo

class TodoListAdapter(
    private val onClick: (Todo) -> Unit
) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val todoList = DataSourceLocal.getTodoList()

    inner class ViewHolder(private val binding: AdapterTodoBinding, val onClick: (Todo) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo){
            binding.root.setOnClickListener {
                onClick(todo)
            }

            binding.apply {
                tvTitle.text = todo.title
                tvContent.text = todo.description
            }
        }
    }

    fun updateList() {
        todoList.clear()
        todoList.addAll(DataSourceLocal.getTodoList())
    }

    fun removeTodo() {
        updateList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}