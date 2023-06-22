package com.katheryn.todoapp.model

import androidx.room.*
import java.util.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg  todo:Todo)

//    @Query("SELECT * FROM todo")
//    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("UPDATE todo SET is_done= 1 WHERE uuid= :uuid")
    suspend fun updateTodoIsDone(is_done: Int, uuid:Int)

    @Query("UPDATE todo SET title= :title, notes=:notes, priority=:priority WHERE uuid=:id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Delete
    fun deleteTodo(todo:Todo)


}