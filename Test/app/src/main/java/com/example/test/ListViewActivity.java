package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.*;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by zky62 on 2016-10-11.
 */

public class ListViewActivity extends AppCompatActivity {
    private PullToRefreshListView pListView;// PullToRefreshListView控件对
    private ArrayAdapter adapter;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        pListView = (PullToRefreshListView) findViewById(R.id.plistview);

        ArrayList<String> arrayList = new ArrayList<String>();
        // 初始化适配器
        adapter = new ArrayAdapter<String>(this, R.layout.item_layout, R.id.tv_item_name, arrayList);
        adapter.add("snail");
        adapter.add("_snail");
        adapter.add("__snail");
        adapter.add("___snail");
        // 绑定适配器
        pListView.setAdapter(adapter);

        /*
         * 设置PullToRefresh刷新模式
         * BOTH:上拉刷新和下拉刷新都支持
         * DISABLED：禁用上拉下拉刷新
         * PULL_FROM_START:仅支持下拉刷新（默认）
         * PULL_FROM_END：仅支持上拉刷新
         * MANUAL_REFRESH_ONLY：只允许手动触发
         * */
        pListView.setMode(Mode.PULL_FROM_START);

        // 设置刷新监听
        pListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(ListViewActivity.this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                //设置刷新标签
                pListView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                //设置下拉标签
                pListView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                //设置释放标签
                pListView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                //设置上一次刷新的提示标签
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                //加载数据操作
                new MyTask().execute();
            }
        });
    }

    private class MyTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);//睡眠2秒，延迟加载数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> mArrayList = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
                counter++;
                mArrayList.add("-----" + String.valueOf(counter) + "-------");
            }
            return mArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            for (String string : result) {
                adapter.add(string);
            }
            pListView.onRefreshComplete();//数据加载到适配器完成后，刷新完成，
            super.onPostExecute(result);
        }

    }
}
