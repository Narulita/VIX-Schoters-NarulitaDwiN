package com.example.beritaku.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beritaku.Profil
import com.example.beritaku.R
import com.example.beritaku.ui.data.remote.ApiClient
import com.example.beritaku.ui.data.response.ArticlesItem
import com.example.beritaku.ui.data.response.NewsResponse
import com.example.beritaku.ui.detail.DetilActivity
import com.example.beritaku.ui.detail.DetilActivity.Companion.EXTRA_NEWS
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter: NewsAdapter
    private lateinit var btnIntent : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initRecycleView()

        btnIntent = findViewById(R.id.tombol)

        btnIntent.setOnClickListener(this)
        
    }

    private fun initRecycleView() {
        adapter = NewsAdapter {

            moveActivity(it)
        }
        rvNews.layoutManager = LinearLayoutManager(this)

        rvNews.adapter= NewsAdapter{
        }
    }

    private fun moveActivity(news: ArticlesItem) {
        val intent = Intent(this,DetilActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
    }

    private fun getNews() {
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles : List<ArticlesItem> =
                        response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tombol ->{
                val intentBiasa = Intent(this@MainActivity, Profil::class.java)
                startActivity(intentBiasa)
            }
        }
    }

}