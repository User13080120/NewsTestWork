package com.example.newstestwork.adapter

import android.annotation.SuppressLint
import com.example.newstestwork.model.DataJson
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstestwork.R
import kotlinx.android.synthetic.main.top_news.view.*


class ViewAdapter(private val context: Context) : RecyclerView.Adapter<ViewAdapter.PagerVH>() {
    private var dataJsonList:List<DataJson> = listOf()

    open class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.textTitle
        val sourceTextView: TextView = itemView.textUrl
        val timeTextView: TextView = itemView.textTime
        val imgView = itemView.imageView

    }

    class PagerTopVH(itemView: View) : PagerVH(itemView) {
        val tvTopIdent: TextView = itemView.topText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        return when(viewType){
            0->PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false))
            else ->PagerTopVH(LayoutInflater.from(parent.context).inflate(R.layout.top_news, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(dataJsonList.get(position).top!=null){
            val isTop = dataJsonList[position].top!!.contentEquals("1")
            if(isTop){
                return 1
            }else{
                return 0
            }
        }
        return 0
    }

    override fun getItemCount(): Int = dataJsonList.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val news = dataJsonList[position]

        if(getItemViewType(position) == 1){
            val view = holder as PagerTopVH
            view.tvTopIdent.text = context.getString(R.string.top)
        }

        val url: String? = news.img

        if(url!=null) {
            url.let {
                holder.imgView.visibility = View.VISIBLE
                Glide.with(context).load(url).into(holder.imgView)
            }
        }else{
            holder.imgView.visibility = View.GONE
        }

        holder.titleTextView.text = news.title
        holder.sourceTextView.text = news.click_url
        holder.timeTextView.text = news.time
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsListItems(dataJsonList: List<DataJson>?){

        if (dataJsonList != null) {
            this.dataJsonList = dataJsonList
        }

        notifyDataSetChanged()
    }

}