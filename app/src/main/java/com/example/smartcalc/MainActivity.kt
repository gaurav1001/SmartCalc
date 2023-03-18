package com.example.smartcalc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.smartcalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private var show: TextView? = null
    private var result: TextView? = null
    private lateinit var expression: Expression
    private lateinit var binding: ActivityMainBinding
    private var lastNumeric = false
    private var statsError = false
    private var isDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        show = binding.show
        result = binding.result

        binding.one.setOnClickListener {
            onDigitClick(it)
        }
        binding.two.setOnClickListener {
            onDigitClick(it)
        }
        binding.three.setOnClickListener {
            onDigitClick(it)
        }
        binding.four.setOnClickListener {
            onDigitClick(it)
        }
        binding.five.setOnClickListener {
            onDigitClick(it)
        }
        binding.six.setOnClickListener {
            onDigitClick(it)
        }
        binding.seven.setOnClickListener {
            onDigitClick(it)
        }
        binding.eight.setOnClickListener {
            onDigitClick(it)
        }
        binding.nine.setOnClickListener {
            onDigitClick(it)
        }
        binding.zero.setOnClickListener {
            onDigitClick(it)
        }
        binding.dot.setOnClickListener {
            onDotClick(it)
        }
        binding.equal.setOnClickListener {
            onEqualClick()
        }
        binding.plus.setOnClickListener {
            onOperatorClick(it)
        }
        binding.minus.setOnClickListener {
            onOperatorClick(it)
        }
        binding.multiple.setOnClickListener {
            onOperatorClick(it)
        }
        binding.divide.setOnClickListener {
            onOperatorClick(it)
        }
        binding.backspace.setOnClickListener {
            onBackClick()
        }
        binding.ac.setOnClickListener {
            onAllClear()
        }

        binding.journey.setOnClickListener {
            onShow()
        }
    }

    private fun onOperatorClick(view: View) {
        show?.visibility = View.VISIBLE
        result?.visibility = View.GONE
        show?.append((view as Button).text.toString())
        isDot = false

    }

    private fun onDigitClick(view: View) {

        if(statsError){
            binding.show.text = (view as AppCompatButton).text.toString()
            statsError = false
        }else{
            binding.show.append((view as AppCompatButton).text.toString())
        }
        lastNumeric = true
        onEqual()
    }

    private fun onEqual() {
        Log.d("LAST","$lastNumeric")
         if (lastNumeric && !statsError) {
             val txt = show?.text.toString()
             expression = ExpressionBuilder(txt).build()
             try {
                 val data = expression.evaluate()
                 result?.visibility = View.VISIBLE
                 val b = "="+String.format("%.2f",data)
                 result?.text = b
                 show?.visibility = View.GONE

             } catch (e: Exception) {
                 Log.d("ERROR", "Err")
                 statsError = true
                 lastNumeric = false
             }
         }else{
             Toast.makeText(this,"input must be digit",Toast.LENGTH_SHORT).show()
         }
}
   private fun onBackClick(){
      show?.text = show?.text.toString().dropLast(1)

   }
   private fun onAllClear(){
       show?.text = ""
       result?.text = ""
       lastNumeric = false
       statsError = false
       isDot = false
   }

   private fun onShow(){
       result?.visibility = View.GONE
       show?.visibility = View.VISIBLE
   }

   private fun onEqualClick(){
       show?.text = binding.result.text.toString().drop(1)
       result?.visibility = View.VISIBLE
       onEqual()
   }

   private fun onDotClick(view: View){
       if(!isDot){
           show?.append((view as AppCompatButton).text.toString())
           lastNumeric = false
           isDot = true
       }


   }
}