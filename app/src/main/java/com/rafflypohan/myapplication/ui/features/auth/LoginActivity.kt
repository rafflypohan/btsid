package com.rafflypohan.myapplication.ui.features.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rafflypohan.myapplication.R
import com.rafflypohan.myapplication.databinding.ActivityLoginBinding
import com.rafflypohan.myapplication.databinding.ActivityRegisterBinding
import com.rafflypohan.myapplication.ui.features.home.HomeActivity
import com.rafflypohan.myapplication.ui.utils.Resource
import com.rafflypohan.myapplication.ui.utils.TokenPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    private val tokenPreference: TokenPreference by lazy { TokenPreference(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            btnLogin.setOnClickListener {
                viewModel.loginUser(password, username)
            }

            lifecycleScope.launchWhenStarted {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.login.collectLatest {
                        when (it) {
                            is Resource.Success -> {
                                it.data?.data?.token?.let { it1 -> tokenPreference.setToken(it1) }
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login Success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            }
                            is Resource.Loading -> {}
                            is Resource.Error -> {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "${it.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}