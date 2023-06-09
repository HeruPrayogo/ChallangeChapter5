package com.example.challangechapter5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challangechapter5.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityRegisterBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertAcc()
    }


    fun insertAcc(){
        sharedPreferences = getSharedPreferences("InsertAcc", Context.MODE_PRIVATE)
        binding.RegistBtn.setOnClickListener {
            var getUser = binding.uss.text.toString()
            var getEmail = binding.getEmail.text.toString()
            var getPassword = binding.pass.text.toString()
            var confirmPassword = binding.cPass.text.toString()

            if (getPassword == confirmPassword){

                var putInput = sharedPreferences.edit()
                putInput.putString("uss", getUser)
                putInput.putString("email", getEmail)
                putInput.putString("pass", getPassword)
                putInput.apply()
                createUserWithEmailAndPassword(email = getEmail, password = getPassword)
            }
        }
    }
    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Register Berhasil", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Register Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}