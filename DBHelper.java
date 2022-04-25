package com.example.sqlite_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "MYDB2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors(id integer primary key, name text, address text, email text)");
        sqLiteDatabase.execSQL("CREATE TABLE Books(id integer primary key, title text, id_author integer not null" +
                " constraint id_author REFERENCES Authors(id) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    //thêm - sửa - xóa - truy vấn
    public int insertAuthor(Author author){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", author.getIdAuthor() + "");
        content.put("name", author.getName());
        content.put("address", author.getAddress());
        content.put("email", author.getEmail());
        int res = (int) db.insert("Authors", null, content);
        db.close();
        return res;
    }

    public int updateAuthor(Author author){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", author.getName());
        content.put("address", author.getAddress());
        content.put("email", author.getEmail());
        int res = (int) db.update("Authors", content, "id = ?",
                new String[]{String.valueOf(author.getIdAuthor())});
        db.close();
        return res;
    }

    public int deleteAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = (int) db.delete("Authors",   "id = ?", new String[]{String.valueOf(author.getIdAuthor())});
        db.close();
        return res;
    }

    public ArrayList<Author> getallAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        String strSQl = "select * from Authors";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(strSQl, null);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public ArrayList<Author> getIdAuthor(int id){
        Author author = new Author();
        ArrayList<Author> list = new ArrayList<>();
        String strSQl = "select * from Authors where id= "+ id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(strSQl, null);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                author.setIdAuthor(cursor.getInt(0));
                author.setName(cursor.getString(1));
                author.setAddress(cursor.getString(2));
                author.setEmail(cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    //thêm - sửa - xóa - truy vấn Book------------------------------------------
    public int insertBook(Book book){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("id", book.getId() + "");
        content.put("title", book.getTitle());
        content.put("id_author", book.getId_author() + "");
        int res = (int) db.insert("Books", null, content);
        db.close();
        return res;
    }

    public ArrayList<Book> getallBook(){
        ArrayList<Book> list = new ArrayList<>();
        String strSQl = "select * from Books";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(strSQl, null);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public ArrayList<Book> getIdBook(int id){
        Book book = new Book();
        ArrayList<Book> list = new ArrayList<>();
        String strSQl = "select * from Authors where id= "+ id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(strSQl, null);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                book.setId(cursor.getInt(0));
                book.setTitle(cursor.getString(1));
                book.setId_author(cursor.getInt(2));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

}
