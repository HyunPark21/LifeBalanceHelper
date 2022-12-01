package com.example.project

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.EndtaskBinding
import kotlin.math.roundToInt

class endDay : Fragment() {
    private var _binding: EndtaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: viewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EndtaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.done.text = "You finished ${viewModel.workDone.getValue()!! * 100 / viewModel.totalWork}% of your day"
        binding.difday.text = "Average Level of the day is ${(viewModel.totalWork.toDouble() / viewModel.expectDailyTime.toDouble()).roundToInt()}"
        binding.enjoy.text = "You enjoy ${viewModel.freetime.getValue()} hours"
        val numbers = arrayListOf<Int>(1,2,3,4,5)
        val adapter: ArrayAdapter<Int> = ArrayAdapter(this.requireContext(),
            R.layout.simple_dropdown_item_1line,numbers)
        binding.sat.setAdapter(adapter)
        binding.cancel.setOnClickListener {
            findNavController().navigate(com.example.project.R.id.action_endDay_to_SecondFragment)
        }
        binding.clear.setOnClickListener {
            viewModel.workDone.postValue(0)
            viewModel.totalWork = 0
            viewModel.a.updateFree(0)
            viewModel.freetime.postValue(0)
            viewModel.a.removeTask(viewModel)
            if(viewModel.tasks.value!!.size >= 1) {
                var a = viewModel.tasks.value!!
                for (i in 0..viewModel.tasks.value!!.size-1)
                    a.removeAt(0)
                viewModel.tasks.postValue(a)
            }
            findNavController().navigate(com.example.project.R.id.action_endDay_to_SecondFragment)
        }
        binding.end.setOnClickListener {
            viewModel.a.addReport(viewModel.workDone.getValue()!! * 100 / viewModel.totalWork,
                (viewModel.totalWork.toDouble() / viewModel.expectDailyTime.toDouble()).roundToInt(),
                viewModel.freetime.getValue()!!,
                binding.sat.selectedItem.toString().toInt()
            )
            viewModel.a.updateFree(0)
            viewModel.freetime.postValue(0)
            viewModel.a.removeTask(viewModel)
            if(viewModel.tasks.value!!.size >= 1) {
                var a = viewModel.tasks.value!!
                for (i in 0..viewModel.tasks.value!!.size-1)
                    a.removeAt(0)
                viewModel.tasks.postValue(a)
            }
            viewModel.workDone.postValue(0)
            viewModel.totalWork = 0
            findNavController().navigate(com.example.project.R.id.action_endDay_to_SecondFragment)
        }
    }
}