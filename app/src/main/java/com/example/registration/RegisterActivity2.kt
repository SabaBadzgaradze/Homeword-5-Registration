package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.registration.databinding.ActivityRegister2Binding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        signUp()
        back()
    }

    private fun signUp(){
        binding.btnSignUp.setOnClickListener {
            val username = binding.edUsername.text.toString()
            if (username.isEmpty()){
                Toast.makeText(this, "Enter Username!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val email = intent.extras?.getString("EMAIL" ).toString()
            val password = intent.extras?.getString("PASSWORD" ).toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
                        Log.d("saba",task.exception.toString())
                    }
                }



        }
    }
    private fun back(){
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}