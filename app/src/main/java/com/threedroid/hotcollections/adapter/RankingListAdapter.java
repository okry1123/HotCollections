package com.threedroid.hotcollections.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.bean.GameData;
import com.threedroid.hotcollections.ui.imageloader.ImageLoaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr on 14-8-26.
 */
public class RankingListAdapter extends BaseArrayAdapter {

    private List<GameData> mDataList;
    public RankingListAdapter(List<GameData> list){
        if(list != null)
            mDataList = list;
        else
            mDataList = new ArrayList<GameData>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.view_main_list, null);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.image.displayRoundCorner(15);
        holder.image.setImageUrl(getItem(position).game_logo);
        holder.text.setText(getItem(position).game_name);
        holder.click.setText("热度" + getItem(position).game_downclick);
        return convertView;
    }

    @Override
    public GameData getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    static class ViewHolder {
        @InjectView(R.id.main_list_image)
        ImageLoaderView image;
        @InjectView(R.id.main_list_text)
        TextView text;
        @InjectView(R.id.main_list_click)
        TextView click;

        public ViewHolder(View root) {
            ButterKnife.inject(this, root);
        }
    }
}
