package com.app.recyclerviewandcardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adil khan on 1/31/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NatureModel> objectList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<NatureModel> objectListl) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectListl;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        NatureModel current = objectList.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTitle;
        private ImageView imgThumb, imgDelete, imgCopy;
        private NatureModel currentObject;
        private int position;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
            imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);

        }

        public void setData(NatureModel currentObject, int position) {

            this.tvTitle.setText(currentObject.getTitle());
            this.imgThumb.setImageResource(currentObject.getImgID());
            this.position = position;
            this.currentObject = currentObject;


        }

        public void setListeners() {

            imgDelete.setOnClickListener(MyViewHolder.this);
            imgCopy.setOnClickListener(MyViewHolder.this);
            imgThumb.setOnClickListener(MyViewHolder.this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_copy:
                    addItem(position, currentObject);
                    break;
                case R.id.img_delete:
                    removeItem(position);
                    break;
                case R.id.img_thumb:
                    break;
            }
        }

        private void removeItem(int position) {
            objectList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, objectList.size());
            notifyDataSetChanged();
        }

        private void addItem(int position, NatureModel currentObject) {
            objectList.add(position, currentObject);
            notifyItemInserted(position);
            notifyItemRangeChanged(position, objectList.size());
            notifyDataSetChanged();
        }
    }
}
