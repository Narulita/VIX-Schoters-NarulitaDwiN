package com.example.beritaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beritaku.R
import com.example.beritaku.ui.data.response.ArticlesItem
import kotlinx.android.synthetic.main.activity_detil.*

class DetilActivity : AppCompatActivity() {

    companion object {
        val EXTRA_NEWS = "extraNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil)

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

            Log.d("DetilActivity",news!!.author.toString())

            tvTitle.text = news.title
            tvAuthor.text = news.author
            tvDeskripstion.text = news.description
            Glide.with(this@DetilActivity)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.load))
                .into(ivNews)
    }
}