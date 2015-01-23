package com.afonseca.myapplication;

/**
 * Created by afonseca on 1/23/2015.
 */
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MsgingActivity extends ListActivity {
    private MsgingDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usernews_activity);

        datasource = new MsgingDataSource(this);
        datasource.open();

        List<Msg> values = datasource.getAllMsgs();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Msg> adapter = new ArrayAdapter<Msg>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Msg> adapter = (ArrayAdapter<Msg>) getListAdapter();
        Msg msg = null;
        switch (view.getId()) {
            case R.id.add:
                String[] msgs = new String[] { "Testing", "Not testing at all", "Just leave it as is" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                msg = datasource.addMsg("from_me","to_you",msgs[nextInt]);
                adapter.add(msg);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    msg = (Msg) getListAdapter().getItem(0);
                    datasource.deleteMsg(msg);
                    adapter.remove(msg);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
