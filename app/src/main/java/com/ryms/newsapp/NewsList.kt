package com.ryms.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryms.newsapp.Adapter.NewsListAdapter
import com.ryms.newsapp.Common.Common
import com.ryms.newsapp.Common.ItemOffsetDecoration
import com.ryms.newsapp.Retrofit.IArticles
import com.ryms.newsapp.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iArticles: IArticles

    internal lateinit var news_recyclerview: RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iArticles =retrofit.create(IArticles::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_news_list, container, false)

        news_recyclerview = itemView.findViewById<RecyclerView>(R.id.news_recyclerview)
        news_recyclerview.setHasFixedSize(true)
        news_recyclerview.layoutManager = GridLayoutManager(activity, 1)
        val itemDecoration = ItemOffsetDecoration(activity!!, R.dimen.spacing)
        news_recyclerview.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iArticles.articles
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ newsApp ->
                Common.newsList = newsApp.articles!!
                val adapter = NewsListAdapter(activity!!, Common.newsList)

                news_recyclerview.adapter = adapter
            }

        );
    }
}