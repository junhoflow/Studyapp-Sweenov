package com.example.sweenov.ui.studymode

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.sweenov.App


data class Memo(var no:Long?, var content:String, var datetime:Long)

class SqliteHelper(context: Context?, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        val create ="create table memo (`no` integer , content text primary key, datetime integer)"
        db?.execSQL(create)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      //테이블에 변경사항이 있을 경우
        //SqliteHelper()의 생성자를 호출할 때 기존 데이터베이스와 version을 비교해서 높으면 호출됨
    }


    fun insertMemo(memo: Memo){

        //db 가져오기
        val wd = writableDatabase

        //메모를 입력 타입으로 변환
        val values = ContentValues()
        values.put("no", memo.no)
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)
        //db에 넣기
        wd.insert("memo", null, values)
        //db 닫기
        wd.close()

    }

    //data 조회 함수
    fun selectMemo(): Long {



        var content : String = ""
        var datetime:Long = 0
        //val select = "select * from memo order by rowid desc limit 1"
        val select2 = "select * from memo WHERE content = '${App.name}'"
        val rd= readableDatabase
        val cursor = rd.rawQuery(select2, null)
        while (cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            //content = cursor.getString(cursor.getColumnIndex("content"))
            datetime = cursor.getLong(cursor.getColumnIndex("datetime"))

            //val memo = Memo(no,content,datetime)

        }
        cursor.close()
        rd.close()

        //val content = cursor.getString(cursor.getColumnIndex("content"))
        //val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))

        return datetime

    }

    //데이터 수정 함수
    fun updateMemo(memo: Memo){
        val wd = writableDatabase

        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        wd.update("memo",values,"content = '${App.name}'",null)
        wd.close()

    }


    //데이터 삭제함수
    fun deleteMemo(memo: Memo){
        val wd = writableDatabase
       // val delete = "delete from memo where no = ${memo.no}"


        //wd.execSQL(delete)


        wd.delete("memo", "no = ${memo.no}", null)
        wd.close()
    }
}