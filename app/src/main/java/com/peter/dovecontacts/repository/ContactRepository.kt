package com.peter.dovecontacts.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.peter.dovecontacts.db.Contact
import com.peter.dovecontacts.db.ContactDatabase
import java.lang.Exception

class ContactRepository (application: Application){




    //디비연결
    private val contactDatabase = ContactDatabase.getInstance(application)
    //Dao 연결
    private val contactDao = contactDatabase!!.contactDao()
    //데이터저장
    private val contacts : LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>>{
        return contacts
    }

    //메인스레드에서는 사용할수없음 원래 여기에 코루틴을 사용해야한다
    fun insert(contact: Contact){
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact)})
            thread.start()
        }catch (e:Exception){}
    }

    fun delete(contact: Contact){
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)})
            thread.start()
        }catch (e:Exception){}
    }


}