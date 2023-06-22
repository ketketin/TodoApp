package com.katheryn.todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.katheryn.todoapp.R
import com.katheryn.todoapp.databinding.FragmentEditTodoBinding
import com.katheryn.todoapp.databinding.TodoItemLayoutBinding
import com.katheryn.todoapp.model.Todo
import com.katheryn.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(),TodoSaveChangesClick, RadioClick {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Update", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo,
        container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.radioListener = this
        dataBinding.saveListener = this
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtNotes = view.findViewById<TextView>(R.id.txtNotes)

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        txtJudulTodo.text = "Edit Todo"

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.text = "Save Changes"

//        val btnCreateTodo = view.findViewById<Button>(R.id.btnCreateTodo)
        val radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)

//        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
//        Navigation.findNavController(v).navigate(action)

        observeViewModel()

//        btnCreateTodo.setOnClickListener{
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
//            radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            val txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
//            when(it.priority) {
//                1 -> view.findViewById<RadioButton>(R.id.radioLow).isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }
        })
    }

}