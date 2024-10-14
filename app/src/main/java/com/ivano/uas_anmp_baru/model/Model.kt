package com.ivano.uas_anmp_baru.model

data class Game(
    var id:String,
    var name:String?,
    var description:String?,
    var image_url:String?
)

data class Achievement(
    var id:Int,
    var game_id:Int,
    var year: String?,
    var achievement: String?,
    var team_id: Int?,
    var name: String?
)

data class Schedule(
    var id:Int,
    var tgl: String,
    var event: String,
    var waktu_mulai: String,
    var waktu_selesai: String,
    var description: String,
    var lokasi: String,
    var teams_id: Int?,
    var games_id: Int?,
    var teams_name: String,
    var games_name: String,
    var image_url: String
)