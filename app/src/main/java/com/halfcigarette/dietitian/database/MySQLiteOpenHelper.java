package com.halfcigarette.dietitian.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.halfcigarette.dietitian.utils.Logger;

/**
 * Created by dongweihang on 2015/11/15.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //public MySQLiteOpenHelper(Context context) {
        /*
        Parameters
        context 上下文环境，用来确定数据库存储的目录
        to use to open or create the database
        name    数据库文件的名字
        of the database file, or null for an in-memory database
        factory 生成游标的工厂，填null就是默认
        to use for creating cursor objects, or null for the default
        version 数据库版本
        number of the database (starting at 1); if the database is older, onUpgrade(SQLiteDatabase, int, int) will be used to upgrade the database;
        if the database is newer, onDowngrade(SQLiteDatabase, int, int) will be used to downgrade the database
         */
        //super(context, "foodtest", null, 1);
    //}

    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
//    2.创建库
//            定义类继承SQLiteOpenHelper, 定义构造函数, 显式调用父类构造函数, 传入4个参数
//    重写onCreate()和onUpgrade()方法
//                            调用getWritableDatabase()或者getReadableDatabase()方法都可以创建数据库
//    数据库文件不存在时, 会创建数据库文件, 并且执行onCreate()方法
//    数据库文件存在, 并且版本没有改变时, 不执行任何方法
//    数据库文件存在, 版本提升, 执行onUpgrade()方法
//    3.增删改查
//    增删改都可以使用SQLiteDatabase.execSQL()方法执行SQL语句完成
//    查询方法需要使用SQLiteDatabase.rawQuery()方法进行查询, 得到一个Cursor, 再调用moveToNext()和getString()getInt()等方法获取数据
    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.i("MySQLiteOpenHelper OnCreate");
        db.execSQL("create table app(packageName text PRIMARY KEY,name text)");
        db.execSQL("create table settings(id text PRIMARY KEY,status text,password text,email text)");
        //db.execSQL("INSERT INTO settings (id,status,password,email) VALUES ('host','true','null','null')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.i("MySQLiteOpenHelper OnUpdate");

    }
}
