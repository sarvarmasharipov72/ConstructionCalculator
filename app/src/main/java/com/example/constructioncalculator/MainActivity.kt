package com.example.constructioncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.constructioncalculator.di.AppComponent

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MainApp).appComponent
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)



    }

    override fun onDestroy() {
        super.onDestroy()
    }
}