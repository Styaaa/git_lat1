package com.ivano.uas_anmp_baru.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivano.uas_anmp_baru.databinding.ScheduleListItemBinding
import com.ivano.uas_anmp_baru.model.Schedule
import java.text.SimpleDateFormat
import java.util.Locale

class ScheduleListAdapter(val scheduleList:ArrayList<Schedule>):RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>() {
    class ScheduleListViewHolder(val binding: ScheduleListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(schedule: Schedule){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val binding = ScheduleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        holder.binding.EventName.text = scheduleList[position].event
        val date = scheduleList[position].tgl.split("-")
        var hari = date[2]
        val bulan = getNamaBulan(scheduleList[position].tgl)
        holder.binding.Day.text = hari
        holder.binding.Month.text = bulan
        holder.binding.GameTeam.text = scheduleList[position].games_name + " - " + scheduleList[position].teams_name

        holder.itemView.setOnClickListener {
            val action = SchduleFragmentDirections.actionScheduleDetail(
                idSchedule = scheduleList[position].id, // ID schedule
                tgl = scheduleList[position].tgl,         // Tanggal
                event = scheduleList[position].event,     // Nama event
                waktuMulai = scheduleList[position].waktu_mulai, // Waktu mulai
                waktuSelesai = scheduleList[position].waktu_selesai, // Waktu selesai
                description = scheduleList[position].description, // Deskripsi
                lokasi = scheduleList[position].lokasi,   // Lokasi
                game = scheduleList[position].games_name,  // Nama game
                team = scheduleList[position].teams_name,   // Nama tim
                imageUrl = scheduleList[position].image_url
            )
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun updateScheduleList(newData: ArrayList<Schedule>) {
        scheduleList.clear()
        scheduleList.addAll(newData)
        notifyDataSetChanged()
    }
    fun getNamaBulan(tgl: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM", Locale.getDefault()) // "MMMM" untuk nama bulan lengkap
        val date = inputFormat.parse(tgl) // Parsing string tgl ke objek Date
        return outputFormat.format(date) ?: "Invalid" // Format date ke nama bulan
    }
}