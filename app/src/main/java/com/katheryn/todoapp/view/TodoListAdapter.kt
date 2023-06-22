package com.katheryn.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.katheryn.todoapp.R
import com.katheryn.todoapp.databinding.TodoItemLayoutBinding
import com.katheryn.todoapp.model.Todo

class TodoListAdapter(
    val todoList: ArrayList<Todo>,
    val adapterOnClick: (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClick{

    class TodoViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCheckedChanges(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this


//        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        var imgEdit = holder.view.findViewById<ImageButton>(R.id.imgEdit)
//        checktask.text = todoList[position].title
//
//        checktask.setText(todoList[position].title.toString())
//
//        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked == true) {
//                adapterOnClick(todoList[position])
//            }
//        }
//
//        imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)

        Navigation.findNavController(v).navigate(action)
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