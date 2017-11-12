package com.example.sayan.listviewcustomarraylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //steps to follow when creating a custom listview with array list and model objects
    //Prerequisite: get all the resources for the child data in one place
    //step 1: add a listview in your activity layout
    //step 2: find the listview by id in your activity java code
    //step 3: create a child layout without any context
    //step 4: define a custom adapter class
    //step 5: set the custom adapter object in the listview

    //resources for creating child objects
    private String[] textList = {"One", "Two", "Three", "Four", "Five", "Six"};
    //declare an array list with Child class type(model object)
    private ArrayList<Child> childList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create each child object with resources and add it to the array list using for loop
        for (int i = 0; i < 6; i++) {
            childList.add(new Child(R.drawable.image, textList[i]));    //here same image has been used to all child objects
        }
        //step 2
        ListView listView = (ListView) findViewById(R.id.listview_id);
        //step 5
        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);
    }

    //step 4
    //for detail explanation refer to previous project ListViewCustom
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return childList.size();
        }

        @Override
        public Object getItem(int i) {
            return childList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View rootView = inflater.inflate(R.layout.child_layout, null);
            ImageView imageView = rootView.findViewById(R.id.imageview_child_id);
            TextView textView = rootView.findViewById(R.id.textview_child_id);

            //childList.get(i) returns a child model object corresponding to the position
            imageView.setImageDrawable(getDrawable(childList.get(i).getImage()));
            textView.setText(childList.get(i).getText());
            return rootView;
        }
    }
}
