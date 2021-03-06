package com.peter.dovecontacts.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.dovecontacts.R
import com.peter.dovecontacts.db.Contact
import com.peter.dovecontacts.main.recyclerview.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

//mainviewmodel의 인스턴스를 만들고 관찰하는 역할을 해야한다.
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    // Set contactItemClick & contactItemLongClick lambda
    val adapter = RecyclerViewAdapter({ contact ->
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
        intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
        intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
        startActivity(intent)
    }, { contact ->
        deleteDialog(contact)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val lm = LinearLayoutManager(this)
        main_recyclerview.adapter = adapter
        main_recyclerview.layoutManager = lm
        main_recyclerview.setHasFixedSize(true)


        //직접초기화가아니라 안드로이드 시스템을 통해 생성해준다.
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

        main_button.setOnClickListener {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)

        }


    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Delete selected contact?")
            setNegativeButton("NO") { _, _ -> }
            setPositiveButton("YES") { _, _ ->
                mainViewModel.delete(contact)
            }
            builder.show()
        }
    }
}