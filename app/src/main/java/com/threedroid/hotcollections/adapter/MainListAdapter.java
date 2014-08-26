package com.threedroid.hotcollections.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.ui.imageloader.ImageLoaderView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr on 14-8-26.
 */
public class MainListAdapter extends BaseArrayAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.view_main_list, null);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.image.displayRoundCorner(15);
        holder.image.setImageUrl("http://img5.imgtn.bdimg.com/it/u=398156607,146149448&fm=23&gp=0.jpg");
        holder.text.setText("position:" + position);
        return convertView;
    }

    @Override
    public int getCount() {
        return 20;
    }

    static class ViewHolder {
        @InjectView(R.id.main_list_image)
        ImageLoaderView image;
        @InjectView(R.id.main_list_text)
        TextView text;

        public ViewHolder(View root) {
            ButterKnife.inject(this, root);
        }
    }
}
