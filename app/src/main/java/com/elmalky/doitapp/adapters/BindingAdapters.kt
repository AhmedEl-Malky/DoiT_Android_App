package com.elmalky.doitapp.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["recyclerVisibility"])
fun <T> showRecycler(view: View, items: List<T>?) {
    if (items != null) {
        if (items.isEmpty())
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["lottieVisibility"])
fun <T> showLottie(view: View, items: List<T>?) {
    if (items != null) {
        if (items.isEmpty())
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["items"])
fun <T> setItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        val check = items.size > view.adapter!!.itemCount
        (view.adapter as BaseRecyclerAdapter<T>?)?.updateItems(items)
        if (check)
            view.scrollToPosition(0)
    } else
        (view.adapter as BaseRecyclerAdapter<T>?)?.updateItems(emptyList())
}

@BindingAdapter(value = ["priorityVisibility"])
fun priorityVisibility(view: View, priority: String?) {
    if (priority != null) {
        if (priority == "#FFFFFF")
            view.visibility = View.GONE
        else {
            view.visibility = View.VISIBLE
            view.setBackgroundColor(Color.parseColor(priority))
        }
    }
}

@BindingAdapter(value = ["priorityFlag"])
fun priorityFlagVisibility(view: View, priority: String?) {
    if (priority != null) {
        if (priority.isEmpty())
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["radioColor"])
fun setRadioColor(view: View, priority: String?) {
    (view as RadioButton).buttonTintList = ColorStateList.valueOf(Color.parseColor(priority))
}

@BindingAdapter(value = ["noteColor"])
fun changeNoteColor(view: View, color: String?) {
    if (color != null)
        view.setBackgroundColor(Color.parseColor(color))
}

@BindingAdapter(value = ["submitVisibilityContent"])
fun submitVisibilityContent(view: View, content: String?) {
    if (content != null) {
        if (content.isEmpty())
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["submitVisibilityTitle"])
fun submitVisibilityTitle(view: View, content: String?) {
    if (content != null) {
        if (content.isEmpty())
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["paletteColor"])
fun changePaletteColor(view: View, color: String?) {
    if (color != null) {
        view.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }
}

@BindingAdapter(value = ["radioCheck"])
fun changeRadioCheck(view: View, state: Boolean?) {
    view as RadioButton
    if (state != null) {
        view.isChecked = state
    }
}

@BindingAdapter(value = ["noteTitleVisibility"])
fun changeNoteTitleVisibility(view: View, title: String?) {
    if (title != null)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}