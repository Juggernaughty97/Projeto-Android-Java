package com.projecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "basededados.db";
    private static final int    DATABASE_VERSION = 1;

    private static final String TABLE_NAME_USER = "utilizadores";
    private static final String COLUMN_ID_USER = "Id";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_EMAIL_USER = "Email";

    private static final String TABLE_NAME_FOR = "formandos";
    private static final String COLUMN_ID_FOR = "Id";
    private static final String COLUMN_NUM = "Numero";
    private static final String COLUMN_NOME = "Nome";
    private static final String COLUMN_TELEFONE = "Telefone";
    private static final String COLUMN_IDADE = "Idade";
    private static final String COLUMN_EMAIL_FOR = "Email";



    public DB_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase DB = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        String queryUser = "create table if not exists " + TABLE_NAME_USER + " (" + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_EMAIL_USER + " TEXT)";
        DB.execSQL(queryUser);

        String queryForm = "create table if not exists " + TABLE_NAME_FOR + " (" + COLUMN_ID_FOR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NUM + " INTEGER, " + COLUMN_NOME + " TEXT, " + COLUMN_TELEFONE + " TEXT, " + COLUMN_IDADE + " INTEGER, " + COLUMN_EMAIL_FOR + " TEXT)";
        DB.execSQL(queryForm);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean CheckLogin(String Username, String Password){

        SQLiteDatabase DB = this.getReadableDatabase();

        long NumEntries = DatabaseUtils.queryNumEntries(DB, TABLE_NAME_USER, COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[] {Username, Password});

        if (NumEntries == 0)
            return false;

        return true;
    }

    public boolean InserUser(String Username, String Password, String Email){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, Username);
        contentValues.put(COLUMN_PASSWORD, Password);
        contentValues.put(COLUMN_EMAIL_USER, Email);

        long result = DB.insert(TABLE_NAME_USER, null, contentValues);

        if (result == -1)
            return false;

        return true;

    }

    public boolean InserFormando(int Numero, String Nome, String Telefone, int Idade, String Email){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NUM, Numero);
        contentValues.put(COLUMN_NOME, Nome);
        contentValues.put(COLUMN_TELEFONE, Telefone);
        contentValues.put(COLUMN_IDADE, Idade);
        contentValues.put(COLUMN_EMAIL_FOR, Email);

        long result = DB.insert(TABLE_NAME_FOR, null, contentValues);

        if (result == -1)
            return false;

        return true;
    }

    public Cursor LerFormandos (){
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "select * from " + TABLE_NAME_FOR;

        Cursor result = DB.rawQuery(query, null);

        return result;
    }

    public boolean UpdateFormando (int Id, int Numero, String Nome, String Telefone, int Idade, String Email){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID_FOR, Id);
        contentValues.put(COLUMN_NUM, Numero);
        contentValues.put(COLUMN_NOME, Nome);
        contentValues.put(COLUMN_TELEFONE, Telefone);
        contentValues.put(COLUMN_IDADE, Idade);
        contentValues.put(COLUMN_EMAIL_FOR, Email);

        long result = DB.update(TABLE_NAME_FOR, contentValues, COLUMN_ID_FOR + " = ?", new String[] {Integer.toString(Id)});

        if (result == -1)
            return false;

        return true;
    }

    public Integer DeleteFormando (int Id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_FOR, COLUMN_ID_FOR + " = ?", new String[] {Integer.toString(Id)});
    }

    public ArrayList<Dados> GetAllDados()
    {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<Dados> dadosArrayList = new ArrayList<>();

        String query = "select * from formandos";
        Cursor cursor = DB.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            int Id = cursor.getInt(0);
            int Numero = cursor.getInt(1);
            String Nome = cursor.getString(2);
            String Telefone = cursor.getString(3);
            int Idade = cursor.getInt(4);
            String Email = cursor.getString(5);

            Dados dados = new Dados(Id, Numero, Nome, Telefone, Idade, Email);

            dadosArrayList.add(dados);
        }

        return dadosArrayList;
    }


    public Dados GetDadosById(int _Id)
    {
        SQLiteDatabase DB = this.getReadableDatabase();

        Dados dados = null;

        String query = "select * from formandos where Id = " + _Id;
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.getCount() == 1)
        {
            cursor.moveToFirst();

            int Id = cursor.getInt(0);
            int Idade = cursor.getInt(1);
            String Nome = cursor.getString(2);
            String Telefone = cursor.getString(3);
            int Numero = cursor.getInt(4);
            String Email = cursor.getString(5);

            dados = new Dados(Id, Numero, Nome, Telefone, Idade, Email);
        }

        return dados;
    }




}
