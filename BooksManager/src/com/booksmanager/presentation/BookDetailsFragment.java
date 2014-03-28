package com.booksmanager.presentation;

import com.booksmanager.R;
import com.booksmanager.business.Book;
import com.booksmanager.persistence.BooksDao;

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
	private static final String DETAILS_BOOK = "bookDetails";
	private TextView tvTitle;
	private TextView tvWriter;
	private TextView tvCategory;
	private TextView tvDateStart;
	private TextView tvDateEnd;
	private TextView tvDaysReading;
	private RatingBar rbValoration;
	private Book firstBook;
	private Book bookSaved;
	
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
		View view=getActivity().findViewById(R.id.course_details_container);
		//Inicialización de los campos del layout.
		tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
		tvCategory = (TextView) rootView.findViewById(R.id.tvCategory);
		tvWriter = (TextView) rootView.findViewById(R.id.tvWriter);
		tvDateStart = (TextView) rootView.findViewById(R.id.tvDateStart);
		tvDateEnd = (TextView) rootView.findViewById(R.id.tvDateEnd);
		rbValoration = (RatingBar) rootView.findViewById (R.id.ratingValoration);
		tvDaysReading = (TextView) rootView.findViewById(R.id.tvDaysReading);
		
		Bundle args = getArguments();		
		if (args != null) //¿Se han recibido argumentos?. De ser así, será que los han pasado desde otra actividad. Mostrarlos.
		{
			String title = args.getString(TITLE_ARG);
			String writer = args.getString(WRITER_ARG);
			String category = args.getString(CATEGORY_ARG);
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
		else 
		{
			firstBook=loadFirstBook();
			if (savedInstanceState!= null)//Hay datos en el Bundle.
			{
				bookSaved = savedInstanceState.getParcelable(DETAILS_BOOK);
				tvTitle.setText(bookSaved.getTitle());
				tvCategory.setText(bookSaved.getWriter());
				tvWriter.setText(bookSaved.getCategory());
				tvDateStart.setText(bookSaved.getDateStart());
				tvDateEnd.setText(bookSaved.getDateEnd());
				tvDaysReading.setText(Integer.toString(bookSaved.getDaysReading()));
				rbValoration.setRating(bookSaved.getValoration());	
			}
			//Se ha obtenido el primer libro de la BD. Es el que se mostrará al abrir la aplicación
			 //si existen libros en la BD y aún no ha sido seleccionado ninguno. (Para evitar que muestre cosas anómalas).
			//Observar además que se compara que la vista sea distinta de null (para saber si estamos en una tableta
			// o en un móvil, para evitar las operaciones del siguiente else-if y ahorrar trabajo computacional innecesario.
			else if (firstBook != null && view != null)
			{
				tvTitle.setText(firstBook.getTitle());
				tvCategory.setText(firstBook.getWriter());
				tvWriter.setText(firstBook.getCategory());
				tvDateStart.setText(firstBook.getDateStart());
				tvDateEnd.setText(firstBook.getDateEnd());
				tvDaysReading.setText(Integer.toString(firstBook.getDaysReading()));
				rbValoration.setRating(firstBook.getValoration());	
			} 
			//Por defecto (BD vacía) se muestran los detalles como vacíos. Aquí también se compara 
			//con que la vista (view) no sea null. Esto se hace porque tanto lo anterior como esto sólo interesa para 
			//dispositivos como tablets, donde se manejan dos fragmentos en paralelo y el fragmento de detalles 
			//SIEMPRE es visible, por lo que hay que tener en cuenta que información mostrarle al usuario.
			else if (view!= null)
			{		
				tvTitle.setText("-----");
				tvCategory.setText("-----");
				tvWriter.setText("-----");
				tvDateStart.setText("-----");
				tvDateEnd.setText("-----");
				tvDaysReading.setText("-----");
				rbValoration.setRating(0);	
			}
		}		
		return rootView;
	}
	
	
	//Salvar la instancia actual en el bundle.
	@Override
	public void onSaveInstanceState(Bundle bundle)
	{
		super.onSaveInstanceState(bundle);
		String title = (String) tvTitle.getText();
		String writer= (String) tvWriter.getText();
		String category = (String) tvCategory.getText();
		String dateStart = (String) tvDateStart.getText();
		String dateEnd = (String) tvDateEnd.getText();
		Float valoration = rbValoration.getRating();
		Book bookSaved = new Book (title,writer,category,dateStart,dateEnd,valoration);
		bundle.putParcelable(DETAILS_BOOK,bookSaved);
	}
	
	//Función loadFirstBook. Encargada de cargar el primer libro de la BD (en función del ID más pequeño) para mostrarlo
	//cuando la aplicación se inicializa y así no mostrar campos con valores no relativos a libros.
	private Book loadFirstBook()
	{
		BooksDao bDao = new BooksDao();
		int id_Book = bDao.loadFirstBook(getActivity());
		if (id_Book != -1) //Comprobar que la BD no esté vacía.
		{
			firstBook=bDao.searchBookById(getActivity(),id_Book);
			return firstBook;
		}
		else 
			return null;
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
