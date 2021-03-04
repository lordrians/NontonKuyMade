package com.example.nontonkuymade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nontonkuymade.core.ui.VpMainAdapter
import com.example.nontonkuymade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbMain)

        val adapter = VpMainAdapter(this)
        binding.vpMain.adapter = adapter

        binding.botnavMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.botnav_home -> {
                    binding.vpMain.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.botnav_favorite -> {
                    binding.vpMain.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }


}