package com.example.bigbigshopre_design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bigbigshopre_design.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding

    private var allCorrect = true
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
            allCorrect = true
            checkBuyerName()
            checkBuyerLocale()
            checkBuyerPhone()
            checkBuyerEmail()
            if (!binding.sameInformationButton.isChecked) {
                checkReceiverName()
                checkReceiverLocale()
                checkReceiverPhone()
            }
            checkPaymentMethod()

            if (allCorrect) {
                startActivity(Intent(this@CheckoutActivity, SuccessActivity::class.java))
            }
        }


    }

    private fun checkBuyerName() {
       if (binding.editTextBuyerName.text.toString().isEmpty()) {
           binding.editTextBuyerName.error = "請輸入你的姓名"
           allCorrect = false
       }
    }

    private fun checkBuyerLocale() {
        if (binding.editTextBuyerLocale.text.toString().isEmpty()) {
            binding.editTextBuyerLocale.error = "請輸入你的地區號碼"
            allCorrect = false
        }
    }

    private fun checkBuyerPhone() {
        if (binding.editTextBuyerPhone.text.toString().isEmpty()) {
            binding.editTextBuyerPhone.error = "請輸入你的手提電話"
            allCorrect = false
        }
    }

    private fun checkBuyerEmail() {
        if (binding.editTextBuyerEmail.text.toString().isEmpty()) {
            binding.editTextBuyerEmail.error = "請輸入你的電子郵件"
            allCorrect = false
        }
    }

    private fun checkReceiverName() {
        if (binding.editTextReceiverName.text.toString().isEmpty()) {
            binding.editTextReceiverName.error = "請輸入收貨人姓名"
            allCorrect = false
        }
    }

    private fun checkReceiverLocale() {
        if (binding.editTextReceiverLocale.text.toString().isEmpty()) {
            binding.editTextReceiverLocale.error = "請輸入收貨人的地區號碼"
            allCorrect = false
        }
    }

    private fun checkReceiverPhone() {
        if (binding.editTextReceiverPhone.text.toString().isEmpty()) {
            binding.editTextReceiverPhone.error = "請輸入收貨人手提電話"
            allCorrect = false
        }
    }

    private fun checkPaymentMethod() {
        if (binding.checkoutMethodRadioGroup.checkedRadioButtonId == -1) {
            binding.checkoutMethodRadioGroupError.visibility = View.VISIBLE
            allCorrect = false
        } else {
            binding.checkoutMethodRadioGroupError.visibility = View.GONE
        }
    }
}