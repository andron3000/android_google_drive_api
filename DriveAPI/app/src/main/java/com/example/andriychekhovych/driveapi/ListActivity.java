package com.example.andriychekhovych.driveapi;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andriy Chekhovych on 03.12.2016.
 */

public class ListActivity  extends Activity {

    private int noOfBtns;
    private int NUM_ITEMS_PAGE = 4;
    private int total_iteams_count;

    private Button[] btns;
    private TextView title;

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listfiles);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> reslist = bundle.getStringArrayList("list");
        total_iteams_count = reslist.size();

        listView = (ListView)findViewById(R.id.list);
        title    = (TextView)findViewById(R.id.title);

        Btnfooter(total_iteams_count, reslist);

        loadList(0, reslist);
        CheckBtnBackGroud(0);

//       adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                reslist);
//
//        listView = (ListView) findViewById(R.id.listViewResults);
//        listView.setAdapter(adapter);
    }

    private void Btnfooter(int TOTAL_LIST_ITEMS, final ArrayList<String> data ) {

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

        LinearLayout ll = (LinearLayout) findViewById(R.id.btnLay);

        btns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("" + (i + 1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    loadList(j,data);
                    CheckBtnBackGroud(j);
                }
            });
        }
    }

    private void CheckBtnBackGroud(int index)
    {
        title.setText("Page "+(index+1)+" of "+noOfBtns);
        for(int i=0;i<noOfBtns;i++)
        {
            if(i==index)
            {
//                btns[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.box_green));
                btns[index].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.holo_green_light));
            }
            else
            {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
                btns[index].setTextColor(getResources().getColor(android.R.color.holo_green_light));
            }
        }

    }

    /**
     * Method for loading data in listview
     * @param page
     */
    private void loadList(int page, ArrayList<String> data)
    {
        ArrayList<String> sort = new ArrayList<String>();

        int start = page * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<data.size())
            {
                sort.add(data.get(i));
            }
            else
            {
                break;
            }
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                sort);

        listView.setAdapter(adapter);
    }

}
