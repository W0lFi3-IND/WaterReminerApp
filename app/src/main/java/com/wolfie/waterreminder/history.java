package com.wolfie.waterreminder;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * A simple {@link Fragment} subclass.
 */
public class history extends Fragment {
TextView tv2,tv3,tv4;
allrecord a;
SQLiteDatabase sqldb;
    GraphView graph;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    public history() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_history, container, false);
      final  allrecord ar = new allrecord(this.getActivity());
      tv2=view.findViewById(R.id.a);
      graph = (GraphView) view.findViewById(R.id.graph);
        a = new allrecord(this.getActivity());
        sqldb = a.getWritableDatabase();

        exqinsert();
        tv3=view.findViewById(R.id.ma);
        tv4=view.findViewById(R.id.min);
        Thread timer = new Thread() {
            @Override
            public void run() {
//do something
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv3.setText(ar.getDatamax());
                        tv4.setText(ar.getDatamin());
                        tv2.setText(ar.getDataavg());
                    }
                });
            }
        };
        timer.start();


        return view;
    }

    private void exqinsert() {
       series = new LineGraphSeries<DataPoint>(getData());
       graph.addSeries(series);
    }

    private DataPoint[] getData() {
String [] columns ={"sno","qty"};
        Cursor cursor = sqldb.query("allrecord",columns,null,null,null,null,null);
        DataPoint [] dp = new DataPoint[cursor.getCount()];

        for (int i =0 ; i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));

        }
        return  dp;
    }


}
