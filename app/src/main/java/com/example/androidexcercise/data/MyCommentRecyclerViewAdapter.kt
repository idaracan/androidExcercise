package com.example.androidexcercise.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidexcercise.R


import com.example.androidexcercise.data.CommentFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_comment.view.*

/**
 * [RecyclerView.Adapter] that can display a [Comment] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyCommentRecyclerViewAdapter(
    private val mValues: List<Comment>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Comment
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.comment
        holder.mAuthor.text = item.author
        holder.mEmail.text = item.email

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mAuthor: TextView = mView.author
        val mEmail: TextView = mView.email
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
