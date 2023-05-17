package com.katheryn.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.katheryn.todoapp.R
import com.katheryn.todoapp.model.Todo

class TodoListAdapter(
    val todoList: ArrayList<Todo>,
    val adapterOnClick: (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
//        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        checktask.text = todoList[position].title

        holder.view.checkTask.setText(todoList[position].title.toString())

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}