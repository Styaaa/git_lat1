<?php
include("koneksi.php");

error_reporting(E_ERROR | E_PARSE);

// Mendapatkan parameter game_id dan year dari POST request
$game_id = $_POST['game_id'];
$year = isset($_POST['year']) ? $_POST['year'] : 0;

$data = array();

if ($game_id == 0) {
    echo json_encode(array('result' => 'ERROR', 'message' => 'Game ID is required'));
    die();
}

// SQL untuk mengambil achievements berdasarkan game_id
$sql = "SELECT achievements.*, teams.name FROM achievements
        JOIN teams ON achievements.team_id = teams.id
        WHERE achievements.game_id = $game_id";

// Menambahkan kondisi tahun jika year != 0
if ($year != 0) {
    $sql .= " AND achievements.year = $year";
}

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($obj = $result->fetch_assoc()) {
        $data[] = $obj;
    }
    echo json_encode($data);
} else {
    echo json_encode(array('result' => 'ERROR', 'message' => 'No achievements found'));
}

$conn->close();
