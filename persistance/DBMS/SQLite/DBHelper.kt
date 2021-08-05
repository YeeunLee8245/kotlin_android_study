package kr.co.yeaeun.viewbasic

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_VERSION = 1
class DBHelper(context: Context) : SQLiteOpenHelper(context, "memodb", null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        var memoSQL:String = "create table tb_memo"+
                "(_id integer primary key autoincrement,"+
                "title,"+
                "content)";
        db?.execSQL(memoSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion == DATABASE_VERSION){
            db?.execSQL("drop table tb_memo")
            onCreate(db)
        }
    }
//    constructor(context: Context) : this(context){
//        return
//    }
}