package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import adpater.ItemListAdapter;
import entity.OddPhotoEntity;
import other.Common;
import other.Interface;

/**
 * Created by zky62 on 2016-10-12.
 */
//趣图
public class OddPhotosActivity extends Fragment {
    private View m_vFindWorkFragment;
    private PullToRefreshListView pListView;// PullToRefreshListView控件
    private List<HashMap<String, Object>> hashMaps;
    private ItemListAdapter sAdapter;//适配器
    private Interface i;//接口帮助类
    private int PageIndex = 1;
    Toast tError;//错误提示

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        m_vFindWorkFragment = inflater.inflate(R.layout.activity_joke, container, false);

        i = new Interface();//初始化接口帮助类
        tError = Toast.makeText(getActivity(), "兄台,看看网络连接了没？", Toast.LENGTH_SHORT);//提示框

        hashMaps = new ArrayList<HashMap<String, Object>>();//初始化
        getData();//第一次默认加载一次
        pListView = (PullToRefreshListView) m_vFindWorkFragment.findViewById(R.id.lvJokes);

        try {//绑定适配器
            sAdapter = new ItemListAdapter(inflater, hashMaps);
            pListView.setAdapter(sAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Init();
        PageIndex++;

        return m_vFindWorkFragment;
    }

    private void Init() {
        /*
         * 设置PullToRefresh刷新模式
         * BOTH:上拉刷新和下拉刷新都支持
         * DISABLED：禁用上拉下拉刷新
         * PULL_FROM_START:仅支持下拉刷新（默认）
         * PULL_FROM_END：仅支持上拉刷新
         * MANUAL_REFRESH_ONLY：只允许手动触发
         * */
        pListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        // 设置刷新监听
        pListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                //设置刷新标签
                pListView.getLoadingLayoutProxy().setRefreshingLabel("火速备货中...");
                //设置下拉标签
                pListView.getLoadingLayoutProxy().setPullLabel("使劲往下拽");
                //设置释放标签
                pListView.getLoadingLayoutProxy().setReleaseLabel("放手,开始备货");
                //设置上一次刷新的提示标签
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                //加载数据操作
                new MyTask().execute();
            }
        });
    }

    private class MyTask extends AsyncTask<Void, Void, List<HashMap<String, Object>>> {

        @Override
        protected List<HashMap<String, Object>> doInBackground(Void... params) {
            try {
                getData();
                PageIndex++;
                Thread.sleep(1000);//睡眠1秒，延迟加载数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return hashMaps;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, Object>> result) {
            sAdapter.notifyDataSetChanged();//刷新数据
            pListView.onRefreshComplete();//数据加载到适配器完成后，刷新完成，
            super.onPostExecute(result);
        }
    }

    //请求数据
    private void getData() {
        if (Common.isNetworkConnected(getActivity())) {
            try {
                String json = i.getRequest4(PageIndex);
                Gson gson = new Gson(); //操作Josn
                OddPhotoEntity joke = gson.fromJson(json, OddPhotoEntity.class);//得到转换后的数据
                for (OddPhotoEntity.ResultBean.DataBean t : joke.getResult().getData()) {
                    HashMap<String, Object> h = new HashMap<String, Object>();
                    h.put("content", t.getContent());
                    h.put("imgdata", t.getUrl());
                    hashMaps.add(0, h);
                }
            } catch (Exception e) {
                tError.show();
                e.printStackTrace();
            }
        } else {
            tError.show();
        }
    }
}
