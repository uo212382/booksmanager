package com.booksmanager.presentation;

import java.util.ArrayList;
import java.util.Iterator;

import com.booksmanager.R;
import com.booksmanager.business.Book;
import com.booksmanager.persistence.BooksDao;







import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends ActionBarActivity implements BookListFragment.Callbacks  {	

	private static final int RESULTADO = 0;	
	private boolean mUserLearnedDrawer;
	private static final String USER_LEARNED_DRAWER = "user_learned_drawer";
	private static final String PREFERENCES = "preferences";
	private static final String TAG = null;
	private String [] recursos;
	private TypedArray navIcons;
	private DrawerAdapter adapter; //Adaptador para el drawer.
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView details_Books;
	private DrawerLayout navDrawerLayout;
	private ListView navList;	private ArrayList<ItemDrawer> items_Drawer; //Items del drawer (compuesto de Title + Icon)

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.book_list_single_pane);
		boolean fromSavedInstanceState = false;
		if (savedInstanceState != null) //Cargar valores pues no es el primer acceso.
		{
			mUserLearnedDrawer = savedInstanceState.getBoolean(USER_LEARNED_DRAWER);
			fromSavedInstanceState=true;
		}
		else //Primer acceso a la aplicación. Cargar desde la BBDD los libros.
		{
			// Restaurar el estado desde las preferencias. Cargarlas.
			SharedPreferences prefs = getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
			mUserLearnedDrawer = prefs.getBoolean(USER_LEARNED_DRAWER, false);
		}
		inicializateDrawer(fromSavedInstanceState);
	}	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		//super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case R.id.action_add_book:
				addBook();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	//Función encargada de inicializar el Drawer.
	private void inicializateDrawer(boolean fromSavedState)
	{		
		navDrawerLayout = (DrawerLayout) findViewById(R.id.menu_drawer);
        navList = (ListView) findViewById(R.id.lista);
		items_Drawer=createdEntryes();
        // Se carga en un ArrayAdapter en forma de lista para visualizarlo.
        adapter = new DrawerAdapter(this,items_Drawer);
        navList.setAdapter(adapter);

        //Control del Drawer.
		// Mostrar el icono del drawer
		final ActionBar actionBar = getSupportActionBar();
		mDrawerToggle = new ActionBarDrawerToggle(this,navDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) 
		{
			// Se llama cuando el Drawer se acaba de cerrar
			public void onDrawerClosed(View view) 
			{
				super.onDrawerClosed(view);	
				// Actualizar las acciones en el Action Bar
				supportInvalidateOptionsMenu();
			}

			// Se llama cuando el Drawer se acaba de abrir
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);	
				// Actualizar las acciones en el Action Bar
				mUserLearnedDrawer = true;
				supportInvalidateOptionsMenu();
			}         
		};
		
		if (!mUserLearnedDrawer && !fromSavedState) 
		{
			navDrawerLayout.openDrawer(navList);
		} 
	}
	
	
	/*Función addBook. Se limita a llamar a una segunda actividad encargada de recoger por pantalla los datos
	 * insertados por el usuario y almacenarlos. */	
	private void addBook()
	{
		Intent intent = new Intent(this, AddBook.class);
		startActivityForResult(intent,RESULTADO);
	}
	
	/*Captura de resultados a partir de la función anterior. Uso de startActivityForResult */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    /*Comunicación con el fragmento BookListFragment */
		FragmentManager fragmentManager = getSupportFragmentManager();	        	
    	BookListFragment fragment = (BookListFragment) fragmentManager.findFragmentById(R.id.book_list_frag);        	
	    String title;
	    String writer;
	    String category;
	    String dateStart;
	    String dateEnd;
	    Log.v(TAG,"Insert ");
	    float valoration;
	    if (resultCode == RESULT_OK)
	    {	    	
		    title = data.getExtras().getString("title");
			writer = data.getExtras().getString("writer"); 
		    category = data.getExtras().getString("category");
		    dateEnd = data.getExtras().getString("dateEnd");
		    dateStart = data.getExtras().getString("dateStart");
		    valoration= data.getExtras().getFloat("valoration");
			Book book = new Book (title,writer,category,dateStart,dateEnd,valoration);
			fragment.setBook(book);
	    }	    
	}		
		
		//Función que crea las entradas del Drawer. Asocia un título con un icono y devuelve un array de los mismos.
		public ArrayList<ItemDrawer> createdEntryes ()
		{
	        //Se obtienen las imágenes definidas en el array de recursos.
	        navIcons = getResources().obtainTypedArray(R.array.nav_icons);
	        // Se carga el array con las opciones del Drawer.
	        recursos = getResources().getStringArray( R.array.nav_options);
	        //Se meten en un ArrayList del tipo 'ItemDrawer'
	        items_Drawer = new ArrayList<ItemDrawer> ();
	        //Agregar los distintos objetos al ArrayList anterior. Incluye los textos de los elementos y sus iconos.
	        for (int i=0; i<recursos.length; i++)
	        {		        
		        items_Drawer.add(new ItemDrawer(recursos[i],navIcons.getResourceId(i,-1)));	
	        }
	        
	        return items_Drawer;
		}
		
		@Override
		public void onBookSelected(Book book)
		{
			// Arrancar la actividad para mostrar los detalles
			Intent intent = new Intent(this, BookDetailsActivity.class);
			intent.putExtra(BookDetailsActivity.TITLE, book.getTitle());
			intent.putExtra(BookDetailsActivity.WRITER, book.getWriter());
			intent.putExtra(BookDetailsActivity.CATEGORY, book.getCategory());
			intent.putExtra(BookDetailsActivity.DATESTART, book.getDateStart());
			intent.putExtra(BookDetailsActivity.DATEEND, book.getDateEnd());		
			intent.putExtra(BookDetailsActivity.DAYSREADING, book.getDaysReading());
			intent.putExtra(BookDetailsActivity.VALORATION, book.getValoration());
			startActivity(intent);
		}
}
