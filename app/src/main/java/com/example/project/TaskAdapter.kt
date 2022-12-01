package com.example.project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.RowTaskBinding
import com.google.android.material.snackbar.Snackbar

class TaskAdapter(val viewModel: viewModel): ListAdapter<task, TaskAdapter.VH>(diff()) {
    inner class VH(val RowTaskBinding: RowTaskBinding)
        : RecyclerView.ViewHolder(RowTaskBinding.root)
    class diff: DiffUtil.ItemCallback<task>() {
        override fun areItemsTheSame(oldItem: task, newItem: task): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: task, newItem: task): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.dif == newItem.dif &&
                    oldItem.expTime == newItem.expTime
        }

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val binding = holder.RowTaskBinding
        binding.d.text = viewModel.tasks.getValue()?.get(position)?.dif.toString()
        binding.e.text = viewModel.tasks.getValue()?.get(position)?.expTime.toString()
        binding.n.text = viewModel.tasks.getValue()?.get(position)?.name.toString()
        Log.d("XXXX", viewModel.tasks.getValue()!!.get(position)!!.isDone.toString())
        if(viewModel.tasks.getValue()!!.get(position)!!.isDone  == true){
            binding.i.text = "O"
        }
        else{
            binding.i.text = "X"
        }
        binding.t.text = viewModel.tasks.getValue()?.get(position)?.timeSpent.toString()
        binding.i.setOnClickListener {
            if(viewModel.tasks.getValue()!!.get(position)!!.isDone  == true){
                viewModel.tasks.getValue()!!.get(position)!!.isDone = false
                binding.i.text = "X"
                var temp = viewModel.workDone.getValue()
                temp = temp!! - viewModel.tasks.getValue()!!.get(position)!!.dif * viewModel.tasks.getValue()!!.get(position)!!.expTime
                viewModel.workDone.setValue(temp)
            }
            else{
                viewModel.tasks.getValue()!!.get(position)!!.isDone = true
                binding.i.text = "O"
                var temp = viewModel.workDone.getValue()
                temp = temp!! + viewModel.tasks.getValue()!!.get(position)!!.dif * viewModel.tasks.getValue()!!.get(position)!!.expTime
                viewModel.workDone.setValue(temp)
            }
            viewModel.a.updateTask(viewModel.tasks.getValue()!!.get(position)!!)
        }
        binding.t.setOnClickListener {
            if(viewModel.timeSpent + 1 <= 20) {
                viewModel.tasks.getValue()?.get(position)?.timeSpent =
                    1 + viewModel.tasks.getValue()!!.get(position)!!.timeSpent
                viewModel.a.updateTask(viewModel.tasks.getValue()!!.get(position)!!)
                binding.t.text = viewModel.tasks.getValue()!!.get(position)!!.timeSpent.toString()
                viewModel.timeSpent += 1
            }
            else{
                var snackbar = Snackbar.make(binding.root, "You need to sleep now", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = RowTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return VH(binding)
    }


}