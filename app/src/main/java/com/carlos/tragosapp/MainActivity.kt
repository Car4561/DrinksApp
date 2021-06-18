package com.carlos.tragosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.carlos.tragosapp.data.DataSource
import com.carlos.tragosapp.databinding.ActivityMainBinding
import com.carlos.tragosapp.databinding.FragmentMainBinding
import com.carlos.tragosapp.domain.RepoImpl
import com.carlos.tragosapp.ui.viewmodel.MainViewModel
import com.carlos.tragosapp.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val navHostFragment = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment )
            navController = navHostFragment.navController
            NavigationUI.setupActionBarWithNavController(this@MainActivity,navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }



}