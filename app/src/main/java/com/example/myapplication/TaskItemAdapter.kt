package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TaskItemCBinding

class TaskItemAdapter(
    private val taskItems: List<TaksItem>,
    private val clickListener: TaskItemCL
): RecyclerView.Adapter<TaskItemVH>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemVH {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCBinding.inflate(from, parent, false)
        return TaskItemVH(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: TaskItemVH, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }

    override fun getItemCount(): Int = taskItems.size
}