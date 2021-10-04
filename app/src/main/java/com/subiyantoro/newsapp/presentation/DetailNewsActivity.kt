package com.subiyantoro.newsapp.presentation

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.subiyantoro.newsapp.R
import com.subiyantoro.newsapp.databinding.ActivityDetailNewsBinding
import com.subiyantoro.newsapp.models.Articles
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    lateinit var articles: Articles
    private var menu: Menu? = null
    private val detailNewsViewModel by viewModels<DetailNewsViewModel>()
    private var isBookmarked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        articles = intent.getSerializableExtra("news") as Articles
        setupToolbar()

        articles.let {
            val parser: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val formatter: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy - HH:mm")
            val dateOutput: String = formatter.format(parser.parse(it.publishedAt))
            Glide.with(this)
                .load(it.urlToImage)
                .centerCrop()
                .into(binding.imgNews)
            binding.newsTitle.text = it.title
            binding.authorNews.text = it.author
            binding.newsDesc.text = it.content
            binding.dateNews.text = dateOutput
        }
        detailNewsViewModel.checkNewsOnDB(articles.title)
        detailNewsViewModel.articleNews.observe(this) {
            isBookmarked = it != null
            updateBookmarkIcon(menu)
        }
        setContentView(binding.root)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        updateBookmarkIcon(menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    private fun updateBookmarkIcon(menu: Menu?) {
        menu?.getItem(1)?.icon = ContextCompat.getDrawable(this, if (isBookmarked) R.drawable.ic_bookmarked else R.drawable.ic_unbookmark)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.share_btn -> {
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, articles.title)
                    type = "text/plain"
                }
                startActivity(shareIntent)
            }
            R.id.bookmark_btn -> {
                if (isBookmarked) {
                    detailNewsViewModel.deleteNewsFromDB(articles)
                    Toast.makeText(this, "News Unbookmarked", Toast.LENGTH_SHORT).show()
                }else{
                    detailNewsViewModel.saveNewsToDB(articles)
                    Toast.makeText(this, "News Bookmarked", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}