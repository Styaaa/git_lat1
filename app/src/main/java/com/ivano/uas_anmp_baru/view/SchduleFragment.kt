package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentHomeBinding
import com.ivano.uas_anmp_baru.databinding.FragmentSchduleBinding
import com.ivano.uas_anmp_baru.viewmodel.GameViewModel
import com.ivano.uas_anmp_baru.viewmodel.ScheduleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SchduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchduleFragment : Fragment() {
    private lateinit var viewModel: ScheduleViewModel
    private val schedulelistAdapter  = ScheduleListAdapter(arrayListOf())
    private lateinit var binding: FragmentSchduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSchduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.fetchSchedule()

        binding.txtError.visibility = View.GONE

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = schedulelistAdapter

        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.scheduleLD.observe(viewLifecycleOwner, Observer {
            schedulelistAdapter.updateScheduleList(it)
        })
        viewModel.scheduleLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }

}