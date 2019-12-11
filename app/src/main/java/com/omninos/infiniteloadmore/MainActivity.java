package com.omninos.infiniteloadmore;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progress_circular;


    MyAdapter adapter;
    private List<String> list = new ArrayList<>();
    private EditText edit_query;


    boolean isScrolling = false;
    int currentItes, totalItem, scrollOutItems, page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progress_circular = findViewById(R.id.progress_circular);
        edit_query = findViewById(R.id.edit_query);

        linearLayoutManager = new LinearLayoutManager(this);

        for (int i = 0; i <= 20; i++) {
            list.add("Item data is -> " + String.valueOf(i));
        }

        edit_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {
                    adapter.getFilter().filter(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        adapter = new MyAdapter(list, this);
        recyclerView.setAdapter(adapter);
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
                if (isScrolling && (currentItes + scrollOutItems == totalItem)) {
                    //data fatch
                    isScrolling = false;
                    fatchData();
                }
            }
        });

    }

    private void fatchData() {
        page++;
        progress_circular.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String start = page + "1";
                String end = (page + 1) + "0";
                for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++) {
                    list.add("New data ->" + i);
                    adapter.notifyDataSetChanged();
                    progress_circular.setVisibility(View.GONE);
                }
            }
        }, 5000);
    }
}
