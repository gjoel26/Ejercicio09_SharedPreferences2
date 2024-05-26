package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var login = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtCarrera = findViewById<TextView>(R.id.txtCarrera)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)

        val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        txtName.text = preferences.getString("name", "xxx")
        txtCarrera.text = preferences.getString("carrera", "xxx")
        txtPhone.text = preferences.getString("phone", "xxx")
        login = preferences.getBoolean("login", false)

        //3.    Is valid login equals true?
        if (!login) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    //4.    Edit the name, address, phone
    fun edit(v: View) {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }

    //5.    Function to close login
    fun close(v: View) {
        val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        val edit = preferences.edit()

        edit.putBoolean("login", false)
        edit.apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}