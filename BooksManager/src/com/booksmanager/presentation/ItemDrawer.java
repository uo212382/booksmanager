package com.booksmanager.presentation;

public class ItemDrawer {
	private String titulo;
	private int icono;
	
	public ItemDrawer (String titulo, int icono)
	{
		this.titulo = titulo;
		this.icono=icono;
	}
	
	public void setTitle(String title)
	{
		this.titulo=title;
	}
	
	public void setIcono (int icon)
	{
		this.icono=icon;
	}
	
	public String getTitle ()
	{
		return this.titulo;
	}
	
	public int getIcon()
	{
		return this.icono;
	}

}
