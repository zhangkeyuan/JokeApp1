package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(3, "greendao");
        schema.setDefaultJavaPackageDao("com.guangda.dao");

        Entity userBean = schema.addEntity("Users");
        userBean.setTableName("Users");
        userBean.addIdProperty();
        userBean.addStringProperty("uSex");
        userBean.addStringProperty("uTelphone");
        userBean.addStringProperty("uAge");
        userBean.addStringProperty("uName");
        userBean.addStringProperty("uPwd");

        Entity infoTypeBean = schema.addEntity("infoType");
        infoTypeBean.implementsSerializable();
        infoTypeBean.addIdProperty();
        infoTypeBean.addStringProperty("infoName");

        Entity infoBean = schema.addEntity("infos");
        infoBean.implementsSerializable();
        infoBean.addIdProperty();
        infoBean.addStringProperty("infoTitle");
        infoBean.addStringProperty("infoAuthor");
        infoBean.addStringProperty("infoContent");
        Property typeId = infoBean.addLongProperty("typeId").getProperty();

        infoBean.addToOne(infoTypeBean, typeId);
        ToMany addToMany = infoTypeBean.addToMany(infoBean,typeId);
        addToMany.setName("infoes");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
