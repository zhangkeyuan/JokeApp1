package adpater;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.PicInfoActivity;
import com.example.test.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import java.util.HashMap;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 张珂源 on 2016/10/14.
 */

public class ItemListAdapter extends BaseAdapter {

    private List<HashMap<String, Object>> hashMaps;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private LayoutInflater nflater;

    public ItemListAdapter(LayoutInflater nflater, List<HashMap<String, Object>> hashMaps) {
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        this.options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.jzz) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.noimg) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 构建完成

        this.hashMaps = hashMaps;
        this.imageLoader = ImageLoader.getInstance();
        this.nflater = nflater;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return hashMaps.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return hashMaps.get(position).get("imgdata"); //imageUrls[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = nflater.inflate(R.layout.activity_oddphotos_item, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.txtcontent);
            holder.image = (GifImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(hashMaps.get(position).get("content").toString());
        imageLoader.displayImage(hashMaps.get(position).get("imgdata").toString(), holder.image, options);
        holder.image.setTag(position);//添加索引（点击事件时用来获取）
        holder.image.setOnClickListener(new View.OnClickListener() {//注册点击事件
            @Override
            public void onClick(View v) {//点击事件
                try {
                    final int index = (Integer) v.getTag();//获取当前点击索引
                    //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
                    Intent intent = new Intent(nflater.getContext(),PicInfoActivity.class);
                    //用Bundle携带数据
                    Bundle bundle = new Bundle();
                    //传递name参数为tinyphp
                    bundle.putString("url", hashMaps.get(index).get("imgdata").toString());
                    intent.putExtras(bundle);
                    nflater.getContext().startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}

class ViewHolder {
    public GifImageView image;
    public TextView text;
}
