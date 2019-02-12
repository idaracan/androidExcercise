package com.example.androidexcercise.data

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidexcercise.R
import com.example.androidexcercise.network.ServiceVolley
import com.example.androidexcercise.utils.JSONUtils

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CommentFragment.OnListFragmentInteractionListener] interface.
 */
class CommentFragment : Fragment() {

    private var postId = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            postId = it.getInt(POST_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 1)
                
                val service = ServiceVolley()
                val url = "http://excercise.born-to-create.de/posts/$postId/comments"

                service.get(url){
                        response ->
                    response?.let { adapter = MyCommentRecyclerViewAdapter(JSONUtils.parseComments(response), listener)}
                }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Comment?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val POST_ID = "column-count"
        
        @JvmStatic
        fun newInstance(postId: Int) =
            CommentFragment().apply {
                arguments = Bundle().apply {
                    putInt(POST_ID, postId)
                }
            }
    }
}
