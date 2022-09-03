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
import com.rafflypohan.myapplication.databinding.ActivityRegisterBinding
import com.rafflypohan.myapplication.ui.utils.Resource
import com.rafflypohan.myapplication.ui.utils.TokenPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    private val tokenPreference: TokenPreference by lazy { TokenPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            val email = edtEmail.text.toString().trim()
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            btnRegister.setOnClickListener {
                viewModel.registerUser(email, password, username)
            }

            lifecycleScope.launchWhenStarted {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.register.collectLatest {
                        when(it){
                            is Resource.Success -> {
                                Toast.makeText(this@RegisterActivity, "Register Success", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            }
                            is Resource.Loading -> {}
                            is Resource.Error -> {
                                Toast.makeText(this@RegisterActivity, "${it.message}", Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                }
            }
        }
    }
}