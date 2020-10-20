package com.peter.dovecontacts.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContactDao{

    //중복데이터 처리 저장할때 겹치는 거있으면 덮어쓴다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    //전체를 블라올려면 리스트로 불러와야하는데 라이브데이터로 반환한다.
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>


}