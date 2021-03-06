package com.zzu.zk.zhiwen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class CheckedAdapter extends ArrayAdapter<String> {
    private SparseArray<String> saveChecked = new SparseArray<>();
    private boolean isSingle;

    public CheckedAdapter(@NonNull Context context, String[] objects) {
        this(context, objects, false);
    }

    public CheckedAdapter(@NonNull Context context, String[] objects, boolean isSingle) {
        super(context, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, objects);
        this.isSingle = isSingle;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(android.R.id.text1);
        checkedTextView.setChecked(saveChecked.get(position) != null);
        return view;
    }

    public void toggle(int key, java.lang.String value) {
        //单选
        if (isSingle) {
            saveChecked.clear();
            saveChecked.put(key, value);
        }
        //多选
        else {
            if (saveChecked.get(key) == null)
                saveChecked.put(key, value);
            else
                saveChecked.remove(key);
        }
        notifyDataSetChanged();
    }

    public SparseArray<String> getSaveChecked() {
        return saveChecked;
    }
}


