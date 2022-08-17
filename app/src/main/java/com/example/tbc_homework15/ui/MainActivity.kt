package com.example.tbc_homework15.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.tbc_homework15.R
import com.example.tbc_homework15.databinding.ActivityMainBinding
import com.example.tbc_homework15.network.NetworkConnectivityObserver
import com.example.tbc_homework15.utils.ConnectivityObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainLayout = binding.mainLayout
        connectivityObserver.observeInternet().onEach {
           val snackBar = Snackbar.make(mainLayout, "Network: ${it.toString()}", Snackbar.LENGTH_LONG)
                .setTextMaxLines(2)
            when(it.toString()){
                "Available" -> snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.lime))
                "Losing" ->  snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.orange))
                else ->  snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.regular_red))
            }
            snackBar.show()
        }.launchIn(lifecycleScope)
    }
}