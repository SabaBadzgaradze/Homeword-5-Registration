package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.registration.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        next()
        back()

    }

    private fun next(){
        binding.btnNext.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Email and Password cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 6){
                Toast.makeText(this, "Password mast contains 6 symbol!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Invalid Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            registerListener()

        }
    }

    private fun back(){
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun registerListener(){
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val intent = Intent(this, RegisterActivity2::class.java)
        intent.putExtra("EMAIL", email)
        intent.putExtra("PASSWORD", password)
        startActivity(intent)
    }

}