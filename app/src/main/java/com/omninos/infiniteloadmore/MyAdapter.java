package com.omninos.infiniteloadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manjinder Singh on 11 , December , 2019
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VHolder> implements Filterable {

    private List<String> list;
    private List<String> tempList;
    Context context;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.tempList = list;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        holder.textData.setText(tempList.get(position));
    }

    @Override
    public int getItemCount() {
        return tempList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    tempList = list;
                } else {
                    ArrayList<String> filteredList = new ArrayList<>();

                    for (String s : list) {
                        if (s.contains(charString)) {
                            filteredList.add(s);
                        }
                    }
                    tempList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = tempList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tempList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class VHolder extends RecyclerView.ViewHolder {

        private TextView textData;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            textData = itemView.findViewById(R.id.textData);
        }
    }
}
