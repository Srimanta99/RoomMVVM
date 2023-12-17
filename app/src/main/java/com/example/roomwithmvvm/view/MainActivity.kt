package com.example.roomwithmvvm.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.roomwithmvvm.R
import com.example.roomwithmvvm.databinding.ActivityMainBinding
import com.example.roomwithmvvm.model.LoginTableModel
import com.example.roomwithmvvm.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String
    lateinit var activityMainBinding : ActivityMainBinding
    lateinit var userdata : LiveData<LoginTableModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        context = this@MainActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.btnAddLogin.setOnClickListener {

            strUsername = activityMainBinding.txtUsername.text.toString().trim()
            strPassword = activityMainBinding.txtPassword.text.toString().trim()

            if (strPassword.isEmpty()) {
                activityMainBinding.txtUsername.error = "Please enter the username"
            }
            else if (strPassword.isEmpty()) {
                activityMainBinding.txtPassword.error = "Please enter the username"
            }
            else {
                loginViewModel.insertData(context, strUsername, strPassword)
                activityMainBinding.lblInsertResponse.text = "Inserted Successfully"
            }
        }


        activityMainBinding.btnReadLogin.setOnClickListener {
            userdata = loginViewModel.getLoginDetails(this, activityMainBinding.txtUsername.text.toString())!!

            activityMainBinding.lblUseraname.text = userdata.toString()


        }

    }
}