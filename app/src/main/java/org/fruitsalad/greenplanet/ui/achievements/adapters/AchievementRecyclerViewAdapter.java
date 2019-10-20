package org.fruitsalad.greenplanet.ui.achievements.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.fruitsalad.greenplanet.R;

import java.util.List;

public class AchievementRecyclerViewAdapter extends
        RecyclerView.Adapter<AchievementRecyclerViewAdapter.AchievementViewHolder> {

    private List<String> achievements;
    private Context context;

    public AchievementRecyclerViewAdapter(List<String> achievements) {
        this.achievements = achievements;
    }

    public AchievementRecyclerViewAdapter(List<String> achievements, Context context) {
        this.achievements = achievements;
        this.context = context;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_achievements, parent, false);
        return new AchievementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        holder.achievement.setText(achievements.get(position));
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {

        public TextView achievement;

        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);
            achievement = itemView.findViewById(R.id.textView_item_achievement);
        }
    }
}
