package edu.jsu.mcis.cs408.memopad;

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
    private static final String TABLE_NOTES = "notes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEMO = "memo";


    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE notes (_id integer primary key autoincrement, memo text)";
        db.execSQL(CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase  db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NOTES);
        onCreate(db);

    }
    public void addMemo(Note n){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMO, n.getMemo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }
    public void deleteMemo(Note n){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMO, n.getMemo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NOTES, "id=?",new String[] {String.valueOf(values)});
        db.close();

    }

    public Note getNote(int id) {
        String query = " SELECT * FROM " + TABLE_NOTES + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Note n = null;

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            int newId = cursor.getInt(0);
            String newNote = cursor.getString(1);
            cursor.close();
            n = new Note(newId, newNote);
        }
        db.close();
        return n;
    }
    public String getAllMemosAsList(){
        String query = "SELECT * FROM " + TABLE_NOTES;

        ArrayList<Note> allMemos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            do{
                int newId = cursor.getInt(0);
                String newMemo = cursor.getString(1);
                allMemos.add(new Note(newId, newMemo));
            }
            while (cursor.moveToNext());
        }
        db.close();
        return String.valueOf(allMemos);
    }
}
