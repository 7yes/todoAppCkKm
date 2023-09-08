package com.example.mytodoapp.addtasks.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.addtasks.domain.AddTaskUseCase
import com.example.mytodoapp.addtasks.domain.GetTasksUseCase
import com.example.mytodoapp.addtasks.ui.TaskUiState.Success
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {
    //conectar al flow del repositori mediante el UseCase, se mapeo como successsy el
    //uiState es lo que va a leer la UI
    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),TaskUiState.Loading )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(it: String) {
      //  _tasks.add(TaskModel(task = it, selected = false))
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = it,selected = false))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        //Actualizar checkBox
//        val index = _tasks.indexOf(taskModel)
//        _tasks[index] = _tasks[index].let {
//            it.copy(selected = !it.selected)
//        }
    }

    fun onItemRemove(taskModel: TaskModel) {
// borrar item
//         val task = _tasks.find { it == taskModel }
//        _tasks.remove(task)
    }
}
