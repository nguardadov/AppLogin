package com.guardado.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnLogout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout = findViewById(R.id.btnLogout)
        auth=FirebaseAuth.getInstance()
        btnLogout.setOnClickListener { logout() }
    }


    private  fun logout()
    {
        auth.signOut()
        startActivity(Intent(this,LoginActivity::class.java))
    }
}
