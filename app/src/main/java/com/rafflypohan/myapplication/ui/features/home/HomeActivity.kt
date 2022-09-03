package com.rafflypohan.myapplication.ui.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafflypohan.myapplication.R
import com.rafflypohan.myapplication.databinding.ActivityHomeBinding
import com.rafflypohan.myapplication.ui.utils.TokenPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val tokenPreference: TokenPreference by lazy { TokenPreference(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            textView.text = tokenPreference.getToken()
        }

    }
}