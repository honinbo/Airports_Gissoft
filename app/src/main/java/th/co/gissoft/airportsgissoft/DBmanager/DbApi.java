package th.co.gissoft.airportsgissoft.DBmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import th.co.gissoft.airportsgissoft.data.AppConfig;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class DbApi {

    private SQLiteDatabase mDb = null;

    public DbApi()
    {

        mDb = SQLiteDatabase.openDatabase(AppConfig.getmDataBasePath(), null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }
    public boolean Insert(String table, ContentValues values)
    {
//    	return Insert(table,values,"");
//    }
//    public boolean Insert(String table, ContentValues values,String whereClause)
//    {
        boolean sucess = true;
        long row = 0;
        try
        {
            row = mDb.insert(table, null, values);
        }
        catch (SQLException e)
        {
            sucess = false;
            e.printStackTrace();
        }
        if(row < 1){
            sucess = false;
//        	sucess = Update(table, values, whereClause);
        }
        return sucess;
    }

    public void close()
    {
        try
        {
            mDb.close();
        }
        catch (SQLException e)
        {
            // TODO
            // Create Log
        }
    }

    public boolean Update(String table, ContentValues values, String whereClause)
    {
        boolean sucess = true;
        long row = 0;
        try
        {
            row = mDb.update(table, values, whereClause, null);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            sucess = false;
        }
        if(row < 0){
            sucess = false;
        }
        return sucess;
    }

    public boolean Delete(String table, String whereClause)
    {
        boolean sucess = true;
        long row = 0;
        try
        {
            row = mDb.delete(table, whereClause, null);
        }
        catch (SQLException e)
        {
            sucess = false;
        }
        if(row < 0){
            sucess = false;
        }
        return sucess;
    }

    public Cursor Select(String tableName, String where, String orderBy)
    {
        Cursor cursor = null;
        try
        {
            cursor = mDb.query(tableName, null, where, null, null, null, orderBy);
        }
        catch (SQLException e)
        {
            cursor = null;
            // TODO
            // Create Log
        }
        return cursor;

    }

    public Cursor RawSelect(String sqlCom)
    {
        Cursor cursor = null;
        try
        {
            cursor = mDb.rawQuery(sqlCom, null);
        }
        catch (SQLException e)
        {
            cursor = null;
        }
        return cursor;
    }
}
