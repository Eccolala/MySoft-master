package me.chunyu.spike.wcl_circle_reveal_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;

/**
 * RecyclerView Adapter
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<BookInfo> list;
    private LayoutInflater inflater;


    public BookAdapter(Context context, List<BookInfo> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.author.setText(list.get(position).getAuthor());
        holder.price.setText(list.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView author;
        TextView price;


        public MyViewHolder(View parent) {
            super(parent);
            name = (TextView) parent.findViewById(R.id.item_name);
            author = (TextView) parent.findViewById(R.id.item_author);
            price = (TextView) parent.findViewById(R.id.item_price);
        }
    }

}