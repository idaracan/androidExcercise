package com.example.androidexcercise.data


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.example.androidexcercise.R
import com.example.androidexcercise.data.PostFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_post.view.*

/**
 * [RecyclerView.Adapter] that can display a [Post] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyPostRecyclerViewAdapter(
    private val mValues: List<Post>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context)}
    val imageLoader: ImageLoader by lazy { ImageLoader(requestQueue, BitmapCache()) }

    private val mOnClickListener: View.OnClickListener
    private var context: Context? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Post
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        this.context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.content
        holder.mAuthorView.text = item.author
        holder.mTitleView.text = item.title
        holder.thumbnail.setImageUrl(item.thumbnail,imageLoader)

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
        val thumbnail:      NetworkImageView = mView.thumbnail
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
