package com.example.project

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import kotlin.math.roundToInt

class AuthInit(viewModel: viewModel, signInLauncher: ActivityResultLauncher<Intent>) {
    companion object {
        private const val TAG = "AuthInit"
    }
    fun drawGraph(viewModel: viewModel){
        var freetimeAmount= intArrayOf(0,0,0,0,0)
        var freetimeCount= intArrayOf(0,0,0,0,0)
        var difLevelAmount = intArrayOf(0,0,0,0,0)
        var difLevelCount = intArrayOf(0,0,0,0,0)
        var workPerncentageAmount = intArrayOf(0,0,0,0,0)
        var workPerncentageCount = intArrayOf(0,0,0,0,0)
        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("day").get()
            .addOnSuccessListener {
                if(it.documents.size > 0){
                    for (i in 0..it.documents.size - 1) {
                        //e
                        if(it.documents.get(i).data?.get("e").toString().toInt() < 2){
                            freetimeAmount[0] += it.documents.get(i).data?.get("s").toString().toInt()
                            freetimeCount[0]++
                        }
                        else if(it.documents.get(i).data?.get("e").toString().toInt() < 4){
                            freetimeAmount[1] += it.documents.get(i).data?.get("s").toString().toInt()
                            freetimeCount[1]++
                        }
                        else if(it.documents.get(i).data?.get("e").toString().toInt() < 6){
                            freetimeAmount[2] += it.documents.get(i).data?.get("s").toString().toInt()
                            freetimeCount[2]++
                        }
                        else if(it.documents.get(i).data?.get("e").toString().toInt() < 8){
                            freetimeAmount[3] += it.documents.get(i).data?.get("s").toString().toInt()
                            freetimeCount[3]++
                        }
                        else{
                            freetimeAmount[4] += it.documents.get(i).data?.get("s").toString().toInt()
                            freetimeCount[4]++
                        }
                        //f
                        if(it.documents.get(i).data?.get("f").toString().toInt() < 20){
                            difLevelAmount[0] += it.documents.get(i).data?.get("s").toString().toInt()
                            difLevelCount[0]++
                        }
                        else if(it.documents.get(i).data?.get("f").toString().toInt() < 40){
                            difLevelAmount[1] += it.documents.get(i).data?.get("s").toString().toInt()
                            difLevelCount[1]++
                        }
                        else if(it.documents.get(i).data?.get("f").toString().toInt() < 60){
                            difLevelAmount[2] += it.documents.get(i).data?.get("s").toString().toInt()
                            difLevelCount[2]++
                        }
                        else if(it.documents.get(i).data?.get("f").toString().toInt() < 80){
                            difLevelAmount[3] += it.documents.get(i).data?.get("s").toString().toInt()
                            difLevelCount[3]++
                        }
                        else{
                            difLevelAmount[4] += it.documents.get(i).data?.get("s").toString().toInt()
                            difLevelCount[4]++
                        }
                        //l
                        if(it.documents.get(i).data?.get("l").toString().toInt() == 1){
                            workPerncentageAmount[0] += it.documents.get(i).data?.get("s").toString().toInt()
                            workPerncentageCount[0]++
                        }
                        else if(it.documents.get(i).data?.get("l").toString().toInt() == 2){
                            workPerncentageAmount[1] += it.documents.get(i).data?.get("s").toString().toInt()
                            workPerncentageCount[1]++
                        }
                        else if(it.documents.get(i).data?.get("l").toString().toInt() == 3){
                            workPerncentageAmount[2] += it.documents.get(i).data?.get("s").toString().toInt()
                            workPerncentageCount[2]++
                        }
                        else if(it.documents.get(i).data?.get("l").toString().toInt() == 4){
                            workPerncentageAmount[3] += it.documents.get(i).data?.get("s").toString().toInt()
                            workPerncentageCount[3]++
                        }
                        else{
                            workPerncentageAmount[4] += it.documents.get(i).data?.get("s").toString().toInt()
                            workPerncentageCount[4]++
                        }
                    }
                    var t1 = intArrayOf(0,0,0,0,0)
                    var t2 = intArrayOf(0,0,0,0,0)
                    var t3 = intArrayOf(0,0,0,0,0)
                    for (i in 0..4){
                        if(freetimeCount[i] != 0){
                            t1[i] = (freetimeAmount[i].toDouble()/freetimeCount[i].toDouble()).roundToInt()
                        }
                        if(difLevelAmount[i] != 0){
                            t2[i] = (difLevelAmount[i].toDouble()/difLevelCount[i].toDouble()).roundToInt()
                        }
                        if(workPerncentageCount[i] != 0){
                            t3[i] = (workPerncentageAmount[i].toDouble()/workPerncentageCount[i].toDouble()).roundToInt()
                        }
                    }
                    viewModel.freetimeGraph.postValue(t1)
                    viewModel.difLevelGraph.postValue(t2)
                    viewModel.workPerncentageGraph.postValue(t3)
                }
            }
            .addOnFailureListener {
                Log.d("XXX", "falsely done")
            }
    }
    fun addReport(f:Int, l:Int, e:Int, s:Int){
        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        data class submission(val f:Int, val l:Int, val e:Int, val s:Int)
        val sub = submission(f, l, e, s)
        val current = LocalDateTime.now()
        db.collection("day").document(
            current.toString()).set(sub).addOnSuccessListener { Log.d("XXX", "Upload Done") }
            .addOnFailureListener { Log.d("XXX", "falsely done")}
    }
    fun updateTask(t:task){
        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("dailyWork").document(
            t.name).set(t).addOnSuccessListener { Log.d("XXX", "Upload Done") }
            .addOnFailureListener { Log.d("XXX", "falsely done") }
    }
    fun removeTask(viewModel: viewModel){
        val t = viewModel.tasks.getValue()!!
        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        for(t:task in t){
            db.collection("dailyWork").document(t.name).delete().addOnSuccessListener { Log.d("XXX", "Delete Done") }
                .addOnFailureListener { Log.d("XXX", "falsely done") }
        }
    }

