package com.applicationdevelopers.fitnessx.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.applicationdevelopers.fitnessx.Model.TimeModel;

public class DBHelperClass extends SQLiteOpenHelper {
    public static final int DatabaseVersion = 40;
    public static final String DatabaseName = "WaterDrinkAlert";
    public static final String Table_name = "Dailywaterneeded";
    public static final String id = "id";
    public static final String Time = "Time";
    public static final String Date = "Date";
    public static final String isDrink = "isDrink";
    public static final String amountofWater = "amountofWater";

    /* String query = "CREATE TABLE " + DBHelperClass.Table_name + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBHelperClass.Time + " TEXT, " +
             DBHelperClass.Date + " TEXT, " + isDrink + " TEXT, " + DBHelperClass.amountofWater + " TEXT " + ") ";
 */
    String query = "CREATE TABLE " + DBHelperClass.Table_name + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBHelperClass.Time +
            " TEXT, " + DBHelperClass.Date + " TEXT, " + DBHelperClass.amountofWater + " TEXT, " + DBHelperClass.isDrink + " TEXT " + ") ";

    public DBHelperClass(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBHelperClass.DatabaseName, factory, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBHelperClass.Table_name);
        onCreate(db);
    }

    public void addTimeTable(TimeModel timeModel) {
        ContentValues contentValues = new ContentValues();
        //   contentValues.put(id, timeModel.getId());
        contentValues.put(Time, timeModel.getTime());
        contentValues.put(Date, timeModel.getDate());
        contentValues.put(isDrink, timeModel.getIsDrink());
        contentValues.put(amountofWater, timeModel.getAmountofWater());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelperClass.Table_name, null, contentValues);
    }

    public boolean CheckifAddedAlready( String datea, String amountofWatera) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + DBHelperClass.Table_name + " where " + DBHelperClass.Date + " = ?" +
                " and " + DBHelperClass.amountofWater + " = ? ", new String[]{ datea, amountofWatera});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}