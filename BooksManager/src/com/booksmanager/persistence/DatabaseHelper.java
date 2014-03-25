package com.booksmanager.persistence;

import java.util.ArrayList;

import com.booksmanager.business.Book;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

 
	
	private static final String TAG = null;

	public DatabaseHelper(Context context) {
        super(context, "booksmanager", null, 1);
  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		  db.execSQL("CREATE TABLE libros ("+
                  "_id_book INTEGER PRIMARY KEY AUTOINCREMENT, "+
                  "title TEXT, "+"writer TEXT, "+"category TEXT, "+
                  "date_start TEXT, "+" date_end TEXT, "+"valoration LONG, "+"total_days INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	/*Guardar un libro en la BBDD con toda la informaci�n asociada. */
	public boolean saveBook(Book book)
	{
        SQLiteDatabase db = getWritableDatabase();
        try
        {
        	db.execSQL("INSERT INTO libros VALUES ( null, '"+
                      book.getTitle()+"', '"+book.getWriter()+"', '"+book.getCategory()+"', '"
                      +book.getDateStart()+"', '"+book.getDateEnd()+"', "+book.getValoration()+", "+
                      book.getDaysReading()+")");
        	db.close();
        	return true;
        }
        catch (SQLException sql)
        {
            db.close();
            Log.v(TAG,"Error en DatabaseHelper. Problemas al guardar el libro");
        	return false;
        }
	}
	
	public ArrayList <Book> uploadBooks ()
	{
		ArrayList <Book> booksList = new ArrayList <Book>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		try
		{
			cursor = db.rawQuery("SELECT * FROM libros",null);
		}
        catch (SQLException sql)
        {
            db.close();
            Log.v(TAG,"Error en DatabaseHelper. Problemas al cargar los libros");
        	return null;
        }
		while (cursor.moveToNext())
		{
			Book book = new Book(cursor.getString(1),cursor.getString(2),cursor.getString(3),
					cursor.getString(4),cursor.getString(5),cursor.getFloat(6));
			booksList.add(book);
		}
		return booksList;
	}
}