package com.example.project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: viewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.TimeEnjoyment.text = "You play ${viewModel.freetime.getValue()} hours today"
        binding.TimeEnjoyment.setOnClickListener {
            if(viewModel.timeSpent < 20) {
                val check = viewModel.freetime.getValue()!! + 1
                viewModel.freetime.setValue(check)
                viewModel.timeSpent += 1
                viewModel.a.updateFree(check)
                binding.TimeEnjoyment.text = "You play ${viewModel.freetime.getValue()} hours today"
            }
            else{
                var snackbar = Snackbar.make(binding.root, "You need to sleep now", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
        binding.graph.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_graph)
        }
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_AddTaskFragment)
        }
        binding.end.setOnClickListener {
            if(viewModel.totalWork > 0)
                findNavController().navigate(R.id.action_SecondFragment_to_endDay)
            else{
                var snackbar = Snackbar.make(binding.root, "You need a task to submit", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
        viewModel.tasks.observeForever {
            var adapter = TaskAdapter(viewModel)
            adapter.submitList(viewModel.tasks.value)
            binding.rv.setAdapter(adapter)
            binding.rv.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.freetime.observeForever{
            binding.TimeEnjoyment.text = "You play ${viewModel.freetime.getValue()} hours today"
        }
        viewModel.workDone.observeForever{
            if(viewModel.totalWork == 0){
                binding.workDone.text =
                    "Your Task is  0% Done!"
            }
            else {
                binding.workDone.text =
                    "Your Task is  ${viewModel.workDone.getValue()!! * 100 / viewModel.totalWork}% Done!"
            }
        }
        viewModel.a.getFree(viewModel)
        viewModel.a.getDaily(viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}