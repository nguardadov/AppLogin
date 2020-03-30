package com.guardado.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_pass.*

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var textUser: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

    }

    private fun initialise()
    {
        textUser = findViewById(R.id.txtEmail)
        auth=FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar)
    }

    private fun send()
    {
        progressBar.visibility = View.VISIBLE
        val email = txtEmail.text.toString()

        if(!TextUtils.isEmpty(email))
        {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this)
                {
                    task ->
                    if(task.isSuccessful)
                    {
                        startActivity(Intent(this,LoginActivity::class.java))
                    }else{
                        Toast.makeText(baseContext, "Error al recuperar",
                            Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE

                    }
                }
        }
    }
}
