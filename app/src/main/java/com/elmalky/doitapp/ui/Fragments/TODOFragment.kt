package com.elmalky.doitapp.ui.Fragments

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
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentTODOBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TODOFragment : Fragment() {
    lateinit var binder: FragmentTODOBinding
    lateinit var todoFab: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentTODOBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoFab = requireActivity().findViewById(R.id.todo_fab)
        binder.todoToolbar.subtitle = "todos_num"
        setUpBottomDialog()
    }

//    override fun onResume() {
//        super.onResume()
//        val animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.pop_up)
//        binder.todoFab.startAnimation(animation)
//
//    }

    //    override fun onPause() {
//        super.onPause()
//        val animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.pop_down)
//        binder.todoFab.startAnimation(animation)
//
//    }
    private fun setUpBottomDialog() {
        todoFab.setOnClickListener {
            showBottomDialog()
        }
    }

    private fun showBottomDialog() {
        val bottomDialog = Dialog(requireActivity())
//        submit_todo.isEnabled = false

        bottomDialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.bottom_sheet)
            show()
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.attributes?.windowAnimations = R.style.bottom_dialog
            window?.setGravity(Gravity.BOTTOM)

        }

        val close_todo = bottomDialog.findViewById<ImageButton>(R.id.close_todo)
        val submit_todo = bottomDialog.findViewById<ImageButton>(R.id.submit_todo)
        val todoInputText = bottomDialog.findViewById<EditText>(R.id.input_todo_text)
        todoInputText.requestFocus()

        close_todo.setOnClickListener {
            bottomDialog.dismiss()
        }
        submit_todo.visibility = View.INVISIBLE
        todoInputText.addTextChangedListener {
            if (it != null) {
                if (it.isNotEmpty()) {
                    submit_todo.visibility = View.VISIBLE
                } else {
                    submit_todo.visibility = View.INVISIBLE
                }
            }
        }
        bottomDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

    }

}