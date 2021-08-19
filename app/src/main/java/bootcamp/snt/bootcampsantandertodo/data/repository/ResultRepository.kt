package bootcamp.snt.bootcampsantandertodo.data.repository

sealed class ResultRepository<out R> {
    data class Success<out T>(val data: T) : ResultRepository<T>()
    data class Error(val exception: Exception) : ResultRepository<Nothing>()
}