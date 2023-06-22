package com.katheryn.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.katheryn.todoapp.Util.buildDb
import com.katheryn.todoapp.model.Todo
import com.katheryn.todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }

    fun clearTask(todo: Todo){
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }

//    fun updateIsDone(is_done: Int, uuid: Int) {
//        launch {
//            val db = buildDb(getApplication())
//            db.todoDao().updateIsDone(is_done, uuid)
//            todoLD.value = db.todoDao().selectAllTodo()
//        }
//    }
}