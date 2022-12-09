package com.example.ez_math

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ez_math.Fragments.FragmentBelajar
import com.example.ez_math.Fragments.FragmentPencapaian
import com.example.ez_math.Fragments.FragmentProfile
import com.example.ez_math.Fragments.Home
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    internal var selectedFragment: Fragment? = null
    private lateinit var textView: TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_homeLatihan -> {
                selectedFragment = Home()
            }
            R.id.navigation_belajar -> {
                selectedFragment = FragmentBelajar()
            }
            R.id.navigation_pencapaian -> {
                selectedFragment = FragmentPencapaian()
            }
            R.id.navigation_profile -> {
                selectedFragment = FragmentProfile()
            }
        }
        if(selectedFragment != null){
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                selectedFragment!!
            ).commit()
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnItemSelectedListener(onNavigationItemSelectedListener)



        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainer,Home()
        ).commit()
    }


}