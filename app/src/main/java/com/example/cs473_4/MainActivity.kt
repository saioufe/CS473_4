package com.example.cs473_4

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cs473_4.data.User


class MainActivity : AppCompatActivity() {
    lateinit var edt_email: EditText
    lateinit var edt_password: EditText
    lateinit var bts_signin : Button
    lateinit var btn_create : Button
    var users : MutableList<User> = mutableListOf(
        User("saif","alnuaimi","saif@gmail.com","pass1"),
        User("ghaith","alnuaimi","ghaith@gmail.com","pass2"),
        User("laith","alnuaimi","laith@gmail.com","pass3"),
        User("maher","alnuaimi","maher@gmail.com","pass4"),
    )
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("saioufe","in the result ok")
            val returnValue: User = result.data?.getSerializableExtra("user") as User
            users.add(returnValue)
            if(returnValue !=null){
                Log.d("saioufe","in the return not null")
                edt_email.setText(returnValue.email)
                edt_password.setText(returnValue.password)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)
        btn_create = findViewById(R.id.btn_create)
        bts_signin = findViewById(R.id.btn_signin)

        btn_create.setOnClickListener(){
            var intent : Intent = Intent(this,CreateAccount::class.java)
            resultLauncher.launch(intent)

        }
        bts_signin.setOnClickListener(){
            var email: String = edt_email.text.toString()
            var password : String = edt_password.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                var foundUser : User?  =   users.find { user -> user.email.equals(email) && user.password.equals(password) }
                if(foundUser != null){
                    var intent : Intent = Intent(this,Category::class.java)
                    intent.putExtra("user",foundUser)
                    startActivity(intent)
                }else {
                    Toast.makeText(this,"No user has found!!", Toast.LENGTH_SHORT).show()
                }
            }else {
                edt_email.error = "Make sure it's right"
                edt_password.error = "Make sure it's right"
            }
        }


    }

    fun forgetPassword(view: View) {
        if(edt_email.text.isEmpty()){
            Toast.makeText(this,"Please enter your email first in the email section", Toast.LENGTH_LONG).show()
        }else {
            val recipient = "alnaimysaioufe@gmail.com"
            val subject = "Forget Password"
            val message = users.find { user -> user.email.equals(edt_email.text.toString())  }

            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)
        }

    }


    private fun sendEmail(recipient: String, subject: String, message: User?) {

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"


        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message?.password.toString())


        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}