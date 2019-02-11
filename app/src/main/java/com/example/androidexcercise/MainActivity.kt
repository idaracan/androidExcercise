package com.example.androidexcercise

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.androidexcercise.data.Post
import com.example.androidexcercise.data.PostFragment
import com.example.androidexcercise.data.dummy.DummyContent
import com.example.androidexcercise.network.ServiceVolley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Post?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        /*
        val service = ServiceVolley()
        val url = "http://excercise.born-to-create.de/posts"

        service.get(url){
            response -> text.text = response.toString()
        }
        */

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
