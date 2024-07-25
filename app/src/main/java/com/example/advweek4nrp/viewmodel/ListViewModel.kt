package com.example.advweek4nrp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4nrp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {

        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Student>>() { }.type
                val result = Gson().fromJson<ArrayList<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>?
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}