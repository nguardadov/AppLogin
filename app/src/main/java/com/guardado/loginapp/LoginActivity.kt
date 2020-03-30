package com.guardado.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var textUser: EditText
    private lateinit var textPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var btnLogin: Button

    private lateinit var textForgot: TextView
    private lateinit var textRegistrar: TextView

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialise()
    }


    private fun initialise(){

        textUser = findViewById(R.id.txtEmail);
        textPassword = findViewById(R.id.txtPassword);
        textRegistrar=findViewById(R.id.txtRegistrar)
        textForgot=findViewById(R.id.txtForgot)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()
        btnLogin.setOnClickListener{}

        btnLogin.setOnClickListener{login()}
        textRegistrar.setOnClickListener { forgotPassword() }
        textForgot.setOnClickListener { register() }
    }

    private fun forgotPassword()
    {
        startActivity(Intent(this, ForgotPassActivity::class.java))
    }

    private fun register()
    {
        startActivity(Intent(this, CreateAccountActivity::class.java))
    }

    private fun login()
    {
        loginUser()
    }

    private fun loginUser()
    {
        val user:String = textUser.text.toString()
        val password:String = textPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty((password)))
        {
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this)
                {
                    task ->
                    if(task.isSuccessful)
                    {
                        action()
                    }else{
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE
                    }
                }
        }
    }

    private fun action()
    {
        startActivity(Intent(this,MainActivity::class.java))
    }
}
