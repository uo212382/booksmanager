package com.booksmanager.presentation;

import com.booksmanager.R;
import com.booksmanager.business.Book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class BookDetailsActivity extends ActionBarActivity{
	
	public static final String TITLE = "com.booksmanager.presentation.title";
	public static final String WRITER = "com.booksmanager.presentation.writer";
	public static final String CATEGORY = "com.booksmanager.presentation.category";
	public static final String DATESTART = "com.booksmanager.presentation.datestart";
	public static final String DATEEND = "com.booksmanager.presentation.dateend";
	public static final String DAYSREADING= "com.booksmanager.presentation.daysreading";
	public static final String VALORATION = "com.booksmanager.presentation.valoration";
	private static final String TAG = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		/*View rootView;
		rootView = inflater.inflate(R.layout.book_details_activity,container, false);
		// Inicializar el layout recuperando los datos. */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details_activity);
		Intent intent = getIntent();
		String title = intent.getStringExtra(TITLE);
		String writer = intent.getStringExtra(WRITER);
		String category = intent.getStringExtra(CATEGORY);
		String date_start = intent.getStringExtra(DATESTART);
		String date_end = intent.getStringExtra(DATEEND);
		//int daysReading = intent.getIntExtra(DAYSREADING,0);
		float valoration = intent.getFloatExtra(VALORATION, 0);
		Book book = new Book (title,writer,category,date_start,date_end,valoration);
		inicializate (book);
		//return rootView;
	}	


	private void inicializate (Book book)
	{
		TextView title = (TextView) findViewById (R.id.tvTitle);
		TextView writer = (TextView) findViewById (R.id.tvWriter);
		TextView category = (TextView) findViewById (R.id.tvCategory);
		TextView dateStart = (TextView) findViewById (R.id.tvDateStart);
		TextView dateEnd = (TextView) findViewById (R.id.tvDateEnd);
		//TextView valoration = (TextView) findViewById (R.id.tvValoracion);
		TextView daysReading = (TextView) findViewById (R.id.tvDaysReading);
		RatingBar valoration = (RatingBar) findViewById (R.id.ratingValoration);
		title.setText(book.getTitle());
		writer.setText(book.getWriter());
		category.setText(book.getCategory());
		dateStart.setText(book.getDateStart());
		dateEnd.setText(book.getDateEnd());
		valoration.setClickable(false);
		valoration.setRating(book.getValoration());
		daysReading.setText(Integer.toString(book.getDaysReading()));
		
	}

}
