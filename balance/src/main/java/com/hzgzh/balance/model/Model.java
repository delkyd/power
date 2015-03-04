package com.hzgzh.balance.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.hzgzh.balance.R;

import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2015/2/16.
 */
public class Model extends BmobObject {
    public JSONObject data;
    private String devicename;
    private int numMass;
    private int numVp;
    private String name;

    public static void saveModel(final Context context) {
        Global g = GlobalProvider.getInstance();
        Model model = new Model();
        try {
            model.setName(BmobUser.getCurrentUser(context).getUsername());
            model.setDevicename(g.model.get("name").toString() + "_" + g.model.get("model").toString());
            model.setData(g.model.toString());
            model.setNumVp(g.num_vp);
            model.setNumMass(g.num_mass);
            model.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "save success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {

        }
    }

    public static void updateModel(final Context context, Model model) {
        try {
            Global g = GlobalProvider.getInstance();
            model.setName(BmobUser.getCurrentUser(context).getUsername());
            model.setDevicename(g.model.get("name").toString() + "_" + g.model.get("model").toString());
            model.setData(g.model.toString());
            model.setNumVp(g.num_vp);
            model.setNumMass(g.num_mass);
            model.update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "input error", Toast.LENGTH_SHORT).show();
        }
    }

    public static void upload(final Context context, View view) {
        Global g = GlobalProvider.getInstance();
        g.setModel(view);

        BmobUser user = BmobUser.getCurrentUser(context);
        if (user == null) {
            Toast.makeText(context, "should login in", Toast.LENGTH_SHORT).show();
            return;
        }


        BmobQuery<Model> query = new BmobQuery<>("Model");
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        String name = null;
        String username = user.getUsername();
        try {
            if (g.model.get("name").toString().isEmpty() || g.model.get("model").toString().isEmpty())
                throw new Exception();
            name = g.model.get("name").toString() + "_" + g.model.get("model").toString();
        } catch (Exception e) {
            Toast.makeText(view.getContext(), R.string.error_input_name_model, Toast.LENGTH_SHORT).show();
            return;
        }

        query.addWhereEqualTo("name", username);
        query.addWhereEqualTo("devicename", name);

        query.findObjects(context, new FindListener<Model>() {
            @Override
            public void onSuccess(final List<Model> models) {
                if (models.size() < 1) {
                    saveModel(context);
                    Toast.makeText(context, "save success", Toast.LENGTH_SHORT).show();
                }
                if (models.size() == 1) {
                    new AlertDialog.Builder(context).setTitle("delete the data?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    updateModel(context, models.get(0));
                                    Toast.makeText(context, "update succes", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public int getNumMass() {
        return numMass;
    }

    public void setNumMass(int numMass) {
        this.numMass = numMass;
    }

    public int getNumVp() {
        return numVp;
    }

    public void setNumVp(int numVp) {
        this.numVp = numVp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data.toString();
    }

    public void setData(String data) {
        try {
            this.data = new JSONObject(data);
        } catch (Exception e) {

        }
    }
}
