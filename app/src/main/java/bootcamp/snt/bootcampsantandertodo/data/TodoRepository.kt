package bootcamp.snt.bootcampsantandertodo.data

interface TodoRepository {
    fun getAll(callback: TodosCallback)

}