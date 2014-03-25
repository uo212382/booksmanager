package com.booksmanager.presentation;

import java.util.ArrayList;

import com.booksmanager.R;
import com.booksmanager.business.Book;
import com.booksmanager.presentation.DrawerAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter{

	
	//Representa una fila definida por un título y un icono.
	public static class BookHolder
	{  
	    TextView title;
	    ImageView valoration;
	}
	
	public LayoutInflater inflater=null; 
    private ArrayList<Book> arrayBooks=null; 
	
    
    public BooksAdapter(Context context, ArrayList<Book>  listarray) {  
        super();  
        this.inflater = LayoutInflater.from(context);  
        this.arrayBooks=listarray;
        } 
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.arrayBooks.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.arrayBooks.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		  View rowView = convertView; 
		  BookHolder viewHolder;
	      if(rowView==null)  
	       {  
			   rowView = inflater.inflate(R.layout.list_books, parent, false);
	           viewHolder = new BookHolder();
	           viewHolder.title = (TextView) rowView.findViewById(R.id.title_Details); 
	           viewHolder.valoration = (ImageView) rowView.findViewById(R.id.imageValoration); 
	           rowView.setTag(viewHolder); 
	        }  
	        else  
	        {  
	           viewHolder = (BookHolder) rowView.getTag();  
	        }  
			Book book = (Book) getItem(position);
			viewHolder.title.setText(book.getTitle());
			/* Para conocer que icono se debe colocar */
			switch (Float.toString(book.getValoration()))
			{
			case "0.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_0);
				break;
			case "0.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_05);
				break;
			case "1.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_1);
				break;
			case "1.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_15);
				break;
			case "2.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_2);
				break;
			case "2.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_25);
				break;
			case "3.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_3);
				break;
			case "3.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_35);
				break;
			case "4.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_4);
				break;
			case "4.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_45);
				break;
			case "5.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_5);
				break;
			case "5.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_55);
				break;
			case "6.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_6);
				break;
			case "6.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_65);
				break;
			case "7.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_7);
				break;
			case "7.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_75);
				break;
			case "8.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_8);
				break;
			case "8.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_85);
				break;
			case "9.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_9);
				break;
			case "9.5":
				viewHolder.valoration.setImageResource(R.drawable.ic_95);
				break;
			case "10.0":
				viewHolder.valoration.setImageResource(R.drawable.ic_10);
				break;
			}
	        return rowView;  
	    
	}

}
