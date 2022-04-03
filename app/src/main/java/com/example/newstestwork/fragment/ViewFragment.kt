package com.example.newstestwork.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstestwork.R
import com.example.newstestwork.adapter.ViewAdapter
import com.example.newstestwork.view.ActivityView
import kotlinx.android.synthetic.main.fragment.*

private const val TAB_NAME = "TAB_NAME"

class NewsFragment : Fragment() {

    private lateinit var tabFilterType: String

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(TAB_NAME, param)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(TAB_NAME)) {
                tabFilterType = it.getString(TAB_NAME)!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recyclerViewAdapter = ViewAdapter(requireContext())
        val model = ViewModelProvider(requireActivity()).get(ActivityView::class.java)
        model.simpleLiveData.observe(viewLifecycleOwner, Observer {

            if (it.data.isNullOrEmpty()) {
                text_view.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                text_view.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }

            recyclerViewAdapter.setNewsListItems(it.data?.filter {it.type!!.contentEquals(tabFilterType)
            }?.sortedBy { !it.top!!.contentEquals("1") })
        })

        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()
    }
}