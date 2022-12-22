package com.example.ez_math

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ez_math.Fragments.FragmentBelajar
import com.example.ez_math.Fragments.FragmentPencapaian
import com.example.ez_math.Fragments.FragmentProfile
import com.example.ez_math.Fragments.Home
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var student = com.example.ez_math.modhel.Student


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_homeLatihan -> {
                moveToFragment(Home())
                return@OnNavigationItemSelectedListener true;
            }
            R.id.navigation_belajar -> {
                moveToFragment(FragmentBelajar())
                return@OnNavigationItemSelectedListener true;
            }
            R.id.navigation_pencapaian -> {
                moveToFragment(FragmentPencapaian())
                return@OnNavigationItemSelectedListener true;
            }
            R.id.navigation_profile -> {
                moveToFragment(FragmentProfile())
                return@OnNavigationItemSelectedListener true;
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnItemSelectedListener(onNavigationItemSelectedListener)

        Log.d("main" , " siswanya : ${intent.getStringExtra("namaPengguna")}")

        val extras = intent.extras
        if(extras != null){
            val value = extras.get("from")
            when(value){
                "detailPencapaian" -> moveToFragment(FragmentPencapaian())
                "pengaturanProfile" -> moveToFragment(FragmentProfile())
                "hasilLatihan" -> moveToFragment(FragmentPencapaian())
                else -> moveToFragment(Home())
            }
        }else{
            moveToFragment(Home())
        }


    }

    private fun moveToFragment(fragment: Fragment)
    {
        val argument = Bundle()


        argument.putString("namaPengguna",intent.getStringExtra("namaPengguna"))
        fragment.arguments = argument
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragmentContainer, fragment)

        fragmentTrans.commit()
    }



}