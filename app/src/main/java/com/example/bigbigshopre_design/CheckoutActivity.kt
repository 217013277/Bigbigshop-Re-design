package com.example.bigbigshopre_design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import com.example.bigbigshopre_design.databinding.ActivityCheckoutBinding
import com.example.bigbigshopre_design.databinding.ActivityMainBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.breadcrumb1.setOnClickListener { finish() }

        binding.sameInformationButton.setOnClickListener {
            if(!binding.sameInformationButton.isChecked) {
                binding.sameInformation.visibility = View.VISIBLE
            } else {
                binding.sameInformation.visibility = View.GONE
            }
        }

        binding.nextButton.setOnClickListener {
            checkValidation()
        }


    }

    private fun checkValidation() {
        if (binding.checkoutMethodRadioGroup.checkedRadioButtonId == -1) {
            // no radio buttons are checked
            Toast.makeText(this, "Payment method not checked", Toast.LENGTH_SHORT).show()
            binding.checkoutMethodRadioGroupError.visibility = View.VISIBLE
        } else {
            // one of the radio buttons is checked
            Toast.makeText(this, "Payment method checked", Toast.LENGTH_SHORT).show()
            binding.checkoutMethodRadioGroupError.visibility = View.GONE
        }
    }
}