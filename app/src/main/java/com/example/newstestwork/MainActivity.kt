package com.example.newstestwork

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.example.newstestwork.adapter.PagerAdapter
import com.example.newstestwork.model.DataJson
import com.example.newstestwork.service.Status
import com.example.newstestwork.view.ActivityView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var activityView: ActivityView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager2 = viewpager
        val viewPagerAdapter = PagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter

        val tabLayout = tab_layout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "Stories"
                1 -> "Video"
                2 -> "Favourites"
                else -> ({}).toString()
            }
        }.attach()

        activityView = ViewModelProvider(this).get(ActivityView::class.java)
        observeGetPosts()
    }

    override fun onResume() {
        super.onResume()
        activityView.getData()
    }


    private fun observeGetPosts() {
        activityView.simpleLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }
    private fun viewOneLoading() {
    }

    private fun viewOneSuccess(data: List<DataJson>?) {
        progressBar.visibility = if (progressBar.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    private fun viewOneError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}
