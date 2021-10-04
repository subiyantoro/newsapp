package com.subiyantoro.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.subiyantoro.newsapp.R
import com.subiyantoro.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fragmentController: NavController
    private val mainViewModel by viewModels<MainViewModel>()
    private val bundle: Bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav = binding.botNav
        mainViewModel.getNewsFromApi("id", "technology", 1)
        fragmentController = findNavController(R.id.fragmentContainerView)
        fragmentController.setGraph(fragmentController.graph, bundle)
        bottomNav.setupWithNavController(fragmentController)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}