package com.subiyantoro.newsapp.presentation

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.subiyantoro.newsapp.databinding.FragmentHomeBinding
import com.subiyantoro.newsapp.models.Articles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),OnNewsAction{
    lateinit var binding: FragmentHomeBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: NewsAdapter
    private var page: Int = 1
    private var isSearch = false
    private var searchWord = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLoadData()
        mainViewModel.getNewsFromApi("id", "technology", page)
        errorListener()
        btnRetryListener()
        setupRVNews()
        searchListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun errorListener() {
        mainViewModel.isError.observe(viewLifecycleOwner) {
            if (it) {
                binding.textError.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.VISIBLE
            } else {
                binding.textError.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
            }
        }
    }

    private fun btnRetryListener() {
        binding.btnRetry.setOnClickListener {
            binding.textError.visibility = View.GONE
            binding.btnRetry.visibility = View.GONE
            mainViewModel.getNewsFromApi("id", "technology", page)
        }
    }

    private fun setupRVNews() {
        adapter = NewsAdapter(this)
        binding.rvNews.adapter = adapter
        val lmNews = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = lmNews
        if (!isSearch) scrollLoadMore(lmNews)
    }

    private fun initLoadData() {
        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true) binding.loadingProgress.visibility = View.VISIBLE else binding.loadingProgress.visibility = View.GONE
        }
        mainViewModel.newsFromAPI.observe(viewLifecycleOwner) {
            it.articles?.let { articles ->
                if (articles.isNotEmpty()) {
                    adapter.updateList(articles, isSearch)
                    binding.noResultText.visibility = View.GONE
                }
                else {
                    Toast.makeText(requireContext(), "No News Anymore", Toast.LENGTH_LONG).show()
                    binding.noResultText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun scrollLoadMore(lm: LinearLayoutManager) {
        binding.rvNews.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItem: Int = lm.findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItem + 1) {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            page += 1
                            mainViewModel.getNewsFromApi("id", "technology", page)
                        }, 2000
                    )
                }
            }
        })
    }

    override fun onClickNews(articles: Articles) {
        val goToDetail = Intent(requireContext(), DetailNewsActivity::class.java)
        goToDetail.putExtra("news", articles)
        startActivity(goToDetail)
    }

    private fun searchListener() {
        binding.searchBox.setOnEditorActionListener { v, actionId, event ->
            adapter.resetList()
            if (v.text.isNotEmpty()) {
                isSearch = true
                mainViewModel.searchNewsFromApi(v.text.toString())
            }else{
                isSearch = false
                mainViewModel.getNewsFromApi("id", "technology", page)
            }
            true
        }
    }
}