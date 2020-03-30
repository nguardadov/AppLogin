package com.guardado.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var textName:EditText
    private lateinit var textLastName:EditText
    private lateinit var textEmail:EditText
    private lateinit var textPassword:EditText
    private lateinit var btnCreate:Button
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initialise()

    }

    private fun initialise()
    {
        textName = findViewById(R.id.txtName);
        textLastName = findViewById(R.id.txtLastName);
        textEmail = findViewById(R.id.txtEmail);
        textPassword = findViewById(R.id.txtPassword);
        btnCreate = findViewById(R.id.btnCreate)
        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference = database.reference.child("Users")
        btnCreate.setOnClickListener{createNewAccount()}
    }

    private fun createNewAccount()
    {
        val firstName = textName.text.toString()
        val lastName = textLastName.text.toString()
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()

        //validate
        if(!TextUtils.isEmpty(firstName) &&  !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            progressBar.visibility = View.VISIBLE

            this.auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this)
                {
                    task ->
                    if(task.isSuccessful)
                    {
                        val user: FirebaseUser? = this.auth.currentUser
                        verifyEmail(user)

                        val userDB = dbReference.child(user?.uid!!)



                        userDB.child("Name").setValue(firstName)
                        userDB.child("lastName").setValue(lastName)
                        action()
                    }else
                    {

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE
                    }
                }

        }
    }

    private fun action()
    {
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun verifyEmail(user:FirebaseUser?)
    {
        user?.sendEmailVerification()?.addOnCompleteListener(this){ task ->
            if(task.isComplete) {
                Toast.makeText(this,"email enviado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"email no enviado",Toast.LENGTH_LONG).show()
            }
        }
    }

}
