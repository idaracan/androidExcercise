package com.example.androidexcercise.network

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.androidexcercise.data.BitmapCache
import org.json.JSONArray
import org.json.JSONObject


class ServiceVolley{

    val TAG = ServiceVolley::class.java.simpleName

    fun get(url: String, completionHandler: (response: JSONArray?) -> Unit) {
        val request = object : JsonArrayRequest(Method.GET,url,
            null, Response.Listener {
                response -> completionHandler(response)
            }, Response.ErrorListener { error ->
                Log.e("error", error.toString())
                completionHandler(null)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        BackendVolley.instance?.addToRequestQueue(request)
    }

    fun post(url: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit){
        val request = object : JsonObjectRequest(Method.POST, url, params, Response.Listener {
            response -> completionHandler(response)
        }, Response.ErrorListener { error -> completionHandler(null) }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        BackendVolley.instance?.addToRequestQueue(request, TAG)
    }
}