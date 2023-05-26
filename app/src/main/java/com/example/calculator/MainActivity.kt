package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.view.View as View1

class MainActivity : AppCompatActivity() {
    private lateinit var tvInput: TextView
    var  lastNumeric: Boolean = false;
    var lastDot: Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view: View1){
        tvInput.append((view as Button).text)
        lastNumeric = true;
    }
    fun onClear(view: View1){
        tvInput.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View1){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View1){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View1){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(value.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }
    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}