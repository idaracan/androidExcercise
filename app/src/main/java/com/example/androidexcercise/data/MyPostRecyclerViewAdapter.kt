package com.example.androidexcercise.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidexcercise.R


import com.example.androidexcercise.data.PostFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_post.view.*

/**
 * [RecyclerView.Adapter] that can display a [Post] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val mValues: List<Post>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Post
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.content
        holder.mAuthorView.text = item.author
        holder.mTitleView.text = item.title

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView:        TextView = mView.item_number
        val mContentView:   TextView = mView.content
        val mAuthorView:    TextView = mView.author
        val mTitleView:     TextView = mView.title

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
