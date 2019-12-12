package com.omninos.infiniteloadmore;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progress_circular;


    MyAdapter adapter;
    private List<MyModel.Detail> list = new ArrayList<>();
    List<MyModel.Detail> list1 = new ArrayList<>();
    private EditText edit_query;


    boolean isScrolling = false;
    int currentItes, totalItem, scrollOutItems, page = 20;

    private MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        progress_circular = findViewById(R.id.progress_circular);
        edit_query = findViewById(R.id.edit_query);

        linearLayoutManager = new LinearLayoutManager(this);

        getFirstData("", "");

        getDataMain();

        edit_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {
                    adapter.getFilter().filter(charSequence);
                    isScrolling = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItes = linearLayoutManager.getChildCount();
                totalItem = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItes + scrollOutItems == page)) {
                    //data fatch
                    isScrolling = false;
//                    getFirstData(String.valueOf(totalItem), "10");
                    getnewData(page, 10);
                    page = page + 10;
                }
            }
        });

    }

    private void getnewData(final int page, final int s) {
        progress_circular.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = page; i < page + s; i++) {
                    list1.add(list.get(i));
                    adapter.notifyDataSetChanged();
                }
                progress_circular.setVisibility(View.GONE);

            }
        }, 3000);
    }

    private void getDataMain() {
        viewModel.getData(MainActivity.this).observe(MainActivity.this, new Observer<MyModel>() {
            @Override
            public void onChanged(MyModel myModel) {
                if (myModel.getSuccess().equalsIgnoreCase("1")) {

                    for (int i = 0; i < 20; i++) {
                        MyModel.Detail myModel1 = new MyModel.Detail();
                        myModel1.setName(myModel.getDetails().get(i).getName());
                        list1.add(myModel1);
                    }

                    list = myModel.getDetails();
//                    if (limit.isEmpty()) {

                    adapter = new MyAdapter(list1,myModel.getDetails(), MainActivity.this);
                    recyclerView.setAdapter(adapter);
//                    }

                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getFirstData(final String limit, String count) {
        System.out.println("Data Is: " + limit + "  :" + count);
        progress_circular.setVisibility(View.VISIBLE);

    }

//    private void fatchData() {
//        page++;
//        progress_circular.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                String start = page + "1";
//                String end = (page + 1) + "0";
//                for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++) {
//
//                    adapter.notifyDataSetChanged();
//                    progress_circular.setVisibility(View.GONE);
//                }
//            }
//        }, 5000);
//    }
}
