package com.booksmanager.presentation;



import com.booksmanager.R;
import com.booksmanager.business.Book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar;
import android.widget.TextView;

public class AddBookActivity extends Activity
{
	private static final String TAG = null;
	private TextView tvTitle;
	private TextView tvWriter;
	private TextView tvCategory;
	private TextView tvDateStart;
	private TextView tvDateEnd;
	private RatingBar rbValue;
	private String title;
	private String writer;
	private String category;
	private String dateStart;
	private String dateEnd;
	private float value=(float) 0.5;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);
        //Inicializar los campos del layout 'add_Book.xml
        tvTitle = (TextView) findViewById(R.id.etTitulo);
        tvWriter = (TextView) findViewById(R.id.etAutor);
        tvCategory = (TextView) findViewById(R.id.etCategoria);
        tvDateStart = (TextView) findViewById(R.id.etFechaInicio);
        tvDateEnd = (TextView) findViewById(R.id.etFechaFin);
        rbValue = (RatingBar) findViewById (R.id.ratingbar);
        
        //Bundle bundle = this.getIntent().getExtras();
    }
	
	/* Manejador de los eventos en el RatingBar. Ante cambios en la puntuación los mismos serán automáticamente
	 * actualizados.
	 */
	  public void addListenerOnRatingBar() {
			//if rating value is changed,
			//display the current rating value in the result (textview) automatically
			rbValue.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
				public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
							rbValue.setRating(rating); //Se actualiza la valoración.			
				}
			});
		  }
	
	public void cancel_newBook (View v)
	{
		finish();
	}
	
	public void newBook (View v)
	{
	    Intent i = getIntent();// Obtenemos el Intent que ha llamado a esta actividad
		/* Se captura la distinta información insertada por el usuario. Faltaría la validación de los campos conflictivos */
		title = tvTitle.getText().toString();
		category = tvCategory.getText().toString();
		writer = tvWriter.getText().toString();
		dateEnd = tvDateEnd.getText().toString();
		dateStart = tvDateStart.getText().toString();
		value = rbValue.getRating();
		/* Se crea un objeto Libro con la información facilitada */
		Book newBook = new Book (title,writer,category,dateStart,dateEnd,value);		
		newBook.saveBookToDb(this);
        i.putExtra("title", title);//Guardamos  la cadena en el intent y le ponemos de nombre resultado
        i.putExtra("category", category);
        i.putExtra("writer", writer);
        i.putExtra("dateStart", dateStart);
        i.putExtra("dateEnd", dateEnd);
        i.putExtra("valoration", value);
        setResult(RESULT_OK, i);
		finish();
	}
    
}

