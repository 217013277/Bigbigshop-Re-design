package com.example.bigbigshopre_design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigbigshopre_design.databinding.ActivityCheckoutBinding
import com.example.bigbigshopre_design.databinding.ActivityMainBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.breadcrumb1.setOnClickListener { finish() }
    }
}