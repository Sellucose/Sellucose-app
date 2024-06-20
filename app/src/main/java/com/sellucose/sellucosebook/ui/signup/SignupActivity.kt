package com.sellucose.sellucosebook.ui.signup

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
import com.sellucose.sellucosebook.ui.customview.CustomEmailInputLayout
import com.sellucose.sellucosebook.ui.customview.CustomNameInputLayout
import com.sellucose.sellucosebook.ui.customview.CustomPasswordInputLayout
import com.sellucose.sellucosebook.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailEditText = findViewById<CustomEmailInputLayout>(R.id.emailEditTextLayout)
        val nameEditText = findViewById<CustomNameInputLayout>(R.id.nameEditTextLayout)
        val passwordEditText = findViewById<CustomPasswordInputLayout>(R.id.passwordEditTextLayout)
        val confirmationPasswordEditText = findViewById<CustomPasswordInputLayout>(R.id.confirmationpasswordEditTextLayout)
        val signupButton = findViewById<Button>(R.id.signupButton)

        signupButton.setOnClickListener {
            val email = emailEditText.editText?.text.toString()
            val name = nameEditText.editText?.text.toString()
            val password = passwordEditText.editText?.text.toString()
            val confirmationPassword = confirmationPasswordEditText.editText?.text.toString()

            if (emailEditText.checkEmailValidity() && nameEditText.checkNameValidity() && passwordEditText.checkPasswordValidity() && confirmationPasswordEditText.checkPasswordValidity()) {
                viewModel.register(email, name, password, confirmationPassword)
                val intent = Intent(this, LoginActivity::class.java)
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
        val nameTextView = ObjectAnimator.ofFloat(findViewById(R.id.nameTextView), View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.nameEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val emailTextView = ObjectAnimator.ofFloat(findViewById(R.id.emailTextView), View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.emailEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val passwordTextView = ObjectAnimator.ofFloat(findViewById(R.id.passwordTextView), View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.passwordEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val confirmationPasswordTextView = ObjectAnimator.ofFloat(findViewById(R.id.confirmationpasswordTextView), View.ALPHA, 1f).setDuration(100)
        val confirmationPasswordEditTextLayout = ObjectAnimator.ofFloat(findViewById(R.id.confirmationpasswordEditTextLayout), View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(findViewById(R.id.signupButton), View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                confirmationPasswordTextView,
                confirmationPasswordEditTextLayout,
                signup
            )
            startDelay = 100
        }.start()
    }
}