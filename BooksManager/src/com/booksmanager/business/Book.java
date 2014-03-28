package com.booksmanager.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.booksmanager.persistence.BooksDao;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Book implements Parcelable{

	private static final String TAG = null;
	private String title;
	private String writer;
	private String category;
	private String date_start;
	private String date_end;
	private float valoration;
	private int days_reading;
	Date start,end;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
	private BooksDao bDao = new BooksDao();

	public Book()
	{
		
	}
	
	public Book(String t, String w, String c, String ds, String de, float v)
	{
		this.title=t;
		this.writer=w;
		this.category=c;
		this.valoration=v;
		date_start=ds;		
		date_end=de;

		
		/*Los días que se ha tardado en leer el libro se calculan mediante función de apoyo.
		previamente habrá que parsear este String contenedor de la fecha a un formato de tipo 'Date' */
		try
		{
		start=dateFormat.parse(date_start);
		}
		catch (ParseException e) 
		{
			start=new Date();
		}
		try
		{
		end=dateFormat.parse(date_end);
		}
		catch (ParseException e)
		{
			end=new Date();
		}
		days_reading=reading_Days(start,end);
		
	}
	
	public Book(Parcel in) {
		// TODO Auto-generated constructor stub
		readFromParcel(in);
	}

	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle (String title)
	{
		this.title=title;
	}
	
	public String getWriter()
	{
		return this.writer;
	}
	
	public void setWriter (String writer)
	{
		this.writer=writer;
	}
	
	public String getCategory()
	{
		return this.category;
	}
	
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	public float getValoration ()
	{
		return this.valoration;
	}
	
	
	public void setValoration(float valoration)
	{
		this.valoration=valoration;
	}	
	
	public String getDateStart ()
	{
		return this.date_start;
	}
	
	public void setDateStart (String dateStart)
	{
		this.date_start=dateStart;
	}
	
	public String getDateEnd ()
	{
		return this.date_end;
	}
	
	public void setDateEnd (String dateEnd)
	{
			this.date_end=dateEnd;
	}
	
	public int getDaysReading ()
	{
		return this.days_reading;
	}
	

	
	public void saveBookToDb(Context context)
	{
		Log.v(TAG,"Titulo: "+this.getTitle());
		Log.v(TAG,"Categoria: "+this.getCategory());
		Log.v(TAG,"Escritor: "+this.getWriter());
		Log.v(TAG,"Date start: "+this.getDateStart());
		Log.v(TAG,"Date end: "+this.getDateEnd());
		Log.v(TAG,"Valoracion: "+this.getValoration());
		Log.v(TAG,"Dias leyendo: "+this.getDaysReading());
		if (bDao != null)
			bDao.createBook(this,context);
		else
			Log.v(TAG,"Libro null");
	}
	
	
	//Función que calcula el número días tardados en leer un libro.
	public static int reading_Days(Date day_start,Date day_end){
		/* Objetos calendario para efectuar la resta. */
		GregorianCalendar date_start = new GregorianCalendar();
		date_start.setTime(day_start); //dateIni es el objeto Date
		GregorianCalendar date_end = new GregorianCalendar();
		date_end.setTime(day_end); //date_endn es el objeto Date
		int rango = 0;
		/* COMPROBAMOS SI ESTAMOS EN EL MISMO AÑO */
		if (date_start.get(Calendar.YEAR) == date_end.get(Calendar.YEAR)) 
		{
			rango = date_end.get(Calendar.DAY_OF_YEAR) - date_start.get(Calendar.DAY_OF_YEAR);
		} 
		else 
		{   //Comprobar que no es bisiesto.
			int daysYear = date_start.isLeapYear(date_start.get(Calendar.YEAR)) ? 366 : 365;
			/* Rango de años*/
			int rankYears = date_end.get(Calendar.YEAR) - date_start.get(Calendar.YEAR);
			/* Rango de días */
			rango = (rankYears * daysYear) + (date_end.get(Calendar.DAY_OF_YEAR) - date_start.get(Calendar.DAY_OF_YEAR));
		}
		return rango;
		}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parc, int flags) {
		// TODO Auto-generated method stub
		parc.writeString(title);
		parc.writeString(writer);
		parc.writeString(category);
		parc.writeString(date_start);
		parc.writeString(date_end);
		parc.writeFloat(valoration);
		parc.writeInt(days_reading);
	}
	
	private void readFromParcel(Parcel parcel) 
	{
		title = parcel.readString();
		writer = parcel.readString();
		category = parcel.readString();
		date_start = parcel.readString();
		date_end = parcel.readString();
		valoration = parcel.readFloat();
		days_reading = parcel.readInt();
	}
		public static final Parcelable.Creator<Book> CREATOR =
		new Parcelable.Creator<Book>() 
		{
			public Book createFromParcel(Parcel in) 
			{
				return new Book(in);
			}
			public Book[] newArray(int size)
			{
				return new Book[size];
			}
		};

	

}
