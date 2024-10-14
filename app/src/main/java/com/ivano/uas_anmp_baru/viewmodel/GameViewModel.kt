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
import com.ivano.uas_anmp_baru.model.Game

class GameViewModel(application: Application): AndroidViewModel(application) {
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gamesData = MutableLiveData<Game>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchGames(){

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.61/file_php/get_games.php" //IP DIGANTI SESUAI DENGAN IP JARINGAN YANG SEDANG DIGUNAKAN

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Game>>() { }.type
                val result = Gson().fromJson<List<Game>>(it, sType)
                gamesLD.value = result as ArrayList<Game>?
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

    fun fetchGamesById(game_id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.61/file_php/get_games_by_id.php" //IP DIGANTI SESUAI DENGAN IP JARINGAN YANG SEDANG DIGUNAKAN

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

                Log.d("showvolley", it.toString())

                val sType = object : TypeToken<Game>() { }.type
                val result = Gson().fromJson<Game>(it.toString(), sType)
                gamesData.value = result
                loadingLD.value = false


            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
                loadingLD.value = false
            }
        ) {
            override fun getParams(): MutableMap<String, String>{
                val params = HashMap<String, String>()
                if (game_id != null) params["id"] = game_id.toString()

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}