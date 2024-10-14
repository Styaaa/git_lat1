package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.HomeCardItemBinding
import com.ivano.uas_anmp_baru.model.Game
import com.squareup.picasso.Picasso

class HomeListAdapter(val gameList:ArrayList<Game>):
    RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {
    class HomeListViewHolder(var binding: HomeCardItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(game: Game){
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val binding = HomeCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.binding.judulCard.text = gameList[position].name
        holder.binding.subtitleCard.text = gameList[position].description

        val url = gameList[position].image_url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{ picasso, uri, exception ->
            exception.printStackTrace()
        }
        Picasso.get().load(url).into(holder.binding.myImageView)

        holder.binding.btnAchievement.setOnClickListener {
            val action = HomeFragmentDirections.actionDetailHome(gameList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateGameList(newGameList: ArrayList<Game>) {
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

}