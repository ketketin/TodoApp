package com.katheryn.todoapp.view

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.katheryn.todoapp.R
import com.katheryn.todoapp.Util.TodoWorker
import com.katheryn.todoapp.Util.notificationHelper
import com.katheryn.todoapp.databinding.FragmentCreateTodoBinding
import com.katheryn.todoapp.model.Todo
import com.katheryn.todoapp.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment(), RadioClick, ButtonAddTodoClickListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding:FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_todo,
        container, false)
        return dataBinding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        dataBinding.todo = Todo("","",3, 0)
        viewModel=
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        dataBinding.listener = this
        dataBinding.radioListener = this

        val list = listOf(dataBinding.todo!!)
//        viewModel.addTodo(list)
//        Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
        val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
            .setInitialDelay(30, TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "Todo Created",
                "message" to "A new todo has been created! Stay Focus!"
            )).build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        Navigation.findNavController(v).popBackStack()

//        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
//        btnAdd.setOnClickListener {
//
//            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(workDataOf(
//                    "title" to "Todo Created",
//                    "message" to "A new todo has been created! Stay Focus!"))
//                .build()
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//
//            var radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
//
//            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
//            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
//            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt())
//            val list = listOf(todo)
//            viewModel.addTodo(todo)
//            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {

    }

    override fun onButtonAddTodo(v: View) {
        TODO("Not yet implemented")
    }
}