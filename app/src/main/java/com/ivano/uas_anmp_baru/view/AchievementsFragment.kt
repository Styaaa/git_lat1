package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentAchievementsBinding
import com.ivano.uas_anmp_baru.model.Game
import com.ivano.uas_anmp_baru.viewmodel.AchievementViewModel
import com.ivano.uas_anmp_baru.viewmodel.GameViewModel
import com.squareup.picasso.Picasso

class AchievementsFragment : Fragment() {
    private lateinit var binding: FragmentAchievementsBinding
    private lateinit var viewModelAchievement: AchievementViewModel
    private lateinit var viewModelGame: GameViewModel
    private val achievementListAdapter = AchievementListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = binding.yearSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout.
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.years_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears.
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner.
                spinner.adapter = adapter
            }
        }

        viewModelAchievement = ViewModelProvider(this).get(AchievementViewModel::class.java)
        viewModelGame = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.recViewAchievements.layoutManager = LinearLayoutManager(context)
        binding.recViewAchievements.adapter = achievementListAdapter

        if(arguments != null) {
            val gameId =
                AchievementsFragmentArgs.fromBundle(requireArguments()).gameId

            viewModelGame.fetchGamesById(gameId)
            viewModelAchievement.fetchAchievement(gameId)

            observeGame()
            observeAchievement()
        }
    }

    fun observeGame(){
        viewModelGame.gamesData.observe(viewLifecycleOwner, Observer {
            binding.txtName.setText(viewModelGame.gamesData.value?.name)

            val url = viewModelGame.gamesData.value?.image_url
            val builder = Picasso.Builder(binding.img.context)
            builder.listener{ picasso, uri, exception ->
                exception.printStackTrace()
            }
            Picasso.get().load(url).into(binding.img)
        })
    }

    fun observeAchievement(){
        viewModelAchievement.achievementLD.observe(viewLifecycleOwner, Observer {
            achievementListAdapter.updateAchievementList(it)
        })
    }
}