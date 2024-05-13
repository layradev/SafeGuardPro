package com.happyhour.safeguardpro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Criar a toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    //Criar a navigation
    private lateinit var  navController: NavController

    //Criar o binding
    private var _binding: ActivityMainBinding? = null
    private val binding : ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        //Configurar a navegação e a toolbar
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        setContentView(R.layout.activity_main)
    }
}