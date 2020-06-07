package com.moviesaggregator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moviesaggregator.userinterface.aggregator.AggregatorFragment
import com.moviesaggregator.util.addFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        addFragment(fragmentClass = AggregatorFragment::class.java,
            tag = AggregatorFragment::class.java.simpleName)
    }
}