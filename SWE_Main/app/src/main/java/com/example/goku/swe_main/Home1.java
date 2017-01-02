package com.example.goku.swe_main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home1 extends Fragment {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    ListView listView;
    String itemlist[] = {"Profile","Change Contacts","Change Messages","Change Password", "Feedback","Log out"};
    public Home1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home1, container, false);

        //   ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,str);
        listView = (ListView)view.findViewById(R.id.list_view);
        MyAdapter myadapter = new MyAdapter();
        listView.setAdapter(myadapter);
        return view;
    }

    public void setup(DrawerLayout drawerLayout , final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                toolbar.setTitle(getActivity().getTitle().toString());
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset < 0.65){
                    toolbar.setAlpha(1 - slideOffset);
                    toolbar.setTitle("MENU");
                    toolbar.setTitleTextColor(Color.BLACK);
                    //toolbar.setBackgroundColor(Color.BLUE);
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return itemlist.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater =getActivity().getLayoutInflater();
            View view;
            view = inflater.inflate(R.layout.listt_text_view,parent,false);
            TextView txt = (TextView)view.findViewById(R.id.list_textview);
            txt.setText(itemlist[position].toString());
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position)
                    {
                        case 0:
                            myCall(Profile.class);
                            break;

                        case 1:
                            myCall(Add_contacts.class);
                            break;
                        case 2:
                            myCall(Add_message.class);
                            break;
                        case 3:
                            myCall(ChangePassword.class);
                            break;
                        case 5:
                            myCall(Logout.class);
                            break;
                        case 4:
                            myCall(Feedback.class);
                            break;


                        default:
                            myCall(Home.class);
                            break;
                    }
                }
            });

            return view;
        }
        public void myCall(Class Mclass){
            startActivity(new Intent(getActivity(),Mclass));
        }
    }

}