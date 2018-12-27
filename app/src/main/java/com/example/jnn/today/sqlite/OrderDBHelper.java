package com.example.jnn.today.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库操作类
 * 创建一个数据库+多个表
 */
public class OrderDBHelper extends SQLiteOpenHelper {
    private static final String TAG = OrderDBHelper.class.getSimpleName();
    //数据库名
    private static final String dbName = "orderRecord.db";
    //数据库版本
    private static int dbVersion = 1;
    //数据库操作唯一实例
    public static OrderDBHelper orderDBHelper;

    //主见 id 自动增长
    private static final String SQL_ORDER_ROBBED = "create table order_record(" +
            "_id                     integer PRIMARY KEY AUTOINCREMENT," +
            "_order_id               varchar(20)," +
            "_name             int default(0)," +
            "_age int default(5))";

    /**
     * 获取数据库实例
     */
    public static OrderDBHelper getInstance(Context context) {
        /**
         * 只有在为空的时候，会有同步锁的影响
         */
        if (orderDBHelper == null) {
            synchronized (OrderDBHelper.class) {
                if (orderDBHelper == null) {
                    orderDBHelper = new OrderDBHelper(context);
                }
            }
        }
        return orderDBHelper;
    }


    /**
     * 初始化
     *
     * @param context
     */
    public OrderDBHelper(Context context) {
        /*
         * @param context 上下文
         * @param name    数据库名称
         * @param factory 默认的游标工厂,目的是创建cursor，通常给值null
         * @param version 当前数据库的版本,从1开始,整数
         */
        super(context, dbName, null, dbVersion);
        Log.e(TAG, "OrderDBHelper");

    }


    /**
     * 通过调用getReadableDatabase() 或者 getWritableDatabase()创建数据库
     * 重复调用，数据库只会创建一次，所以不用考虑单例一个数据库对象
     * 当数据库第一次创建的时候调用
     * 适合做表结构的初始化
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate");
        createTables(db);
    }

    /**
     * 当数据库版本需要升级的时候调用
     * 适合做表结构更新的操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade");
        if (oldVersion != newVersion) {
            deleteTables(db);
            createTables(db);
        }
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //每次成功打开数据库后首先被执行
        Log.e(TAG, "onOpen");
    }

    //创建表
    private void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_ORDER_ROBBED);
    }


    //删除表
    //暂时放这些 后期根据本App需求做更改
    private void deleteTables(SQLiteDatabase db) {
        Log.e("OrderRecordHelper",
                "deleteTables(OrderRecordHelper.java:126)");
        // 删除推单序列表
        final String SQL_DELETE_ORDER_SEQUENCE = "drop table order_sequence";
        // 删除被抢缓存表
        final String SQL_DELETE_ORDER_ROBBED = "drop table order_record";

        db.execSQL(SQL_DELETE_ORDER_SEQUENCE);
        db.execSQL(SQL_DELETE_ORDER_ROBBED);
    }

    /**
     * 指定表中插入数据
     *
     * @param database  数据库操作对象
     * @param tableName 表名
     * @param values    要插入的数据（ContentValues键值对）
     * @return 插入是否成功
     */
    public boolean insert(SQLiteDatabase database, String tableName, ContentValues values) {
        long insert = database.insert(tableName, null, values);
        return insert > 0 ? true : false;
    }

    /**
     * 删除指定条件数据
     *
     * @param database    数据库操作对象
     * @param tableName   表名
     * @param whereClause 删除条件 id=?
     * @param whereArgs   删除条件匹配的值
     * @return 删除是否成功
     */
    public boolean delete(SQLiteDatabase database, String tableName, String whereClause, String[] whereArgs) {
        long insert = database.delete(tableName, whereClause, whereArgs);
        return insert > 0 ? true : false;
    }

    /**
     * 更新指定条件数据
     *
     * @param database    数据库操作对象
     * @param tableName   表名
     * @param whereClause 条件 id=?
     * @param values      跟行列ContentValues类型的键值对Key-Value
     * @param whereArgs   条件匹配的值
     * @return 更新是否成功
     */
    public boolean update(SQLiteDatabase database, ContentValues values, String tableName, String whereClause, String[] whereArgs) {
        long insert = database.update(tableName, values, whereClause, whereArgs);
        return insert > 0 ? true : false;
    }


    /**
     * 指定表中查询数据
     *
     * @param database      数据库操作对象
     * @param tableName     表名
     * @param selection     查询条件
     * @param selectionArgs 查询条件匹配的值
     * @return 查询是否成功
     */
    public boolean query(SQLiteDatabase database, String tableName, String selection, String[] selectionArgs) {
        /*
         * 1.表名
         * 2.查询列条件 null则返回全部列
         * 3.查询行条件
         * 4.匹配查询条件 3 的值
         */
        Cursor cursor = database.query(tableName, null, selection, selectionArgs, null, null, null);
        int count = 0;
        //查询成功则cursor不为null
        if (cursor != null && cursor.getCount() != 0) {
            //获取查询到的匹配 数据量
            count = cursor.getCount();
            cursor.close();
        }
        database.close();
        return count > 0;
    }
}
