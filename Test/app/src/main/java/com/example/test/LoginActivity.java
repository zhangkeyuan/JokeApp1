package com.example.test;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.content.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import base.DbService;
import greendao.Users;

/**
 * Created by 张珂源 on 2016/10/9.
 */

public class LoginActivity extends Fragment {
    private DbService db;
    private View m_vFindWorkFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        m_vFindWorkFragment = inflater.inflate(R.layout.activity_login, container, false);
        db = DbService.getInstance(getActivity());
        //对View中控件的操作方法
        Button btn = (Button) m_vFindWorkFragment.findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Login();
            }
        });
        return m_vFindWorkFragment;
    }

    private void Login() {
        EditText et = (EditText) m_vFindWorkFragment.findViewById(R.id.account);
        String account = et.getText().toString();
        et = (EditText) m_vFindWorkFragment.findViewById(R.id.pwd);
        String pwd = et.getText().toString();
        if (account.trim().equals("") || pwd.trim().equals("")) {
            Toast tst = Toast.makeText(getActivity(), "账号/密码不能为空！", Toast.LENGTH_SHORT);//提示框
            tst.show();
        } else {
            List<Users> users = db.queryNote("where U_NAME == ? and U_PWD == ?", new String[]{account, pwd});//条件查询
            if (users.size() > 0) {
                Intent intent = new Intent(getActivity(), oneTableActivity.class);//跳转页面
                startActivity(intent);
            } else {
                Toast tst = Toast.makeText(getActivity(), "账号或密码错误！", Toast.LENGTH_SHORT);//提示框
                tst.show();
            }
        }
    }

    //弹出对话框
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
                getActivity().finish();//退出当前窗口
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
