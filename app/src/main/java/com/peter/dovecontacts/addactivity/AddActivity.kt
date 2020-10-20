package com.peter.dovecontacts.addactivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.peter.dovecontacts.R
import com.peter.dovecontacts.main.MainViewModel

class AddActivity : AppCompatActivity(){
    private lateinit var mainViewModel: MainViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_add)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


    }
}