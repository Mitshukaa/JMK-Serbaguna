package com.example.myapplication

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TaskItemCBinding
import java.time.format.DateTimeFormatter

class TaskItemVH(
    private val context: Context,
    private val binding: TaskItemCBinding,
    private val clickListener: TaskItemCL
): RecyclerView.ViewHolder(binding.root)
{
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItem(taskItem: TaksItem)
    {
        binding.name.text = taskItem.name

        if (taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskItem.imageResource())


        binding.completeButton.setOnClickListener {
            if (taskItem.isCompleted()) {
                taskItem.completedDate = null// Menandai tugas sebagai tidak selesai
                binding.name.paintFlags = binding.name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv() // Menghapus garis strikethrough
                binding.dueTime.paintFlags = binding.dueTime.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv() // Menghapus garis strikethrough
            } else {
                clickListener.completeTaskItem(taskItem)
            }
            // Memperbarui gambar tombol setelah perubahan status
            binding.completeButton.setImageResource(taskItem.imageResource())
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTime != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.dueTime.text = ""
    }
}