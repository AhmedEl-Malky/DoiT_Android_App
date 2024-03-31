package com.elmalky.doitapp.ui.Fragments

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.elmalky.doitapp.R
import com.elmalky.doitapp.adapters.TODOInteractions
import com.elmalky.doitapp.adapters.TODORecycler
import com.elmalky.doitapp.databinding.BottomSheetBinding
import com.elmalky.doitapp.databinding.FragmentTODOBinding
import com.elmalky.doitapp.databinding.PriorityAlertDialogBinding
import com.elmalky.doitapp.models.dataBase.entities.TODO
import com.elmalky.doitapp.viewModels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TODOFragment : Fragment(), TODOInteractions {
    lateinit var binder: FragmentTODOBinding
    lateinit var todoFab: FloatingActionButton
    lateinit var bottomDialog: Dialog
    lateinit var priorityDialogBinder: PriorityAlertDialogBinding
    lateinit var bottomDialogBinder: BottomSheetBinding
    lateinit var priorityAlertDialog: AlertDialog
    lateinit var inputTodoText: EditText
    lateinit var submitTodo: ImageButton
    val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binder = FragmentTODOBinding.inflate(layoutInflater)
        binder.lifecycleOwner = this
        binder.vm = viewModel
        priorityDialogBinder = PriorityAlertDialogBinding.inflate(layoutInflater)
        bottomDialogBinder = BottomSheetBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.todoRecycler.adapter = TODORecycler(emptyList(), this)
        todoFab = requireActivity().findViewById(R.id.todo_fab)
        setUpBottomDialog()
        setUpPriorityDialog()
        todoFab.setOnClickListener {
            setUpBottomDialog()
            bottomDialog.show()
        }
    }


    private fun setUpPriorityDialog() {
        val view = LayoutInflater.from(requireActivity())
            .inflate(R.layout.priority_alert_dialog, priorityDialogBinder.priorityDialog)
        val priorityDialogBuilder = AlertDialog.Builder(requireActivity())
        priorityDialogBuilder.setView(view)
        priorityAlertDialog = priorityDialogBuilder.create()
        priorityAlertDialog.window?.setBackgroundDrawable(ColorDrawable(0))

    }

    private fun setUpBottomDialog() {
        bottomDialog = Dialog(requireActivity())
        bottomDialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.bottom_sheet)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.attributes?.windowAnimations = R.style.bottom_dialog
            window?.setGravity(Gravity.BOTTOM)
        }

        val closeTodo = bottomDialog.findViewById<ImageButton>(R.id.close_todo)
        submitTodo = bottomDialog.findViewById(R.id.submit_todo)
        inputTodoText = bottomDialog.findViewById(R.id.input_todo_text)
        val priorityBtn = bottomDialog.findViewById<ImageButton>(R.id.priority_btn)
        closeTodo.setOnClickListener {
            bottomDialog.dismiss()
        }
        submitTodo.setOnClickListener {
            val todoTask = inputTodoText.text.toString()
            viewModel.addNewTODO(todoTask)
            bottomDialog.dismiss()
        }
        inputTodoText.addTextChangedListener {
            if (it != null) {
                if (it.isEmpty())
                    submitTodo.visibility = View.GONE
                else
                    submitTodo.visibility = View.VISIBLE
            }
        }
        inputTodoText.requestFocus()
        bottomDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        priorityBtn.setOnClickListener {
            showPriorityDialog()
        }
    }

    fun showPriorityDialog() {
        priorityAlertDialog.show()
    }

    override fun deleteTODO(todo: TODO) {
        viewModel.deleteTODO(todo)
    }

    override fun onTODOClickListener(todo: TODO) {
        bottomDialog.show()
        inputTodoText.setText(todo.task)
        inputTodoText.setSelection(inputTodoText.text.length)
        submitTodo.setOnClickListener {
            viewModel.updateTODO(todo, inputTodoText.text.toString())
            bottomDialog.dismiss()
        }
    }


}