package org.fruitsalad.greenplanet.ui.achievements.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.fruitsalad.R;
import org.fruitsalad.model.SaviourOfEarth;

import java.util.List;

public class LeaderBoardRecyclerViewAdapter
        extends RecyclerView.Adapter<LeaderBoardRecyclerViewAdapter.LeaderBoardViewHolder> {

    private List<SaviourOfEarth> users;

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leaderboard, parent, false);

        return new LeaderBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder holder, int position) {
        holder.saviourOfEarthName.setText(users.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderBoardViewHolder extends RecyclerView.ViewHolder {

        public TextView saviourOfEarthName;

        public LeaderBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            saviourOfEarthName = itemView.findViewById(R.id.textView_item_leaderboard);
        }
    }

}
