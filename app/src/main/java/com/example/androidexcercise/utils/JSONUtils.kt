package com.example.androidexcercise.utils

import com.example.androidexcercise.data.Post
import org.json.JSONArray

class JSONUtils {

    companion object {
        fun parsePosts(posts: JSONArray): List<Post>{
            val postList: ArrayList<Post> = ArrayList()
            for (i in 0 until posts.length()){
                val post = posts.getJSONObject(i)
                postList.add(Post(post.getInt("id"),
                    post.getString("author"),post.getString("title"),
                    post.getString("thumbnail"),post.getString("image"),
                    post.getString("content")))
            }
            return postList
        }
    }
}