package me.chunyu.spike.wcl_circle_reveal_demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;

/**
 * Created by woops on 16-6-6.
 */
public class BookDB {
    //数据库名
    public static final String DB_NAME = "cookit";
    //数据库版本
    public static final int VERSION = 1;

    private static BookDB bookDB;

    private SQLiteDatabase db;



    /**
     * 构造方法私有化
     */

    private BookDB(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, DB_NAME
                , null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    /**
     * 获取CookitDB的实例
     */
    public synchronized static BookDB getInstance(Context context) {
        if (bookDB == null) {
            bookDB = new BookDB(context);
        }
        return bookDB;
    }

    /**
     * 将BookInfo实体存储到数据库
     */
    public void savaBook(BookInfo bookInfo) {
        if (bookInfo != null) {
            ContentValues values = new ContentValues();
            values.put("name", bookInfo.getName());
            values.put("author", bookInfo.getAuthor());
            values.put("price", bookInfo.getPrice());
            db.insert("book", null, values); // 插入第一条数据
            Log.d("MY","插入数据成功");
        }
    }

    /**
     * 从数据库中读取库存数据
     */
    public List<BookInfo> loadBook() {
        List<BookInfo> list = new ArrayList<BookInfo>();
        Cursor cursor = db
                .query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                BookInfo bookInfo = new BookInfo();


                bookInfo.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                bookInfo.setAuthor(cursor.getString(cursor
                        .getColumnIndex("author")));
                bookInfo.setPrice(cursor.getString(cursor
                        .getColumnIndex("price")));
                list.add(bookInfo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 数据库读取测试
     */
    public void loadDataBaseTest() {
        Cursor cursor = db
                .query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象,取出数据并打印
                String name = cursor.getString(cursor.
                        getColumnIndex("name"));
                String author = cursor.getString(cursor.
                        getColumnIndex("author"));
                String price = cursor.getString(cursor.getColumnIndex
                        ("price"));
                Log.d("MY", "book name is " + name);
                Log.d("MY", "book author is " + author);
                Log.d("MY", "book  price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }
    /**
     * 数据库删除测试
     */
    public void deleteDataBaseTest(){
        db.delete("book", null, null);
        Log.d("MY","删除数据库成功");

    }



}
