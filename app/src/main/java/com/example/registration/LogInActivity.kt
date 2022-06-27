package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.registration.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logIn()
        back()
    }

    private fun logIn(){
        binding.btnLogIn.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Authorization successful", Toast.LENGTH_SHORT).show()
                        gotoProfile()
                    }else{
                        Toast.makeText(this, "Email or password is not correct!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun back(){
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun gotoProfile() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}