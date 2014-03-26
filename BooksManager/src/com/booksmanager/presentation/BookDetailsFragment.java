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

public class BookDetailsFragment extends Fragment{
	
	private static final String TITLE_ARG = "title";
	private static final String WRITER_ARG = "writer";
	private static final String CATEGORY_ARG = "category";
	private static final String DATESTART_ARG ="dateStart";
	private static final String DATEEND_ARG ="dateEnd";
	private static final String VALORATION_ARG = "valoration";
	private static final String DAYSREADING_ARG ="daysReading";
	private static final String TAG = null;
	private TextView tvTitle;
	private TextView tvWriter;
	private TextView tvCategory;
	private TextView tvDateStart;
	private TextView tvDateEnd;
	private TextView tvDaysReading;
	private RatingBar rbValoration;
	
	public static BookDetailsFragment newInstance(Book book)
	{
		BookDetailsFragment fragment = new BookDetailsFragment();
		Bundle args = new Bundle();
		args.putString(TITLE_ARG, book.getTitle());
		args.putString(WRITER_ARG,book.getWriter());
		args.putString(CATEGORY_ARG,book.getCategory());
		args.putString(DATESTART_ARG,book.getDateStart());
		args.putString(DATEEND_ARG,book.getDateEnd());
		args.putFloat(VALORATION_ARG,book.getValoration());
		args.putInt(DAYSREADING_ARG, book.getDaysReading());
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		
		View rootView;
		rootView = inflater.inflate(R.layout.book_details_fragment, container, false);
		tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
		tvCategory = (TextView) rootView.findViewById(R.id.tvCategory);
		tvWriter = (TextView) rootView.findViewById(R.id.tvWriter);
		tvDateStart = (TextView) rootView.findViewById(R.id.tvDateStart);
		tvDateEnd = (TextView) rootView.findViewById(R.id.tvDateEnd);
		rbValoration = (RatingBar) rootView.findViewById (R.id.ratingValoration);
		tvDaysReading = (TextView) rootView.findViewById(R.id.tvDaysReading);
		
		Bundle args = getArguments();		
		if (args != null) 
		{
			String title = args.getString(TITLE_ARG);
			String writer = args.getString(WRITER_ARG);
			String category = args.getString(WRITER_ARG);
			String dateStart= args.getString(DATESTART_ARG);
			String dateEnd= args.getString(DATEEND_ARG);
			Float valoration = args.getFloat(VALORATION_ARG);
			int daysReading = args.getInt(DAYSREADING_ARG);
			tvTitle.setText(title);
			tvCategory.setText(category);
			tvWriter.setText(writer);
			tvDateStart.setText(dateStart);
			tvDateEnd.setText(dateEnd);
			tvDaysReading.setText(Integer.toString(daysReading));
			rbValoration.setRating(valoration);			
		}
		
		return rootView;
	}

	public void setBook(Book book) {
		// TODO Auto-generated method stub
		tvTitle.setText(book.getTitle());
		tvCategory.setText(book.getCategory());
		tvWriter.setText(book.getWriter());
		tvDateStart.setText(book.getDateStart());
		tvDateEnd.setText(book.getDateEnd());
		tvDaysReading.setText(Integer.toString(book.getDaysReading()));
		rbValoration.setRating(book.getValoration());
		
	}

}
