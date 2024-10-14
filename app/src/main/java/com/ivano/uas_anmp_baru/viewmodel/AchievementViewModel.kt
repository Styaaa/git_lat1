package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_anmp_baru.model.Achievement

class AchievementViewModel(application: Application): AndroidViewModel(application) {
    val achievementLD = MutableLiveData<ArrayList<Achievement>>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchAchievement(game_id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.61/file_php/get_achievements.php" //IP DIGANTI SESUAI DENGAN IP JARINGAN YANG SEDANG DIGUNAKAN

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("showvolley", it.toString())

                val sType = object : TypeToken<List<Achievement>>() { }.type
                val result = Gson().fromJson<List<Achievement>>(it, sType)
                achievementLD.value = result as ArrayList<Achievement>
                loadingLD.value = false

                Log.d("showvolley", result.toString())
            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
                loadingLD.value = false
            }
        ) {
            override fun getParams(): MutableMap<String, String>{
                val params = HashMap<String, String>()
                if(game_id != null) params["game_id"] = game_id.toString()

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}