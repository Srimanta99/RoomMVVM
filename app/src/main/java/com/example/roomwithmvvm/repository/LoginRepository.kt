package com.example.roomwithmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomwithmvvm.model.LoginTableModel
import com.example.roomwithmvvm.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO

class LoginRepository {
companion object {

    var loginDatabase: LoginDatabase? = null

    var loginTableModel: LiveData<LoginTableModel>? = null

    fun initializeDB(context: Context) : LoginDatabase {
        return LoginDatabase.getDataseClient(context)
    }

    fun insertData(context: Context, username: String, password: String) {

        loginDatabase = initializeDB(context)

        CoroutineScope(IO).launch {
            val loginDetails = LoginTableModel(username, password)
            loginDatabase!!.loginDao().InsertData(loginDetails)
        }

    }

    fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {

        loginDatabase = initializeDB(context)

        loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

        return loginTableModel
    }

}
}