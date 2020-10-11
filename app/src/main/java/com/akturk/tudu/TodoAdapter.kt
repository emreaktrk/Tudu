package com.akturk.tudu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akturk.domain.model.TodoItem
import com.akturk.tudu.databinding.ItemTodoBinding

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.Holder>() {

    var items: List<TodoItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.item = items[position]
    }

    override fun getItemCount(): Int = items.size
}