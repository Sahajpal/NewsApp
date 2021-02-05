package com.ryms.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NewsContent : Fragment() {

    companion object{
        internal var instance: NewsContent ?= null

        fun getInstance(): NewsContent{
            if(instance == null)
                instance = NewsContent()
            return instance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val itemView = inflater.inflate(R.layout.fragment_news_content, container, false)

        return itemView
    }
}