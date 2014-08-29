package com.threedroid.hotcollections.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.bean.GameData;
import com.threedroid.hotcollections.fragment.TypeGridFragment;
import com.threedroid.hotcollections.ui.imageloader.ImageLoaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr on 14-8-26.
 */
public class TypeGridAdapter extends BaseArrayAdapter {

    private List<TypeGridFragment.GridItem> mDataList;
    public TypeGridAdapter(List<TypeGridFragment.GridItem> list){
        if(list != null)
            mDataList = list;
        else
            mDataList = new ArrayList<TypeGridFragment.GridItem>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.view_main_grid, null);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.image.displayRoundCorner(15);
        holder.image.setImageUrl(getItem(position).icon);
        holder.text.setText(getItem(position).text);
        return convertView;
    }

    @Override
    public TypeGridFragment.GridItem getItem(int position) {
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

        public ViewHolder(View root) {
            ButterKnife.inject(this, root);
        }
    }
}
