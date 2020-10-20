package com.peter.dovecontacts.addactivity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.peter.dovecontacts.R
import com.peter.dovecontacts.db.Contact
import com.peter.dovecontacts.main.MainViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(){
    private lateinit var mainViewModel: MainViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_add)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //데이터가 입력되었는지 확인하고 저장
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER)
            && intent.hasExtra(EXTRA_CONTACT_ID)){
            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }


        add_button.apply {
            setOnClickListener {
                val name = add_edittext_name.text.toString().trim()
                val number = add_edittext_number.text.toString()

                if (name.isNotEmpty()||number.isNotEmpty()){
                    Toast.makeText(this@AddActivity, "Please enter name and number.", Toast.LENGTH_SHORT).show()
                }else{
                    val initial = name
                    val contact = Contact(id, name, number, initial)
                    mainViewModel.insert(contact)
                    finish()
                }
            }
        }

    }


    companion object{
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }


}