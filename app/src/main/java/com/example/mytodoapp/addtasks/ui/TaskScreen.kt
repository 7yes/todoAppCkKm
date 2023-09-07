package com.example.mytodoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mytodoapp.R

@Composable
fun TasksScreen(taskViewModel: TaskViewModel) {
    val showDialog by taskViewModel.showDialog.observeAsState(initial = false)
    Box(Modifier.fillMaxSize()) {
        FabDialog(Modifier.align(Alignment.BottomEnd).padding(16.dp),taskViewModel)
        AddTasksDialog(show = showDialog, onDismiss = {taskViewModel.onDialogClose()}, onTaskAdded = {taskViewModel.onTasksCreated(it)})
    }
}


@Composable
fun FabDialog(modifier: Modifier, taskViewModel: TaskViewModel) {
    FloatingActionButton(onClick = { taskViewModel.onShowDialogClick()}, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded:(String)->Unit) {
    var myTask by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)) {
                Text(text = stringResource(R.string.add_task), fontSize = 20.sp, modifier = Modifier.align(
                    Alignment.CenterHorizontally), fontWeight = FontWeight.Bold)
                MySpacer()
                TextField(value = myTask, onValueChange = { myTask = it }, singleLine = true, maxLines = 1)
                MySpacer()
                Button(onClick = {
                    onTaskAdded(myTask)
                }, modifier = Modifier.align(Alignment.End)) {
                    Text(text = stringResource(R.string.add_task))
                }
            }
        }
    }
}

@Composable
fun MySpacer() = Spacer(modifier = Modifier.size(16.dp))

