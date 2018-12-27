package com.example.jnn.today.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;

public class SQLiteActivity extends AppCompatActivity {
    private static final String TAG = SQLiteActivity.class.getSimpleName();
    private OrderDBHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }

    public void connect(View view) {
        instance = OrderDBHelper.getInstance(this);
    }

    public void insert(View view) {
        SQLiteDatabase db = instance.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("_order_id", 2);
        values.put("_name", "贾娜娜");
        values.put("_age", 12);
        long order_record = db.insert("order_record", null, values);
        Log.e(TAG, "插入是否成功" + order_record);
        db.close();
    }

    public void query(View view) {
        SQLiteDatabase db = instance.getReadableDatabase();
        Cursor cursor = db.query("order_record", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    //遍历Cursor对象，并打印
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("_name"));
                    String age = cursor.getString(cursor.getColumnIndex("_age"));
                    Log.e(TAG, "查询到：" + "id=" + id + "\tname=" + name + "\tage=" + age);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
    }

    public void update(View view) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_name", "贾美丽");
        int order_record = db.update("order_record", values, "_id=?", new String[]{"1"});
        Log.e(TAG, "更新结果：" + order_record);
        db.close();
    }

    public void delete(View view) {
        SQLiteDatabase db = instance.getWritableDatabase();
        int order_record = db.delete("order_record", "_id=?", new String[]{"1"});
        Log.e(TAG, "更新结果：" + order_record);
        db.close();
    }
}
