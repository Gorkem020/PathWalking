package com.example.pathwalkings.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pathwalkings.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    val tittleImageFragment = TittleImageFragment()
    val descriptionPathFragment = DescriptionPathFragment()
    val signInFragment = SignInFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        auth = Firebase.auth

        val user = auth.currentUser







    }

}










