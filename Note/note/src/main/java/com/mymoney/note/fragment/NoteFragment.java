package com.mymoney.note.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mymoney.note.R;

import java.util.ArrayList;

public class NoteFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String ARG_RESOURCE_ICON_TAB = "arg_resource_icon_tab";
    private OnFragmentInteractionListener mListener;
    private int loadType = 0;
    static int  resIcon;

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();


    public static NoteFragment newInstance(int res) {
        NoteFragment fragment = new NoteFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_RESOURCE_ICON_TAB, resIcon);
//        fragment.setArguments(args);
        resIcon = res;
        return fragment;
    }

    public NoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        resIcon = getArguments().getInt(ARG_RESOURCE_ICON_TAB);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_money, container, false);
        //init swipe refresh
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                getData()));

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        return view;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public int getLoadType() {
        return loadType;
    }

    public void setLoadType(int loadType) {
        this.loadType = loadType;
    }
    public int getResIcon() {
        return resIcon;
    }

    private ArrayList<String> getData() {
        list.add("Hello");
        list.add("This is stormzhang");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("My GitHub: stormzhang");
        list.add("weibo: googdev");
        return list;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
                                 @Override
                                 public void run() {
                                     mSwipeLayout.setRefreshing(false);
                                 }
                             },3000);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction();
    }

}