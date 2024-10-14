package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.AchievementListItemBinding
import com.ivano.uas_anmp_baru.model.Achievement

class AchievementListAdapter(val achievementList: ArrayList<Achievement>): RecyclerView.Adapter<AchievementListAdapter.AchievementListViewHolder>() {
    class AchievementListViewHolder(var binding: AchievementListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(achievement: Achievement){
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementListAdapter.AchievementListViewHolder {
        val binding = AchievementListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AchievementListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AchievementListAdapter.AchievementListViewHolder,
        position: Int
    ) {
        holder.binding.txtAchievementItem.text = (position + 1).toString() + ". " + achievementList[position].achievement + " (" + achievementList[position].year + ") - " + achievementList[position].name
    }

    override fun getItemCount(): Int {
        return achievementList.size
    }

    fun updateAchievementList(newAchievementList: ArrayList<Achievement>) {
        achievementList.clear()
        achievementList.addAll(newAchievementList)
        notifyDataSetChanged()
    }

}