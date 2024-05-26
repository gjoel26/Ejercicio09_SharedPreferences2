package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private var user = ""
    private var pass = ""
    private var login = false


    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText

    private lateinit var  txtInputUser: TextInputLayout
    private lateinit var txtInputPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        user = preferences.getString("user", "xxx").toString()
        pass = preferences.getString("password", "xxx").toString()
        login = preferences.getBoolean("login", false)

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPass)

        txtInputUser = findViewById(R.id.txtInputUser)
        txtInputPassword = findViewById(R.id.txtInputPassword)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //1.    The login is true or false
        if (login) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun authenticate(v: View) {
        val user = txtUser.text.toString()
        val password = txtPassword.text.toString()

        if (user.isEmpty() || password.isEmpty()) {
            txtInputPassword.error = "No vacios"
            txtInputUser.error = "No vacios"

        } else if (!this.user.equals("xxx") && user.equals(this.user) && password.equals(this.pass)) {

            // 2.   The login equals true
            val preferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putBoolean("login", true)
            edit.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            txtInputUser.error = "User incorrect"
            txtInputPassword.error = "Password incorrect"
        }
    }

    fun registryNew (v: View) {
        val intent = Intent(this, RegistryActivity2::class.java)
        startActivity(intent)
    }

}