package dialogfragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by 张珂源 on 2016/10/10.
 */
@SuppressLint("ValidFragment")
public class oneTableItemDialogFragment extends DialogFragment {
    private long id;                                  //用户id
    private int postion;                              //list中的编号
    private TextView txt_onetable_update;
    private TextView txt_onetable_delete;

    public interface EditUserOnClickListener {
        //flag标识,0表示删除,1表示修改
        void onEditUserOnClick(long id, int postion, int flag);
    }
    public oneTableItemDialogFragment(long id, int postion) {
        this.id = id;
        this.postion = postion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_onetable_itemdialog, container);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_onetable_update = (TextView) view.findViewById(R.id.txt_onetable_update);
        txt_onetable_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserOnClickListener listener = (EditUserOnClickListener) getActivity();
                listener.onEditUserOnClick(id, postion, 1);
            }
        });
        txt_onetable_delete = (TextView) view.findViewById(R.id.txt_onetable_delete);
        txt_onetable_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserOnClickListener listener = (EditUserOnClickListener) getActivity();
                listener.onEditUserOnClick(id, postion, 0);
            }
        });
    }
}
