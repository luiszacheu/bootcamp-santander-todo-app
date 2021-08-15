package bootcamp.snt.bootcampsantandertodo.utils

import kotlinx.coroutines.flow.*

/**
 * Responsible for the logic of requesting new data and caching to the database
 * @param query gets the data from database
 * @param fetch fetching data from API
 * @param saveFetchResult gets data from API and saves to database
 * @param shouldFetch decide if new data should be fetched, defaults to true
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first() // Get the first emitted list to compare

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}