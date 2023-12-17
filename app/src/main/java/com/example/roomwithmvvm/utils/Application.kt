package com.example.roomwithmvvm.utils

import com.example.roomwithmvvm.room.LoginDatabase

open class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        LoginDatabase.getDataseClient(this)
    }
}