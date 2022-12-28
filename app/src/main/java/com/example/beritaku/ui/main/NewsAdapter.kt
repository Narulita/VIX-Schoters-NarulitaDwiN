package com.example.beritaku.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beritaku.R
import com.example.beritaku.ui.data.response.ArticlesItem
import com.example.beritaku.ui.data.response.NewsResponse
import kotlinx.android.synthetic.main.item.view.*

class NewsAdapter (private val listener: (ArticlesItem)->Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem>()

    fun setNews(news : List<ArticlesItem>){
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
       return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: ArticlesItem) {

            itemView.tvTitle.text = news.title

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.load))
                .into(itemView.imgNews)

            itemView.setOnClickListener {
                listener(news)
            }

        }

    }
}