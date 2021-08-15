package bootcamp.snt.bootcampsantandertodo.features.di

import bootcamp.snt.bootcampsantandertodo.features.listTodo.viewmodel.ListTodosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel { ListTodosViewModel(get()) }
}