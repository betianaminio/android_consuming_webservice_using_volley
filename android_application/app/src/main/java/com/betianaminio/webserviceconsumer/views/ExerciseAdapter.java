package com.betianaminio.webserviceconsumer.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betianaminio.webserviceconsumer.R;
import com.betianaminio.webserviceconsumer.models.Exercise;

import java.util.List;

/**
 * Created by Betiana G. Miño on 26/05/2017.
 */

class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {

    private List<Exercise> mExercisesList;
    private Context        mContext;

    ExerciseAdapter(Context context, List<Exercise> exercises){

        this.mContext = context;
        this.mExercisesList = exercises;

    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item_card_view,parent,false);

        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {

        holder.setName(this.mExercisesList.get(position).getName());
        holder.setDescription(this.mExercisesList.get(position).getDescription());
        holder.setLevel( this.mContext.getString(R.string.card_view_exercise_level) + ": " + this.mExercisesList.get(position).getLevel());
    }

    @Override
    public int getItemCount() {
        return this.mExercisesList.size();
    }
}
