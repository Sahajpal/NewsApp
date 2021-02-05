package com.ryms.newsapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryms.newsapp.Common.Common
import com.ryms.newsapp.Interface.IItemClickListner
import com.ryms.newsapp.Model.News
import com.ryms.newsapp.R

class NewsListAdapter(internal var context: Context, internal var articles: List<News>): RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var newsImage: ImageView
        internal var newsAuthor: TextView
        internal var newsTitle: TextView
        internal var newsPublishedAt: TextView

        internal  var itemClickListener: IItemClickListner ?= null

        fun setItemClickListener(iItemClickListner: IItemClickListner){
            this.itemClickListener = iItemClickListner
        }

        init {
            newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
            newsAuthor = itemView.findViewById<TextView>(R.id.newsAuthor)
            newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
            newsPublishedAt = itemView.findViewById<TextView>(R.id.newsPublishedAt)

            itemView.setOnClickListener{view -> itemClickListener!!.onClick(view, adapterPosition)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.news_article, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(articles[position].urlToImage).into(holder.newsImage)
        holder.newsAuthor.text = articles[position].author
        holder.newsTitle.text = articles[position].title
        holder.newsPublishedAt.text = articles[position].publishedAt.toString()

        holder.setItemClickListener(object: IItemClickListner{
            override fun onClick(view: View, position: Int) {
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position", position))
            }
        })
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}