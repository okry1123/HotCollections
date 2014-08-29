package com.threedroid.hotcollections.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.activity.TypeGameListActivity;
import com.threedroid.hotcollections.adapter.TypeGridAdapter;
import com.threedroid.hotcollections.bean.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014-8-29.
 */
public class TypeGridFragment extends Fragment{

    private GridView mGridView;
    private List<GridItem> mDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_type_grid, null);
        mGridView = (GridView) root.findViewById(R.id.gridView);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String host = "http://img.pipaw.net/WY/typeLogo/2014/08/25/";
        mDataList = new ArrayList<GridItem>();
        mDataList.add(new GridItem("角色扮演", host + "5e898f46c1904180944bbb1cdb6f469e.png", GameData.GAME_TYPE_JUESE));
        mDataList.add(new GridItem("动作游戏", host + "9a4f59edffff4d2995238babb1f5b110.png", GameData.GAME_TYPE_DONGZUO));
        mDataList.add(new GridItem("飞行射击", host + "5e74939ece3a4eb0b60839437963bb67.png", GameData.GAME_TYPE_FEIXING));
        mDataList.add(new GridItem("休闲益智", host + "44b638498ed34ee396534fadfdcade38.png", GameData.GAME_TYPE_XIUXIAN));
        mDataList.add(new GridItem("体育竞技", host + "35f423e73dc440558b8af54f34443ba1.png", GameData.GAME_TYPE_TIYU));
        mDataList.add(new GridItem("赛车竞速", host + "628345f36da443709987a69c092ed787.png", GameData.GAME_TYPE_SAICHE));
        mDataList.add(new GridItem("棋牌娱乐", host + "e31dab87874d4a9aa0ef3ce6bfb2cf89.jpg", GameData.GAME_TYPE_QIPAI));
        mDataList.add(new GridItem("冒险解谜", host + "1517d0dd5bae4e65b93e366e47baaa79.png", GameData.GAME_TYPE_MAOXIAN));
        mGridView.setAdapter(new TypeGridAdapter(mDataList));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TypeGameListActivity.launch(getActivity(), ((TypeGridAdapter)adapterView.getAdapter()).getItem(i).type);
            }
        });
    }

    public class GridItem{
        public GridItem(String text, String icon, int type){
            this.text = text;
            this.icon = icon;
            this.type = type;
        }
        public String icon;
        public int type;
        public String text;
    }

}
