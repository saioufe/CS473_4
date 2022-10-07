package com.example.cs473_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.cs473_4.data.User

class Category : AppCompatActivity() {
    lateinit var topTitle:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        topTitle = findViewById(R.id.txt_title)

        var intent: Intent = intent
        var user : User =   intent.getSerializableExtra("user") as User;

        if(user!= null){
            topTitle.text = "Welcome ${user.email}"
        }
    }

    fun categoryPressed(view: View) {
        when(view.id){
            R.id.btn_electronic -> Toast.makeText(this,"You have chosen the Electronic category" ,Toast.LENGTH_SHORT).show()
            R.id.btn_clothes -> Toast.makeText(this,"You have chosen the Clothes category" ,Toast.LENGTH_SHORT).show()
            R.id.btn_food -> Toast.makeText(this,"You have chosen the Food category" ,Toast.LENGTH_SHORT).show()
            R.id.btn_makeup -> Toast.makeText(this,"You have chosen the Makeup category" ,Toast.LENGTH_SHORT).show()
        }
    }
}