    fun updateFree(i:Int){
        try {
            val data = hashMapOf("t" to i)
            val db:FirebaseFirestore = FirebaseFirestore.getInstance()
            db.collection("freeTime").document(
                    "7qrn7xri8DYeUtLb5ybo").set(data).addOnSuccessListener { Log.d("XXX", "Upload Done") }
                .addOnFailureListener { Log.d("XXX", "falsely done") }
        }
        catch (e: Exception){
            Log.d("XXX", "falsely done")
        }
    }
    fun getFree(viewModel: viewModel){
        try {
            val db:FirebaseFirestore = FirebaseFirestore.getInstance()
            db.collection("freeTime").get().addOnSuccessListener {
                Log.d("XXX", "Upload Done")
                val t = it.documents.get(0).data?.get("t").toString().toInt()
                viewModel.timeSpent = t
                viewModel.freetime.setValue(t)
            }
                .addOnFailureListener { Log.d("XXX", "falsely done") }
        }
        catch (e: Exception){
            Log.d("XXX", "falsely done")
        }
    }

    fun addDaily(t:task){
        try {
            val db:FirebaseFirestore = FirebaseFirestore.getInstance()
            db.collection("dailyWork").document(t.name).set(t).addOnSuccessListener { Log.d("XXX", "Upload Done") }
                .addOnFailureListener { Log.d("XXX", "falsely done") }
        }
        catch (e: Exception){
            Log.d("XXX", "falsely done")
        }
    }

    fun getDaily(viewModel: viewModel){
        try {
            val db:FirebaseFirestore = FirebaseFirestore.getInstance()
            db.collection("dailyWork").get()
                .addOnSuccessListener {
                    var wd = 0
                    viewModel.expectDailyTime = 0
                    viewModel.totalWork = 0
                    viewModel.timeSpent = viewModel.freetime.getValue()!!
                    if(it.documents.size > 0){
                        var f = false
                        if(it.documents.get(0).data?.get("done").toString() == "true")
                            f = true
                        val t = task(it.documents.get(0).data?.get("name").toString(),
                            it.documents.get(0).data?.get("dif").toString().toInt(),
                            it.documents.get(0).data?.get("expTime").toString().toInt(),
                            f,
                            it.documents.get(0).data?.get("timeSpent").toString().toInt()
                        )
                        if(it.documents.get(0).data?.get("done").toString().toBoolean()  == true){
                            wd = it.documents.get(0).data?.get("dif").toString().toInt() *
                                    it.documents.get(0).data?.get("expTime").toString().toInt()
                        }
                        viewModel.timeSpent += it.documents.get(0).data?.get("timeSpent").toString().toInt()
                        viewModel.totalWork = it.documents.get(0).data?.get("dif").toString().toInt() *
                            it.documents.get(0).data?.get("expTime").toString().toInt()
                        viewModel.expectDailyTime = it.documents.get(0).data?.get("expTime").toString().toInt()
                        val ts = mutableListOf(t)
                        if(it.documents.size > 1){
                            for(i in 1 .. it.documents.size-1){
                                var f = false
                                if(it.documents.get(i).data?.get("done").toString() == "true")
                                    f = true
                                val t = task(it.documents.get(i).data?.get("name").toString(),
                                    it.documents.get(i).data?.get("dif").toString().toInt(),
                                    it.documents.get(i).data?.get("expTime").toString().toInt(),
                                    f,
                                    it.documents.get(i).data?.get("timeSpent").toString().toInt())
                                ts.add(t)
                                if(it.documents.get(i).data?.get("done").toString().toBoolean() == true){
                                    wd += it.documents.get(i).data?.get("dif").toString().toInt() *
                                            it.documents.get(i).data?.get("expTime").toString().toInt()
                                }
                                viewModel.timeSpent += it.documents.get(i).data?.get("timeSpent").toString().toInt()
                                viewModel.totalWork += it.documents.get(i).data?.get("dif").toString().toInt() *
                                        it.documents.get(i).data?.get("expTime").toString().toInt()
                                viewModel.expectDailyTime += it.documents.get(i).data?.get("expTime").toString().toInt()
                            }
                        }
                        viewModel.workDone.postValue(0)
                        if(wd != null){
                            viewModel.workDone.postValue(wd)
                        }
                        viewModel.tasks.setValue(ts)
                    }
                    Log.d("XXX", "Update Done") }
                .addOnFailureListener { Log.d("XXX", "falsely done") }
        }
        catch (e: Exception){
            Log.d("XXX", "falsely done")
        }
    }

    init {
        val user = FirebaseAuth.getInstance().currentUser
        if(user == null) {
            Log.d(TAG, "XXX user null")
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build())
            // Create and launch sign-in intent
            // XXX Write me. Set authentication providers and start sign-in for user
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers).setIsSmartLockEnabled(false)
                .build()
            signInLauncher.launch(signInIntent)
            viewModel.user.setValue(user?.email)
        } else {
            viewModel.user.setValue(user.email)
            Log.d(TAG, "XXX user ${user.displayName} email ${user.email}")
        }
    }
}