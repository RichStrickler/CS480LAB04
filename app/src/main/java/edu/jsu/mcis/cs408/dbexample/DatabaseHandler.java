package edu.jsu.mcis.cs408.dbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_MEMOS = "contacts";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEMO = "name";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts (_id integer primary key autoincrement, memo text)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOS);
        onCreate(db);
    }

    public void addMemo(Memo c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMO, c.getMemo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MEMOS, null, values);
        db.close();

    }

    public void removeMemo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MEMOS, "_id=?", new String[]{id});
        db.close();
    }

    public Memo getMemo(int id) {

        String query = "SELECT * FROM " + TABLE_MEMOS + " WHERE " + COLUMN_ID + " = " + id;
        System.out.println("GetMemo: " + id);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Memo c = null;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int newId = cursor.getInt(0);
            String newName = cursor.getString(1);
            cursor.close();
            c = new Memo(newId, newName);
        }

        db.close();
        return c;

    }

    public String getAllMemos() {

        String query = "SELECT * FROM " + TABLE_MEMOS;

        StringBuilder s = new StringBuilder();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                s.append(getMemo(id)).append("\n");
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return s.toString();

    }

    public ArrayList<Memo> getAllMemosAsList() {

        String query = "SELECT * FROM " + TABLE_MEMOS;

        ArrayList<Memo> allMemos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newId = cursor.getInt(0);
                String newMemo = cursor.getString(1);
                allMemos.add( new Memo(newId, newMemo) );
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return allMemos;

    }

    public ArrayList<String> getAllMemosAsStringList() {

        String query = "SELECT * FROM " + TABLE_MEMOS;

        ArrayList<String> allMemos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newId = cursor.getInt(0);
                String newMemo = cursor.getString(1);
                allMemos.add( new Memo(newId, newMemo).toString() );
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return allMemos;

    }

}