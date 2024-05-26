package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        val txtName = findViewById<TextView>(R.id.edName)
        val txtCarrera = findViewById<TextView>(R.id.edCarrera)
        val txtPhone = findViewById<TextView>(R.id.edPhone)

        txtName.text = preferences.getString("name", "xxx")
        txtCarrera.text = preferences.getString("carrera", "xxx")
        txtPhone.text = preferences.getString("phone", "xxx")


    }

    fun saveEdit(v: View) {
        val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        val editor = preferences.edit()

        val txtName = findViewById<TextView>(R.id.edName)
        val txtCarrera = findViewById<TextView>(R.id.edCarrera)
        val txtPhone = findViewById<TextView>(R.id.edPhone)

        editor.putString("name", txtName.text.toString())
        editor.putString("carrera", txtCarrera.text.toString())
        editor.putString("phone", txtPhone.text.toString())

        editor.apply()
        Toast.makeText(this, "Save user", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}