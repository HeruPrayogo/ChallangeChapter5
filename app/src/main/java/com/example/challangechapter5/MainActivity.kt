package com.example.challangechapter5

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.challangechapter5.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
        binding.Register.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        getAcc()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getAcc(){
        sharedPreferences = getSharedPreferences("InsertAcc", MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            var getEmail = sharedPreferences.getString("email", "")
            var getPass = sharedPreferences.getString("pass", "")
            var email = binding.insEmail.text.toString()
            var pass = binding.insPass.text.toString()
                if(email == getEmail.toString() && pass == getPass.toString()){
                    signInWithEmailAndPassword(email = getEmail.toString(), password = getPass.toString())
                }
        }
    }
    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login berhasil
                    Toast.makeText(this,"Login Berhasil", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}