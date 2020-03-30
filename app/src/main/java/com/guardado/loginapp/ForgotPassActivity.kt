package com.guardado.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var textUser: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

    }

    private fun initialise()
    {
        textUser = findViewById(R.id.txtEmail)
    }
}
