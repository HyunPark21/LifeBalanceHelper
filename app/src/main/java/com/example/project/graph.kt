package com.example.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.GraphBinding

class graph : Fragment() {
    private var _binding: GraphBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: viewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var c = 0
        viewModel.a.drawGraph(viewModel)
        viewModel.workPerncentageGraph.observeForever {
            drawPercentageGraph()
            binding.b.setOnClickListener {
                if(c == 0){
                    clearGraph()
                    drawdifGraph()
                    binding.graphname.text = "Difficulty level X Satifaction"
                    binding.r2c1.text = "1"
                    binding.r3c1.text = "2"
                    binding.r4c1.text = "3"
                    binding.r5c1.text = "4"
                    binding.r6c1.text = "5"
                    c++
                }
                else if(c == 1){
                    clearGraph()
                    drawFreeGraph()
                    binding.graphname.text = "Freetime X Satifaction"
                    binding.r2c1.text = "0-2"
                    binding.r3c1.text = "2-4"
                    binding.r4c1.text = "4-6"
                    binding.r5c1.text = "6-8"
                    binding.r6c1.text = "8-"
                    c++
                }
                else if(c == 2){
                    findNavController().navigate(R.id.action_graph_to_SecondFragment)
                }
            }
        }
    }


    fun clearGraph(){
            binding.r2c2.setBackgroundColor(0)
            binding.r2c3.setBackgroundColor(0)
            binding.r2c4.setBackgroundColor(0)
            binding.r2c5.setBackgroundColor(0)
            binding.r2c6.setBackgroundColor(0)

            binding.r3c2.setBackgroundColor(0)
            binding.r3c3.setBackgroundColor(0)
            binding.r3c4.setBackgroundColor(0)
            binding.r3c5.setBackgroundColor(0)
            binding.r3c6.setBackgroundColor(0)

            binding.r4c2.setBackgroundColor(0)
            binding.r4c3.setBackgroundColor(0)
            binding.r4c4.setBackgroundColor(0)
            binding.r4c5.setBackgroundColor(0)
            binding.r4c6.setBackgroundColor(0)

            binding.r5c2.setBackgroundColor(0)
            binding.r5c3.setBackgroundColor(0)
            binding.r5c4.setBackgroundColor(0)
            binding.r5c5.setBackgroundColor(0)
            binding.r5c6.setBackgroundColor(0)

            binding.r6c2.setBackgroundColor(0)
            binding.r6c3.setBackgroundColor(0)
            binding.r6c4.setBackgroundColor(0)
            binding.r6c5.setBackgroundColor(0)
            binding.r6c6.setBackgroundColor(0)
    }



    fun drawPercentageGraph(){
        val v = viewModel.workPerncentageGraph.value!!
        if(v[0] > 0)
            binding.r2c2.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 1)
            binding.r2c3.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 2)
            binding.r2c4.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 3)
            binding.r2c5.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 4)
            binding.r2c6.setBackgroundColor(0xFF000000.toInt())

        if(v[1] > 0)
            binding.r3c2.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 1)
            binding.r3c3.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 2)
            binding.r3c4.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 3)
            binding.r3c5.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 4)
            binding.r3c6.setBackgroundColor(0xFF000000.toInt())

        if(v[2] > 0)
            binding.r4c2.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 1)
            binding.r4c3.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 2)
            binding.r4c4.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 3)
            binding.r4c5.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 4)
            binding.r4c6.setBackgroundColor(0xFF000000.toInt())

        if(v[3] > 0)
            binding.r5c2.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 1)
            binding.r5c3.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 2)
            binding.r5c4.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 3)
            binding.r5c5.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 4)
            binding.r5c6.setBackgroundColor(0xFF000000.toInt())

        if(v[4] > 0)
            binding.r6c2.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 1)
            binding.r6c3.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 2)
            binding.r6c4.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 3)
            binding.r6c5.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 4)
            binding.r6c6.setBackgroundColor(0xFF000000.toInt())
    }

    fun drawFreeGraph(){
        val v = viewModel.freetimeGraph.value!!
        if(v[0] > 0)
            binding.r2c2.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 1)
            binding.r2c3.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 2)
            binding.r2c4.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 3)
            binding.r2c5.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 4)
            binding.r2c6.setBackgroundColor(0xFF000000.toInt())

        if(v[1] > 0)
            binding.r3c2.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 1)
            binding.r3c3.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 2)
            binding.r3c4.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 3)
            binding.r3c5.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 4)
            binding.r3c6.setBackgroundColor(0xFF000000.toInt())

        if(v[2] > 0)
            binding.r4c2.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 1)
            binding.r4c3.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 2)
            binding.r4c4.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 3)
            binding.r4c5.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 4)
            binding.r4c6.setBackgroundColor(0xFF000000.toInt())

        if(v[3] > 0)
            binding.r5c2.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 1)
            binding.r5c3.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 2)
            binding.r5c4.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 3)
            binding.r5c5.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 4)
            binding.r5c6.setBackgroundColor(0xFF000000.toInt())

        if(v[4] > 0)
            binding.r6c2.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 1)
            binding.r6c3.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 2)
            binding.r6c4.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 3)
            binding.r6c5.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 4)
            binding.r6c6.setBackgroundColor(0xFF000000.toInt())
    }

    fun drawdifGraph(){
        val v = viewModel.difLevelGraph.value!!
        if(v[0] > 0)
            binding.r2c2.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 1)
            binding.r2c3.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 2)
            binding.r2c4.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 3)
            binding.r2c5.setBackgroundColor(0xFF000000.toInt())
        if(v[0] > 4)
            binding.r2c6.setBackgroundColor(0xFF000000.toInt())

        if(v[1] > 0)
            binding.r3c2.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 1)
            binding.r3c3.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 2)
            binding.r3c4.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 3)
            binding.r3c5.setBackgroundColor(0xFF000000.toInt())
        if(v[1] > 4)
            binding.r3c6.setBackgroundColor(0xFF000000.toInt())

        if(v[2] > 0)
            binding.r4c2.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 1)
            binding.r4c3.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 2)
            binding.r4c4.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 3)
            binding.r4c5.setBackgroundColor(0xFF000000.toInt())
        if(v[2] > 4)
            binding.r4c6.setBackgroundColor(0xFF000000.toInt())

        if(v[3] > 0)
            binding.r5c2.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 1)
            binding.r5c3.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 2)
            binding.r5c4.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 3)
            binding.r5c5.setBackgroundColor(0xFF000000.toInt())
        if(v[3] > 4)
            binding.r5c6.setBackgroundColor(0xFF000000.toInt())

        if(v[4] > 0)
            binding.r6c2.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 1)
            binding.r6c3.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 2)
            binding.r6c4.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 3)
            binding.r6c5.setBackgroundColor(0xFF000000.toInt())
        if(v[4] > 4)
            binding.r6c6.setBackgroundColor(0xFF000000.toInt())
    }
}