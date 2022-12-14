package com.example.testcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.testcoroutines.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }
    private fun loadData(){
        binding.progressBar.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity{
            binding.tvLocation.text= it
            loadTemp(it){
                binding.tvTemperature.text = it
                binding.progressBar.isVisible = false
                binding.buttonLoad.isEnabled = true}

        }

    }
    private fun loadCity(callback: (String)-> Unit){
        thread{
            Thread.sleep(5000)
            runOnUiThread{
            callback.invoke("Moscow")}
        }

    }
    private fun loadTemp(city: String,callback:  (String) -> Unit){
        thread {
            runOnUiThread{
            Toast.makeText(
                this,
                "Loading temperature for city: $city",
                Toast.LENGTH_SHORT
            ).show()}
            Thread.sleep(5000)
            runOnUiThread{
            callback.invoke("+18") }}

    }
}