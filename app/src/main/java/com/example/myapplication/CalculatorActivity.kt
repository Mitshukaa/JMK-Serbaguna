package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityKalkulatorBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {

    private var _binding: ActivityKalkulatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKalkulatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.btnClear.setOnClickListener {
            binding.tvDisplay.text = ""
            binding.output.text = ""
        }

        binding.kurungkiri.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "(")
        }

        binding.kurungkanan.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = ")")
        }

        binding.membagi.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "÷")
        }

        binding.perkalian.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "×")
        }

        binding.kurang.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "-")
        }

        binding.tambah.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "+")
        }

        binding.btn0.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "0")
        }

        binding.btn1.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "1")
        }

        binding.btn2.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "2")
        }

        binding.btn3.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "3")
        }

        binding.btn4.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "4")
        }

        binding.btn5.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "5")
        }

        binding.btn6.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "6")
        }

        binding.btn7.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "7")
        }

        binding.btn8.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "8")
        }

        binding.btn9.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = "9")
        }

        binding.titik.setOnClickListener {
            binding.tvDisplay.text = addToInputText(buttonValue = ".")
        }

        binding.samadengan.setOnClickListener {
            showResult()
        }

        // Handle back button click
        binding.buttonGoToBack.setOnClickListener {
            // Kembali ke Activity awal
            val intent = Intent(this, MainActivity::class.java) // Ganti dengan Activity awal yang sesuai
            startActivity(intent)
            finish() // Optional: Menutup activity saat ini
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${binding.tvDisplay.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = binding.tvDisplay.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Error
                binding.output.text = "Error"
            } else {
                // Hasil
                binding.output.text = DecimalFormat("0.#####").format(result).toString()
            }
        } catch (e: Exception) {
            // Error
            binding.output.text = "Error"
        }
    }
}
