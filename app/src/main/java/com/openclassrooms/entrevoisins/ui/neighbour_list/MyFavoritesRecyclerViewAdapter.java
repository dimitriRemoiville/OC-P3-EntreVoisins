package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoritesRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoritesRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavorites;

    public MyFavoritesRecyclerViewAdapter(List<Neighbour> items) {
        mFavorites = items;
    }

    @Override
    public MyFavoritesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyFavoritesRecyclerViewAdapter.ViewHolder holder, int position) {
        Neighbour neighbour = mFavorites.get(position);
        holder.mFavoritesName.setText(neighbour.getName());
        Glide.with(holder.mFavoritesAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavoritesAvatar);

        holder.mDeleteFromFavButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteFavoriteEvent(neighbour)));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),DetailsNeighbourActivity.class);
            intent.putExtra("Neighbour", neighbour);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar_fav)
        public ImageView mFavoritesAvatar;
        @BindView(R.id.item_list_name_fav)
        public TextView mFavoritesName;
        @BindView(R.id.item_list_delete_button_fav)
        public ImageButton mDeleteFromFavButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
