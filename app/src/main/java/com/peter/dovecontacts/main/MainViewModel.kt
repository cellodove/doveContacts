package com.peter.dovecontacts.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.peter.dovecontacts.db.Contact
import com.peter.dovecontacts.repository.ContactRepository

//화면처리
//Application을 파라미터로 사용한다. repository를 통해서 room데이터베이스의 인스턴스를
//만들때에는 context가 필요하다.하지만 여기서context를 사용하면 액티비티가 디스트로이되면
//메모리 릭이 발생할수있다. 따라서 application Context를 사용하기위해 파라미터로 받는다
class MainViewModel(application: Application) : AndroidViewModel(application){
    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>>{
        return this.contacts
    }

    fun insert(contact: Contact){
        repository.insert(contact)
    }

    fun delete(contact: Contact){
        repository.delete(contact)
    }
}