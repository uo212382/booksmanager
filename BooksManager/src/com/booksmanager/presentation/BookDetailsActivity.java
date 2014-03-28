package com.booksmanager.presentation;



import java.util.ArrayList;

import com.booksmanager.R;
import com.booksmanager.business.Book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class BookDetailsActivity extends ActionBarActivity {
	
	public static final String BOOK = "com.booksmanager.presentation.BOOK";
	private static final String TAG = null;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// ¿ Existe el contenedor del fragmento ?
		if (findViewById(R.id.fragment_container) != null)
		{
			// Si estamos restaurando desde un estado previo no hacemos nada
			if (savedInstanceState != null) 
			{
				return;
			}
			// Crear el fragmento pasándole el parámetro
		Intent intent = getIntent();
		Book book = intent.getParcelableExtra(BOOK);	
		String writer = book.getWriter();
		String title = book.getTitle();
		String category = book.getCategory();
		String dateEnd = book.getDateEnd();
		String dateStart = book.getDateStart();
		float valoration = book.getValoration();
		Book book2 = new Book (title,writer,category,dateStart,dateEnd,valoration);
		BookDetailsFragment fragment =  BookDetailsFragment.newInstance(book2);			
		// Añadir el fragmento al contenedor 'fragment_container'
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}			
	}
	


}
