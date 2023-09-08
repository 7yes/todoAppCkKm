package com.example.mytodoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.mytodoapp.R
import com.example.mytodoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksScreen(taskViewModel: TaskViewModel) {
    val showDialog by taskViewModel.showDialog.observeAsState(initial = false)

    // conectandose al uiState en el vm, se creo un uiState aca que necesita un lifecycle y se usa
    // el uiState de aca con un when
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<TaskUiState>(
        initialValue = TaskUiState.Loading,
        key1 = lifecycle, key2 = taskViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            taskViewModel.uiState.collect { value = it }
        }
    }
    when (uiState) {
        is TaskUiState.Error -> {}
        TaskUiState.Loading -> { CircularProgressIndicator() }
        is TaskUiState.Success -> {
            Box(Modifier.fillMaxSize()) {
                FabDialog(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp), taskViewModel
                )
                AddTasksDialog(
                    show = showDialog,
                    onDismiss = { taskViewModel.onDialogClose() },
                    onTaskAdded = { taskViewModel.onTasksCreated(it) })
                TasksListRV((uiState as TaskUiState.Success).tasks, taskViewModel)
            }
        }
    }
}

@Composable
fun TasksListRV(tasks: List<TaskModel>, taskViewModel: TaskViewModel) {
    // val myTasks: List<TaskModel> = taskViewModel.tasks
    LazyColumn {
        //este items hay que importarlo bien es el de compose.foundation.lazy.items
        items(tasks, key = { it.id }) {
            ItemTask(taskModel = it, taskViewModel = taskViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, taskViewModel: TaskViewModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    taskViewModel.onItemRemove(taskModel)
                })

            }
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task, modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f), fontWeight = FontWeight.Bold
            )

            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { taskViewModel.onCheckBoxSelected(taskModel) })
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, taskViewModel: TaskViewModel) {
    FloatingActionButton(onClick = { taskViewModel.onShowDialogClick() }, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_task),
                    fontSize = 20.sp,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    ),
                    fontWeight = FontWeight.Bold
                )
                MySpacer()
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                MySpacer()
                Button(onClick = {
                    onTaskAdded(myTask)
                    myTask = ""
                }, modifier = Modifier.align(Alignment.End)) {
                    Text(text = stringResource(R.string.add_task))
                }
            }
        }
    }
}

@Composable
fun MySpacer() = Spacer(modifier = Modifier.size(16.dp))

