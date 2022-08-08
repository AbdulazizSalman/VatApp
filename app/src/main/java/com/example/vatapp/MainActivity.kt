package com.example.vatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.calculateButton.setOnClickListener {
            calculateVat()
        }

    }

    private fun calculateVat() {
        val TextInput = binding.InputTotalCost.text.toString()
        val cost = TextInput.toDouble()

        val IdB = binding.RadioGroup.checkedRadioButtonId
        val CheckRoundUp = binding.switchB.isChecked
        val Per = when (IdB) {
            R.id.ten_Button -> 0.10
            R.id.fifteen_Button -> 0.15
            else -> 0.20

        }
        val VAT = cost * Per
        var TotalAmount = cost + VAT

        if (CheckRoundUp) {
            TotalAmount = kotlin.math.ceil(TotalAmount)
        }
        val TotalFormat = NumberFormat.getCurrencyInstance().format(TotalAmount)
        val VatFormat = NumberFormat.getCurrencyInstance().format(VAT)

        binding.TextCostTotal.text = getString(R.string.Total_Amount, TotalFormat)
        binding.TextVat.text = getString(R.string.VAT_Amount, VatFormat)

    }


}