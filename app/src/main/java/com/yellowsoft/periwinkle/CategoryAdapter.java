package com.yellowsoft.periwinkle;

/**
 * Created by subhankar on 11/6/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private Context mContext;
    private List<Category> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public View root;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            root = (View) view.findViewById(R.id.root);
        }
    }


    public CategoryAdapter(Context mContext, List<Category> albumList) {
        this.mContext = mContext;
        this.categoryList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.title.setText(category.getName());
        holder.count.setText(category.getNumOfItems() + " tasks");

        // loading album cover using Glide library
        Picasso.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
            }
        });

        holder.root.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyViewHolder holder = (MyViewHolder) view.getTag();
                Intent intent = new Intent(mContext,
                        CategoryDetailActivity.class);
                intent.putExtra("thumbnail", category.getThumbnail());
                intent.putExtra("categoryid", category.getId());
                intent.putExtra("title", category.getName());
                mContext.startActivity(intent);
//            finish();
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
//    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
