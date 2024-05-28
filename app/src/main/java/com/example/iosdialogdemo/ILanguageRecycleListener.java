package com.example.iosdialogdemo;



public interface ILanguageRecycleListener {
    void itemOnClick(int model,Long id,String title,int position);
    /* mode1 0:id,1:title,2:delete*/
}