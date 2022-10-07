package com.example.cs473_4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cs473_4.data.User


class CreateAccount : AppCompatActivity() {
    lateinit var edt_firstname:EditText
    lateinit var edt_lastname:EditText
    lateinit var edt_email:EditText
    lateinit var edt_password:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        edt_firstname = findViewById(R.id.edt_firstname)
        edt_lastname = findViewById(R.id.edt_lastname)
        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)



    }

    fun createAccount(view: View) {
        var firstname:String = edt_firstname.text.toString()
        var lastname:String = edt_lastname.text.toString()
        var email :String = edt_email.text.toString()
        var password:String = edt_password.text.toString()
        if(firstname.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
            var user :User = User(firstname,lastname,email,password)

            val resultIntent = Intent()
            resultIntent.putExtra("user", user)
            setResult(RESULT_OK, resultIntent)
            finish()
        }else {
            Toast.makeText(this,"Please Enter all the fields",Toast.LENGTH_SHORT).show()
        }
    }
}