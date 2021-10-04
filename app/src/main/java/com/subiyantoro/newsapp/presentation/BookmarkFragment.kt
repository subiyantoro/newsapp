package com.subiyantoro.newsapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.subiyantoro.newsapp.databinding.FragmentBookmarkBinding
import com.subiyantoro.newsapp.models.Articles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(), OnNewsAction {
    lateinit var binding: FragmentBookmarkBinding
    lateinit var adapter: NewsAdapter
    private var isSearch: Boolean = false
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLoad()
        setupRV()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initLoad() {
        mainViewModel.getNewsFromDB()
        mainViewModel.newsFromDB.observe(viewLifecycleOwner) {
            if (it == null) binding.noBookmarkText.visibility = View.VISIBLE else adapter.updateList(it, isSearch)
        }
    }

    private fun setupRV() {
        adapter = NewsAdapter(this)
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickNews(articles: Articles) {
        val goToDetail = Intent(requireContext(), DetailNewsActivity::class.java)
        goToDetail.putExtra("news", articles)
        startActivity(goToDetail)
    }
}