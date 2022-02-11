package fi.juliusphung.moodtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.juliusphung.moodtrack.MainActivity5;
import fi.juliusphung.moodtrack.R;
import fi.juliusphung.moodtrack.dataBase.Mood;
import fi.juliusphung.moodtrack.dataBase.MoodDB;

/**
 * @author PhuongTa
 */


public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private List<Mood> mListMood;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param list List[] containing the data to populate views to be used
     *             by RecyclerView.
     */

    public void setData(List<Mood> list) {
        this.mListMood = list;
        notifyDataSetChanged();
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    // Create new views (invoked by the layout manager)
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_mood_item, parent, false);
        return new MoodViewHolder(view);
    }

    /**
     * @param holder
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        Mood mood = mListMood.get(position);
        if (mood == null) {
            return;
        }
        holder.tvDay.setText(mood.getDate().toString());
        holder.tvComment.setText(mood.getComment());
        holder.tvFactor.setText(mood.getFactor());
        int level = mood.getLevel();
        long moodID = mood.getId();

        // add Pic to moodPic, and change color of comment with level of mood
        if (level == 0) {
            holder.imgLevel.setImageResource(R.drawable.sad);
            holder.tvComment.setTextColor(holder.tvComment.getContext().getColor(R.color.sad));

        } else if (level == 1) {
            holder.imgLevel.setImageResource(R.drawable.meh);
            holder.tvComment.setTextColor(holder.tvComment.getContext().getColor(R.color.meh));

        } else if (level == 2) {
            holder.imgLevel.setImageResource(R.drawable.okay);
            holder.tvComment.setTextColor(holder.tvComment.getContext().getColor(R.color.okay));

        } else if (level == 3) {
            holder.imgLevel.setImageResource(R.drawable.happy);
            holder.tvComment.setTextColor(holder.tvComment.getContext().getColor(R.color.happy));

        } else if (level == 4) {
            holder.imgLevel.setImageResource(R.drawable.excited);
            holder.tvComment.setTextColor(holder.tvComment.getContext().getColor(R.color.excited));
        }

        /**
         * When click to to Delete Button
         * Call method method DELETE with MoodID as parameter
         */
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.btnDelete.getContext();
                MoodDB.get(context).moodDao().deleteById(moodID);
                Intent intentData = new Intent(context, MainActivity5.class);
                Log.d("TAG", "onClick: " + moodID);
                context.startActivity(intentData);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mListMood != null) {
            return mListMood.size();
        }
        return 0;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     * Define click listener for the ViewHolder's View
     */
    public class MoodViewHolder extends RecyclerView.ViewHolder {

        private TextView tvComment;
        private TextView tvFactor;
        private TextView tvDay;
        private ImageView imgLevel;
        private ImageButton btnDelete;

        /**
         * Define click listener for the ViewHolder's View
         *
         * @param itemView
         */
        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvComment = itemView.findViewById(R.id.tv_Comment);
            tvFactor = itemView.findViewById(R.id.tv_Factor);
            tvDay = itemView.findViewById(R.id.tv_Day);
            imgLevel = itemView.findViewById(R.id.imageLevel);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
        }
    }
}