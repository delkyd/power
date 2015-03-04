package com.hzgzh.balance.ui;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hzgzh.balance.R;
import com.hzgzh.balance.model.Global;
import com.hzgzh.balance.model.GlobalProvider;
import com.hzgzh.balance.model.Model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/2/9.
 */
public class IntroFragment extends Fragment {
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    SwipeRefreshLayout mSwipe;
    ListView mListView;
    ViewGroup mContainer;
    BaseAdapter mAdapter;
    List<Model> models = new ArrayList<Model>();
    private int limit = 10;
    private int curPage = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        mContainer = (ViewGroup) view.findViewById(R.id.fragment_child_container);
        initListView(view);
        BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
        if (bmobUser != null) {
            View checkin = (View) inflater.inflate(R.layout.fragment_child_checkin, mContainer, true);
            TextView username = (TextView) checkin.findViewById(R.id.checkin_username);
            username.setText(bmobUser.getUsername());
            queryData(0, STATE_REFRESH);
            Button button = (Button) checkin.findViewById(R.id.btn_checkout);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BmobUser.logOut(getActivity());
                    createLogin();
                    models.clear();
                    mAdapter.notifyDataSetChanged();
                }
            });

        } else {
            View loginview = inflater.inflate(R.layout.fragment_child_login, mContainer, true);
            //缓存用户对象为空时， 可打开用户注册界面…
            initButton(loginview);
        }
        return view;
    }

    private void createLogin() {
        mContainer.removeAllViews();
        View login = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_child_login, mContainer, true);
        initButton(login);

    }

    private void createCheckin() {
        mContainer.removeAllViews();
        View checkin = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_child_checkin, mContainer, true);
        TextView username = (TextView) checkin.findViewById(R.id.checkin_username);
        final BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
        username.setText(bmobUser.getUsername());
        Button button = (Button) checkin.findViewById(R.id.btn_checkout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut(getActivity());
                createLogin();
            }
        });
    }

    private void initButton(View view) {
        final Button signup = (Button) view.findViewById(R.id.btn_sign_up);
        Button signin = (Button) view.findViewById(R.id.btn_sign_in);
        final EditText username = (EditText) view.findViewById(R.id.met_username);
        final EditText password = (EditText) view.findViewById(R.id.met_password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                BmobUser user = new BmobUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUp(v.getContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        createCheckin();

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                BmobUser user = new BmobUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.login(v.getContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        createCheckin();
                        queryData(0, STATE_REFRESH);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initListView(View view) {
        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Model model = models.get(position);
                Global g = GlobalProvider.getInstance();
                try {
                    g.num_vp = model.getNumVp();
                    g.num_mass = model.getNumMass();
                    g.model = new JSONObject(model.getData());
                    g.updated = true;
                } catch (Exception e) {
                    showToast("load error");
                }
                Fragment fragment = new MainFragment();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int i = 0;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                mode.setTitle("已选择");

                View view = mListView.getChildAt(position);
                Drawable d = view.getBackground();
                if (checked) {
                    view.setBackground(getResources().getDrawable(R.drawable.abc_list_longpressed_holo));
                    i++;
                    mode.setSubtitle(Integer.toString(i));
                } else {
                    view.setBackground(getResources().getDrawable(R.drawable.abc_list_selector_holo_light));
                    i--;
                    mode.setSubtitle(Integer.toString(i));
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request

                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.context_menu_del:

                        for (int i = 0; i < mListView.getCount(); i++) {

                            if (mListView.isItemChecked(i)) {
                                mListView.setItemChecked(i, false);
                                final int j = i;
                                Model model = models.get(i);

                                model.delete(getActivity(), new DeleteListener() {
                                    @Override
                                    public void onSuccess() {
                                        models.remove(j);
                                        mAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        showToast("delete fail");
                                    }
                                });
                            }
                        }

                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.

            }
        });
 /*       mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity()).setTitle("delete the data?").setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Model model=models.get(position);

                       model.delete(getActivity(),new DeleteListener() {
                           @Override
                           public void onSuccess() {
                               showToast("delete success");
                               models.remove(position);
                               mAdapter.notifyDataSetChanged();
                           }

                           @Override
                           public void onFailure(int i, String s) {
                                showToast("delete fail");
                           }
                       });
                    }
                })
                .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                return true;
            }
        });*/
        mAdapter = new DeviceListAdapter(view.getContext());
        mListView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipe.setRefreshing(true);
                queryData(curPage, STATE_MORE);
            }
        });
    }

    private void queryData(final int page, final int actionType) {
        BmobQuery<Model> query = new BmobQuery<Model>();
        query.setLimit(limit);
        query.setSkip(page * limit);
        query.addWhereEqualTo("name", BmobUser.getCurrentUser(getActivity()).getUsername());
        query.findObjects(getActivity(), new FindListener<Model>() {
            @Override
            public void onSuccess(List<Model> arg0) {
                // TODO Auto-generated method stub

                if (arg0.size() > 0) {
                    if (actionType == STATE_REFRESH) {

                        curPage = 0;
                        models.clear();
                    }
                    for (Model td : arg0) {
                        models.add(td);
                    }
                    curPage++;
                    mAdapter.notifyDataSetChanged();
                    mSwipe.setRefreshing(false);
                } else {
                    mSwipe.setRefreshing(false);
                }
            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                showToast(arg1);
                mSwipe.setRefreshing(false);
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    private class DeviceListAdapter extends BaseAdapter {

        Context context;

        public DeviceListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {

                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.list_name);
                holder.time = (TextView) convertView.findViewById(R.id.list_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Model td = (Model) getItem(position);

            holder.name.setText(td.getDevicename());
            holder.name.setTextColor(getResources().getColor(R.color.black));
            holder.time.setText(td.getCreatedAt());
            holder.time.setTextColor(getResources().getColor(R.color.black));
            return convertView;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return models.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        class ViewHolder {
            TextView name;
            TextView time;
        }

    }
}
