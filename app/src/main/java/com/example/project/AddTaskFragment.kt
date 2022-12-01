package com.example.project

import android.R
import android.R.string
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.AddtaskBinding
import com.google.android.material.snackbar.Snackbar

class AddTaskFragment: Fragment() {
    private var _binding: AddtaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: viewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddtaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numbers = arrayListOf<Int>(1,2,3,4,5)
        val adapter: ArrayAdapter<Int> = ArrayAdapter(this.requireContext(),R.layout.simple_dropdown_item_1line,numbers)
        binding.dif.setAdapter(adapter)
        binding.exp.setAdapter(adapter)
        binding.cancel.setOnClickListener {
            findNavController().navigate(com.example.project.R.id.action_AddTaskFragment_to_SecondFragment)
        }
        binding.create.setOnClickListener {
            if(binding.taskName.text.toString() != ""){
                if(binding.exp.selectedItem.toString().toInt() + viewModel.expectDailyTime <= 18) {
                    val t = task(
                        binding.taskName.text.toString(),
                        binding.dif.selectedItem.toString().toInt(),
                        binding.exp.selectedItem.toString().toInt(),
                        false,
                        0
                    )
                    viewModel.addTask(t)
                    viewModel.a.addDaily(t)
                    viewModel.expectDailyTime += binding.exp.selectedItem.toString().toInt()
                    viewModel.totalWork += binding.exp.selectedItem.toString().toInt() * binding.dif.selectedItem.toString().toInt()
                    findNavController().navigate(com.example.project.R.id.action_AddTaskFragment_to_SecondFragment)
                }
                else{
                    var snackbar = Snackbar.make(binding.root, "You expect to much for today", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            }
            else{
                var snackbar = Snackbar.make(binding.root, "Enter your task's name", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}