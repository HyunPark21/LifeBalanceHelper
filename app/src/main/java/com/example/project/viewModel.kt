package com.example.project

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.quoteAPI
import com.example.project.api.quoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewModel: ViewModel() {
    //variables for api
    val api = quoteAPI.create()
    val ret = quoteRepository(api)
    var quote = MutableLiveData<String>()
    var autor1 = ""
    var user = MutableLiveData<String>()
    lateinit var a:AuthInit
    //variables for task
    var freetime = MutableLiveData<Int>()
    var workDone= MutableLiveData<Int>()
    var timeSpent = 0
    var expectDailyTime = 0
    var totalWork = 0
    var tasks = MutableLiveData<MutableList<task>?>()
    //variables for graph
    var freetimeGraph = MutableLiveData<IntArray>()
    var difLevelGraph = MutableLiveData<IntArray>()
    var workPerncentageGraph = MutableLiveData<IntArray>()
    fun fetchQuote(){
        viewModelScope.launch (
            context = viewModelScope.coroutineContext + Dispatchers.IO
        ) {
            val fact = ret.fetchFact()
            quote.postValue(fact.quote1)
            autor1 = fact.author
        }
    }
    fun addTask(t:task){
        if(tasks.value == null){
            val ts = mutableListOf(t)
            tasks.setValue(ts)
        }
        else{
            val ts = tasks.value
            ts!!.add(t)
            tasks.setValue(ts)
        }
    }
    init {
        freetime.postValue(0)
        workDone.postValue(0)
        user.setValue("")
        fetchQuote()
        freetimeGraph.postValue(intArrayOf(0,0,0,0,0))
        difLevelGraph.postValue(intArrayOf(0,0,0,0,0))
        workPerncentageGraph.postValue(intArrayOf(0,0,0,0,0))
    }
}