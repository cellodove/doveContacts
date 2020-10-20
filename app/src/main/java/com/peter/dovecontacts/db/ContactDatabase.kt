package com.peter.dovecontacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//어떤 entity를사용하고 sqlite 버전을 지정한다.
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){

    abstract fun contactDao() : ContactDao

    //인스턴스를 싱글톤으로 맡들어주기위해 사용
    companion object{
        private var INSTANCE : ContactDatabase? = null

        fun getInstance(context:Context): ContactDatabase?{
            if(INSTANCE == null){
                //여러 스레드가 접근하지못하도록 설정
                synchronized(ContactDatabase::class){
                    //데이터빌더로 인스턴스 생성
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDatabase::class.java,"contact")
                            //데이터베이스 갱신될때 기존의 테이블을 버리고 새로 사용
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}