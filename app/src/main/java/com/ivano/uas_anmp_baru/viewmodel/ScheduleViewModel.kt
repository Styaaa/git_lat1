package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_anmp_baru.model.Game
import com.ivano.uas_anmp_baru.model.Schedule

class ScheduleViewModel(aplication:Application):AndroidViewModel(aplication) {
    val scheduleLD = MutableLiveData<ArrayList<Schedule>>()
    val scheduleData = MutableLiveData<Schedule>()
    val scheduleLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchSchedule(){

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.61/file_php/get_schedule.php" //IP DIGANTI SESUAI DENGAN IP JARINGAN YANG SEDANG DIGUNAKAN

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Schedule>>() { }.type
                val result = Gson().fromJson<List<Schedule>>(it, sType)
                scheduleLD.value = result as ArrayList<Schedule>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}