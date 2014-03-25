package com.booksmanager.persistence;

import java.util.ArrayList;

import android.util.Log;
import android.content.Context;

import com.booksmanager.business.Book;

/*Data access object. Clase enlace entre la capa de negocio (clase Book.java) y la clase 
 * manejadora de la base de datos (DatabaseHelper.java).
 */

public class BooksDao {
	
	private static final String TAG = null;
	private DatabaseHelper db;
	
	public void createBook (Book book, Context context)
	{
		db= new DatabaseHelper(context);
		if (db != null)
			if (db.saveBook(book) == false )
				Log.v(TAG,"Ha existido un fallo al insertar en la base de Datos");		
	}
	
	public ArrayList <Book> loadBooks (Context context)
	{
		db= new DatabaseHelper (context);
		if (db != null)
			return db.uploadBooks();
		else return null;
	}

}
