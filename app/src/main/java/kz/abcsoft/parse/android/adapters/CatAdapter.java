package kz.abcsoft.parse.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kz.abcsoft.parse.android.DetailActivity;
import kz.abcsoft.parse.android.R;
import kz.abcsoft.parse.android.models.Cat;

public class CatAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Cat> mCatList = null;

    public CatAdapter(Context context, List<Cat> catList){
        mContext = context ;
        mCatList = catList ;
        mInflater = LayoutInflater.from(context) ;
    }

    public class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        TextView colorTextView;
    }

    @Override
    public int getCount() {
        return mCatList.size();
    }

    @Override
    public Cat getItem(int position) {
        return mCatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listview_item, parent, false);

            holder.nameTextView = (TextView) view.findViewById(R.id.textViewName);
            holder.ageTextView = (TextView) view.findViewById(R.id.textViewAge);
            holder.colorTextView = (TextView) view.findViewById(R.id.textViewColor);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Выводим результаты
        holder.nameTextView.setText(" " + mCatList.get(position).getName());
        holder.ageTextView.setText(" " + mCatList.get(position).getAge());
        holder.colorTextView.setText("  "+ mCatList.get(position).getColor());

        // Щелчок
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // запускаем активность DetailActivity
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("name",
                        (mCatList.get(position).getName()));
                intent.putExtra("age",
                        (mCatList.get(position).getAge()));
                intent.putExtra("color",
                        (mCatList.get(position).getColor()));
                mContext.startActivity(intent);
            }
        });

        return view;
    }

}
