-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 11, 2024 at 09:17 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uts_anmp`
--

-- --------------------------------------------------------

--
-- Table structure for table `achievements`
--

CREATE TABLE `achievements` (
  `id` int NOT NULL,
  `game_id` int NOT NULL,
  `year` year NOT NULL,
  `achievement` varchar(255) NOT NULL,
  `team_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `achievements`
--

INSERT INTO `achievements` (`id`, `game_id`, `year`, `achievement`, `team_id`) VALUES
(1, 1, '2024', 'Champions of the Global Valorant Championship', 1),
(2, 2, '2023', 'Champions of the Mobile Legends World Series', 1),
(3, 3, '2023', 'MVP of the Summer Split', 1),
(4, 4, '2024', 'Champions of the Fortnite World Cup', 1),
(5, 5, '2023', 'Flawless Victory at the Dota 2 Majors', 1),
(6, 1, '2023', 'Undefeated in the Summer Valorant Tournament', 2),
(7, 2, '2024', 'Top 3 Finish at the MLBB International Invitational', 2),
(8, 3, '2024', 'Top 4 Finish in the LoL Global Championship', 2),
(9, 4, '2024', 'Top 4 Finish at the Winter Fortnite Clash', 2),
(10, 5, '2023', 'Champions of The International', 2),
(11, 1, '2023', 'MVP of the Valorant World Cup', 3),
(12, 2, '2024', 'Best Support Player Award', 3),
(13, 3, '2023', 'Champions of the LoL World Championship', 3),
(14, 4, '2023', 'Best Battle Royale Team', 3),
(15, 5, '2022', 'MVP of the Spring Dota 2 Cup', 3),
(16, 1, '2024', 'Best Strategic Team Award', 4),
(17, 2, '2022', 'Most Dominant Performance in the MLBB Continental Cup', 4),
(18, 3, '2024', 'Best ADC of the Season', 4),
(19, 4, '2023', 'First Place in the Fortnite Summer Series', 4),
(20, 5, '2024', 'Top 3 Finish in the Dota 2 Global Championship', 4),
(21, 1, '2022', 'Flawless Run at the Winter Valorant Invitational', 5),
(22, 2, '2023', 'MVP of the Mobile Legends National Championship', 5),
(23, 3, '2022', 'Flawless Victory at the Mid-Season Invitational', 5),
(24, 4, '2022', 'MVP of the Fortnite Invitational', 5),
(25, 5, '2024', 'Best Carry Player of the Year', 5);

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`id`, `name`, `image_url`, `description`) VALUES
(1, 'Valorant', 'https://cdn1.epicgames.com/offer/cbd5b3d310a54b12bf3fe8c41994174f/EGS_VALORANT_RiotGames_S1_2560x1440-62a5c694f32fd4273a02a86b7f74ef8a', 'Valorant is a tactical first-person shooter from Riot Games that combines precise gunplay with unique character abilities. Players engage in strategic, team-based matches where they must complete objectives and outsmart opponents to win.'),
(2, 'Mobile Legends', 'https://cdn.oneesports.id/cdn-data/wp-content/uploads/sites/2/2020/08/MobileLegendsBangBang_MLBB_appstore_officialart.jpg', 'Mobile Legends: Bang Bang is a popular multiplayer online battle arena (MOBA) game. Players team up and use different heroes in fast-paced battles to destroy the enemy base.'),
(3, 'League of Legends', 'https://cdn1.epicgames.com/offer/24b9b5e323bc40eea252a10cdd3b2f10/EGS_LeagueofLegends_RiotGames_S1_2560x1440-80471666c140f790f28dff68d72c384b', 'League of Legends is a MOBA game where teams of champions battle to control the map and destroy the enemy Nexus. It features various roles and strategies in a competitive environment.'),
(4, 'Fortnite', 'https://cdn2.unrealengine.com/social-image-chapter4-s3-3840x2160-d35912cc25ad.jpg', 'Fortnite is a battle royale game where players compete to be the last person or team standing on an island. It combines shooting, building, and unique events in a vibrant, interactive world.'),
(5, 'Dota 2', 'https://business-portal-bucket.s3.amazonaws.com/media/images/41e172c318357d632f53b92d8cb38661_large_cover.original.jpg', 'Dota 2 is a highly competitive MOBA game where players control powerful heroes in battles to defend their ancient while destroying the enemy’s. It is known for its complex strategies and mechanics.');

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`id`, `name`) VALUES
(1, 'Team Phoenix'),
(2, 'Team Eclipse'),
(3, 'Team Hydra'),
(4, 'Team Vortex'),
(5, 'Team Seraph');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `achievements`
--
ALTER TABLE `achievements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_game_id_achievements` (`game_id`),
  ADD KEY `fk_team_id_achievements` (`team_id`);

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `achievements`
--
ALTER TABLE `achievements`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `achievements`
--
ALTER TABLE `achievements`
  ADD CONSTRAINT `fk_game_id_achievements` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_team_id_achievements` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
