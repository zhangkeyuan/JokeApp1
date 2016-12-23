package base;

/**
 * Created by 张珂源 on 2016/10/10.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.guangda.dao.DaoMaster;
import com.guangda.dao.infoTypeDao;
import com.guangda.dao.infosDao;
/**
 * 封装DaoMaster.OpenHelper方法, 在更新的时候,用来保存原来的数据
 * greenDao默认在更新的时候,会新建表,原来的数据就丢失了
 * Created by cg on 2015/12/28.
 */
public class THDevOpenHelper extends DaoMaster.OpenHelper {

    public THDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 2:
                //创建新表，注意createTable()是静态方法
                infosDao.createTable(db, true);
                infoTypeDao.createTable(db,true);

                // 加入新字段
                // db.execSQL("ALTER TABLE 'moments' ADD 'audio_path' TEXT;");

                // TODO
                break;
        }
    }
}
