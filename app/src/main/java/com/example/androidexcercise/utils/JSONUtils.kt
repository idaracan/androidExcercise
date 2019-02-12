package com.example.androidexcercise.utils

import android.util.Log
import com.example.androidexcercise.data.Comment
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

        fun parseComments(comments: JSONArray): List<Comment> {
            val commentList: ArrayList<Comment> = ArrayList()
            for (i in 0 until comments.length()){
                val comments = comments.getJSONObject(i)
                try {
                    commentList.add(Comment(comments.getInt("id"),
                        comments.getInt("post_id"), comments.getString("author"),
                        comments.getString("email"), comments.getString("comment")))
                }catch (e: Exception){
                    Log.e("error:", e.toString())
                }

            }
            return commentList
        }
    }
}