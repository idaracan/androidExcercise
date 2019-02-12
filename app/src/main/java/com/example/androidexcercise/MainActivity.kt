package com.example.androidexcercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.androidexcercise.data.Comment
import com.example.androidexcercise.data.CommentFragment
import com.example.androidexcercise.data.Post
import com.example.androidexcercise.data.PostFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener, CommentFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Comment?) {
        Toast.makeText(this, "comment id: ${item!!.id}",Toast.LENGTH_SHORT).show()
    }

    override fun onListFragmentInteraction(item: Post?) {
        val commentFragment = CommentFragment.newInstance(item!!.id)
        supportFragmentManager.beginTransaction()
            .replace(mainScreen.id, commentFragment).addToBackStack(null).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val postFragment = PostFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(mainScreen.id, postFragment).addToBackStack(null).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
