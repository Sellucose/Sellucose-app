package com.sellucose.sellucosebook.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sellucose.sellucosebook.MainActivity
import com.sellucose.sellucosebook.R
import com.sellucose.sellucosebook.ui.ViewModelFactory
import com.sellucose.sellucosebook.ui.customview.CustomKeywordInputLayout
import com.sellucose.sellucosebook.ui.customview.CustomPasswordInputLayout

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val keywordEditText = findViewById<CustomKeywordInputLayout>(R.id.keywordEditTextLayout)
        val passwordEditText = findViewById<CustomPasswordInputLayout>(R.id.passwordEditTextLayout)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val keyword = keywordEditText.editText?.text.toString()
            val password = passwordEditText.editText?.text.toString()

            if (keywordEditText.checkKeywordValidity() && passwordEditText.checkPasswordValidity()) {
                viewModel.login(keyword, password)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        startAnimations()
    }

    private fun startAnimations() {
        ObjectAnimator.ofFloat(findViewById(R.id.imageView), View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(findViewById(R.id.titleTextView), View.ALPHA, 1f).setDuration(100)
        val keywordTextView = ObjectAnimator.ofFloat(findViewById(R.id.keywordTextView), View.ALPHA, 1f).setDuration(100)
        val keywordEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.keywordEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val passwordTextView = ObjectAnimator.ofFloat(findViewById(R.id.passwordTextView), View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.passwordEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(findViewById(R.id.loginButton), View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                keywordTextView,
                keywordEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }
}