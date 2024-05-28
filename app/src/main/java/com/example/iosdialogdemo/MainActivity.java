package com.example.iosdialogdemo;



        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity implements ILanguageRecycleListener {
    private LanguageRecycleAdapter languageRecycleAdapter;
    private RecyclerView rvLanguage;
//    private List<String> languageList = new ArrayList<>();
    private EditText editText;
    private Button btn_add,btn_update;
    private UserDao userDao;
    private DaoSession daoSession;

    private List<User> languageList;
    private Long id2;
    private String title2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        daoSession = ((GreenApp) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();

        languageList = new ArrayList<>();

        if (languageList!=null){
            languageList.clear();
        }

        languageList.addAll(userDao.queryBuilder().list());



        rvLanguage = findViewById(R.id.rvLanguage);
        editText= findViewById(R.id.etLanguage);
        btn_add= findViewById(R.id.btn_add);
        btn_update= findViewById(R.id.btn_update);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this);
        rvLanguage.setLayoutManager(layoutManager);
        languageRecycleAdapter = new LanguageRecycleAdapter(languageList, this);
        rvLanguage.setAdapter(languageRecycleAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etString = editText.getText().toString();
               // languageList.add(etString);

                User user = new User();
                user.setName(etString);


                userDao.insert(user);


                if (languageList!=null){
                    languageList.clear();
                }

                languageList.addAll(userDao.queryBuilder().list());



                languageRecycleAdapter.setData(languageList);
                languageRecycleAdapter.notifyDataSetChanged();
            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etString = editText.getText().toString();
                // languageList.add(etString);
                Log.e("TAG", "sql_itemOnClick: " +id2+"==="+title2);

                User user = new User();
                user.setName(etString);
                user.setId(id2);


                userDao.update(user);


                if (languageList!=null){
                    languageList.clear();
                }

                languageList.addAll(userDao.queryBuilder().list());



                languageRecycleAdapter.setData(languageList);
                languageRecycleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void itemOnClick(int model,Long id, String title,int position) {
        Log.e("TAG", "itemOnClick: " + id + ", " + position);
        if (model==2){

            userDao.deleteByKey(id);

            if (languageList!=null){
                languageList.clear();
            }

            languageList.addAll(userDao.queryBuilder().list());


            Log.e("TAG", "sql_itemOnClick: " +languageList);


            languageRecycleAdapter.setData(languageList);
            languageRecycleAdapter.notifyDataSetChanged();

          /*  languageList.remove(position);
            languageRecycleAdapter.setData(languageList);
            languageRecycleAdapter.notifyDataSetChanged();*/
        }else if (model==1){
            editText.setText(title);

            id2 = id;
            title2=title;
        }
    }
}