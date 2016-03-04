package br.com.spiderman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Caramelo on 03/03/2016.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(BaseAdapter<?> parent, View view, int position);
    }

    protected Context context;
    protected ArrayList<T> dataSet;
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context) {
        this.context = context;
        dataSet = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ArrayList<T> getItens() {
        return dataSet;
    }

    public void addItens(ArrayList<T> itens) {
        dataSet.addAll(itens);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return dataSet.get(position);
    }

    public void addItem(T item) {
        dataSet.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        dataSet.remove(item);
        notifyDataSetChanged();
    }

    public void clearItens() {
        dataSet.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
