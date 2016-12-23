package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.droidsonroids.gif.GifImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zky62 on 2016-10-16.
 */

public class PicInfoActivity extends AppCompatActivity {
    @InjectView(R.id.iv)
    GifImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picinfo);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String url = bundle.getString("url");
        String prefix= url.substring(url.lastIndexOf("."));//获取后缀名
        ButterKnife.inject(this);
        PhotoViewAttacher attacher = new PhotoViewAttacher(iv);
        switch (prefix.toLowerCase()){
            case ".gif":
                Glide.with(PicInfoActivity.this).load(url).asGif().placeholder(R.drawable.res).dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                break;
            default: {
                //不支持GIF
                Picasso.with(this).load(url).placeholder(R.drawable.res).into(iv);
                break;
            }
        }
    }
}
