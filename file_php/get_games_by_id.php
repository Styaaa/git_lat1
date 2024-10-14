<?php
include("koneksi.php");

error_reporting(E_ERROR | E_PARSE);

// Mendapatkan id game dari request POST/GET
$game_id = $_POST['id'];

if ($game_id == 0) {
    echo json_encode(array('result' => 'ERROR', 'message' => 'Game ID is required'));
    die();
}

// SQL query untuk mendapatkan game berdasarkan id
$sql = "SELECT * FROM games WHERE id = ?";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $game_id);  // Mengikat parameter game_id sebagai integer

$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    // Only fetch one result since game_id is unique
    $game = $result->fetch_object();
    echo json_encode($game); // Return as a single object
} else {
    echo json_encode(array('result' => 'ERROR', 'message' => 'No game found with the given ID'));
}

$stmt->close();
$conn->close();
