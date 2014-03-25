package com.booksmanager.presentation;

import java.util.ArrayList;

import com.booksmanager.R;



import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	//Representa una fila definida por un título y un icono.
	public static class ViewHolder 
	{  
	    TextView titulo;
	    ImageView icono;
	}

	private static final String TAG = null;
	
	public LayoutInflater inflater=null; 
    ArrayList<ItemDrawer> arrayitms=null; 
    
    public DrawerAdapter(Context context, ArrayList<ItemDrawer>  listarray) {  
        super();  
        this.inflater = LayoutInflater.from(context);  
        this.arrayitms=listarray;
        } 
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.arrayitms.size(); 
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.arrayitms.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		   View rowView = convertView; 
		   ViewHolder viewHolder;
	      if(rowView==null)  
	       {  
				rowView = inflater.inflate(R.layout.list_drawer, parent, false);
	           viewHolder = new ViewHolder();
	           //Creo objeto item y lo obtengo del array
	          // ItemDrawer item=arrayitms.get(position);
	           //Titulo
	           viewHolder.titulo = (TextView) rowView.findViewById(R.id.title_item);          
	           //Capturamos el icono
	           viewHolder.icono = (ImageView) rowView.findViewById(R.id.icon);    
	           rowView.setTag(viewHolder); 
	        }  
	        else  
	        {  
	           viewHolder = (ViewHolder) rowView.getTag();  
	        }  
			ItemDrawer course = (ItemDrawer ) getItem(position);
			viewHolder.titulo.setText(course.getTitle());
			viewHolder.icono.setImageResource(course.getIcon());
	        return rowView;  
	    }
	}
	


