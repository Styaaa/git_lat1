package com.ivano.uas_anmp_baru.view

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentScheduleDetailBinding
import com.ivano.uas_anmp_baru.viewmodel.ScheduleViewModel
import com.squareup.picasso.Picasso

class ScheduleDetailFragment : Fragment() {

    private var idSchedule: Int = 0
    private var tgl: String? = null
    private var event: String? = null
    private var waktuMulai: String? = null
    private var waktuSelesai: String? = null
    private var description: String? = null
    private var lokasi: String? = null
    private var game: String? = null
    private var team: String? = null
    private var image_url: String? = null

    private var _binding: FragmentScheduleDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChannel()

        // Menerima argumen dari navController
        arguments?.let {
            val args = ScheduleDetailFragmentArgs.fromBundle(it)
            idSchedule = args.idSchedule
            tgl = args.tgl
            event = args.event
            waktuMulai = args.waktuMulai
            waktuSelesai = args.waktuSelesai
            description = args.description
            lokasi = args.lokasi
            game = args.game
            team = args.team
            image_url = args.imageUrl
        }

        setupUI()

        // Meminta izin notifikasi saat fragment dibuka
        requestNotificationPermission()

        binding.btnTeams.setOnClickListener {
            createNotification("Notification created.")
        }
    }

    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        } else {
            createNotificationChannel()
        }
    }

    private fun createNotificationChannel() {
        val channelId = "${requireContext().packageName}-${requireContext().getString(R.string.app_name)}"
        val channelName = "Notify Me Channel"
        val channelDescription = "Channel for Notify Me notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelDescription
            }
            val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(message: String) {
        val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "${requireContext().packageName}-${requireContext().getString(R.string.app_name)}"

        // Buat notifikasi
        val notification = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(com.ivano.uas_anmp_baru.R.drawable.ic_notification)
            .setContentTitle("Notification")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        // Tampilkan notifikasi
        notificationManager.notify(1001, notification)
    }

    private fun setupUI() {
        // Update tampilan dengan data yang diterima
        binding.eventGame.text = "$event - $game"
        binding.location.text = "$lokasi ( $waktuMulai )"
        binding.teams.text = team
        binding.description.text = description

        Picasso.get()
            .load(image_url)
            .placeholder(android.R.drawable.ic_menu_gallery) // Gambar placeholder built-in
            .error(android.R.drawable.ic_menu_report_image) // Gambar error built-in
            .into(binding.myImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Melepaskan binding saat tampilan hancur
    }
}
