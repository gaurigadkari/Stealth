package com.example.blankspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.blankspace.ui.theme.BlankSpaceTheme


// 1. 2 elements 1 button 1 text counter which increments when we click on the button, default value of the counter is 1
// 2. 2nd button get todo button, on click we want to hit the below url corresponding to the counter value
// https://jsonplaceholder.typicode.com/todos/<int-id>
// {
//  "userId": 1,
//  "id": 1,
//  "title": "delectus aut autem",
//  "completed": false
//}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: TodoViewModel = ViewModelProvider(this, TodoViewModelFactory())[TodoViewModel::class.java]

        setContent {
            BlankSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowCounterValue(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ShowCounterValue(modifier: Modifier = Modifier,
                     viewModel: TodoViewModel
) {
    var counter by remember { mutableIntStateOf(1) }
    val todoTitle by viewModel.todoTitle
    val errorMessage by viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                counter++
            }, modifier = Modifier.padding(16.dp)
            ) {
                Text(stringResource(R.string.increment_button))
            }
            Text(text = counter.toString(), modifier = Modifier.padding(16.dp))

        }
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                viewModel.getTodoById(counter)
            }, modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.todo_button))
            }
            Text(
                text = errorMessage ?: "Title: ${todoTitle ?: "No data yet"}",
                color = if (errorMessage != null) Color.Red else Color.Unspecified,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}