package com.example.cloudbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CloudBank.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_TRANSACTIONS = "transactions";
    private static final String COLUMN_TRANSACTION_ID = "id";
    private static final String COLUMN_TRANSACTION_ACCOUNT_ID = "account_id";
    private static final String COLUMN_TRANSACTION_DESCRIPTION = "description";
    private static final String COLUMN_TRANSACTION_CREATED_AT = "created_at";
    private static final String COLUMN_TRANSACTION_EXPIRE_AT = "expire_at";
    private static final String COLUMN_TRANSACTION_AMOUNT = "amount";
    private static final String COLUMN_TRANSACTION_RECIPIENT = "recipient";

    // BENEFICIARIES TABLE
    private static final String TABLE_NAME_BENEFICIARIES = "beneficiaries";
    private static final String COLUMN_BENEFICIARY_ID = "id";
    private static final String COLUMN_BENEFICIARY_ACCOUNT_ID = "account_id";
    private static final String COLUMN_BENEFICIARY_NAME = "name";
    private static final String COLUMN_BENEFICIARY_CREATED_AT = "created_at";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "CREATE TABLE " + TABLE_NAME_BENEFICIARIES + " ( "+
                COLUMN_BENEFICIARY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BENEFICIARY_ACCOUNT_ID + " TEXT, "+
                COLUMN_BENEFICIARY_NAME + " TEXT , "+
                COLUMN_BENEFICIARY_CREATED_AT+ " TEXT "
                +"); ";
        db.execSQL(query2);

        String query = "CREATE TABLE " + TABLE_NAME_TRANSACTIONS + " ( "+
                COLUMN_TRANSACTION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TRANSACTION_ACCOUNT_ID + " TEXT, "+
                COLUMN_TRANSACTION_DESCRIPTION + " TEXT, "+
                COLUMN_TRANSACTION_CREATED_AT +" TEXT, "+
                COLUMN_TRANSACTION_EXPIRE_AT +" TEXT, "+
                COLUMN_TRANSACTION_AMOUNT +" INTEGER, "+
                COLUMN_TRANSACTION_RECIPIENT + " INTEGER, "+
                "FOREIGN KEY ( "+COLUMN_TRANSACTION_RECIPIENT+" ) REFERENCES "+TABLE_NAME_BENEFICIARIES+"("+COLUMN_BENEFICIARY_ID+") "+
                "); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_BENEFICIARIES);
        onCreate(db);
    }

    public void addBeneficiary(String accountid, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        cv.put(COLUMN_BENEFICIARY_ACCOUNT_ID, accountid);
        cv.put(COLUMN_BENEFICIARY_NAME, name);
        cv.put(COLUMN_BENEFICIARY_CREATED_AT, strDate);
        long result = db.insert(TABLE_NAME_BENEFICIARIES, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Benficiary Added", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeBeneficiary(String accountid){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME_BENEFICIARIES, COLUMN_BENEFICIARY_ID+"=?", new String[]{String.valueOf(accountid)});
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Benficiary Removed", Toast.LENGTH_SHORT).show();
        }
    }

    public void addTransaction(String accountid, String description, String recipient_id, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        String strDateExpire = sdf.format(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);


        cv.put(COLUMN_TRANSACTION_ACCOUNT_ID, accountid);
        cv.put(COLUMN_TRANSACTION_DESCRIPTION, description);
        cv.put(COLUMN_BENEFICIARY_CREATED_AT, strDate);
        cv.put(COLUMN_TRANSACTION_EXPIRE_AT, strDateExpire);
        cv.put(COLUMN_TRANSACTION_AMOUNT, amount);
        cv.put(COLUMN_TRANSACTION_RECIPIENT, recipient_id);
        long result = db.insert(TABLE_NAME_TRANSACTIONS, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Transacation Created Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllBeneficiaries() {
        String query = "SELECT * FROM " + TABLE_NAME_BENEFICIARIES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}