SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `dbviewapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `databaseinfo`
--

CREATE TABLE `databaseinfo` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `hostname` varchar(30) NOT NULL,
  `port` int(11) NOT NULL,
  `databaseName` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `databaseinfo`
--

INSERT INTO `databaseinfo` (`id`, `name`, `hostname`, `port`, `databaseName`, `username`, `password`) VALUES
(1, 'db1', 'dbhost1', 3306, 'test_db1', 'root', 'root'),
(2, 'db2', 'localhost', 8889, 'dbviewapp', 'root', 'root'),
(3, 'db3', 'localhost', 8889, 'gamingplatform', 'root', 'root'),
(4, 'db4', 'localhost', 8889, 'carecenter', 'root', 'root'),
(5, 'db5', 'localhost', 8889, 'transfermarket', 'root', 'root'),
(6, 'db6', 'mysql-rfam-public.ebi.ac.uk', 4497, 'Rfam', 'rfamro', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `databaseinfo`
--
ALTER TABLE `databaseinfo2`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_unique` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `databaseinfo`
--
ALTER TABLE `databaseinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;