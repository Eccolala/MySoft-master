package me.chunyu.spike.wcl_circle_reveal_demo.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    //创建书籍管理table语句
    //name 是名字
    //author 是作者
    //price 是图书价格
    public static String CREATE_STORE_TABLE = "create table book ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "author text, "
            + "price integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db != null){
            db.execSQL(CREATE_STORE_TABLE);
            Toast.makeText(mContext,"创建库存表成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(mContext,"已经创建好数据库了哦",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
