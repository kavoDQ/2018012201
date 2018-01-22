package forfun.good.a2018012201;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void CL1(View v)
    {
        File dbFile = new File(getFilesDir(),"students.db");
        InputStream is = getResources().openRawResource(R.raw.students);
        try{
            OutputStream os = new FileOutputStream(dbFile);
            int r;
            while ((r = is.read()) !=-1)
            {
                os.write(r);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CL2(View V)
    {
        File dbFile = new File(getFilesDir(),"students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.rawQuery("Select * from student",null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getInt(2));
        c.moveToNext();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));

    }
    public void CL3(View v)
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        String strSql = "select * from student where _id=?";
        Cursor c = db.rawQuery(strSql, new String[]{"2"});
        c.moveToFirst();
        Log.d("DB", c.getString(1)+","+c.getInt(2));


    }
    public void CL4(View v)
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("student", new String[] {"_id", "name", "score"}, null, null, null, null, null);
        c.moveToFirst();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));
    }
    public void CL5(View v)
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("student", new String[] {"_id", "name", "score"}, "_id=?", new String[] {"2"}, null, null, null);
        c.moveToFirst();
        Log.d("DB", c.getString(3) + "," + c.getInt(2));
    }
    public void CL6(View v) //新增
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("_id", 5);
        cv.put("name", "Mary");
        cv.put("score", 92);
        db.insert("student", null, cv);
        db.close();
    }
    public void CL8(View v)
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("score",88);
        db.update("student",cv,"_id=?",new String[]{"2"});
        db.close();
    }
    public void CL9(View v)
    {
        File dbFile = new File(getFilesDir(), "students.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.delete("student","_id=?",new String[]{"2"});
        db.close();

    }


}

