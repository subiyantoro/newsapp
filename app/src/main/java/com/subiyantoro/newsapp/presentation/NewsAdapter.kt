package com.subiyantoro.newsapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.subiyantoro.newsapp.databinding.NewsItemLayoutBinding
import com.subiyantoro.newsapp.models.Articles
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class NewsAdapter(var onNewsAction: OnNewsAction): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var listNews = mutableListOf<Articles>()

    inner class NewsViewHolder(var binding: NewsItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(articles: Articles) {
            val parser: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val formatter: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy - HH:mm")
            val dateOutput: String = formatter.format(parser.parse(articles.publishedAt))
            binding.newsTitle.text = articles.title
            binding.newsDesc.text = articles.description
            binding.newsDate.text = dateOutput
            Glide.with(binding.root.context)
                .load(articles.urlToImage)
                .centerCrop()
                .into(binding.thumbnailNews)

            itemView.setOnClickListener {
                onNewsAction.onClickNews(articles)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

    override fun getItemCount(): Int = listNews.size

    fun updateList(dataArticles: List<Articles>, isSearch: Boolean) {
        if (isSearch) {
            listNews.clear()
            listNews.addAll(dataArticles)
            notifyItemRangeChanged(0, dataArticles.size)
        }else{
            listNews.addAll(dataArticles)
            notifyItemRangeChanged(0, dataArticles.size)
        }
    }

    fun resetList() {
        listNews.clear()
    }
}