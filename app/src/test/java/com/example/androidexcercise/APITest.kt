package com.example.androidexcercise

import android.util.Log
import com.example.androidexcercise.network.ServiceVolley
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class APITest{
    var result = false
    @Test
    fun getPostsList(){
        val sv = ServiceVolley()
        val url = "http://excercise.born-to-create.de/posts"
        val countDownLatch = CountDownLatch(1)
        sv.get(url){
            response -> run {
                val this_: APITest = this
                this_.result = true
                countDownLatch.countDown()
            }
        }
        countDownLatch.await(10,TimeUnit.SECONDS)
        assert(result)
    }


    fun postComment(){
        val serviceVolley = ServiceVolley()
        val url = "http://excercise.born-to-create.de/posts/1/comments"
        val params = JSONObject()//.put("email", "ivan@ivan.com").put("name","ivan").put("comment","I like kotlin :3")
        serviceVolley.post(url, params){
            response ->
        }
    }
}