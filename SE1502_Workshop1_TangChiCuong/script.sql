USE [master]
GO
/****** Object:  Database [SE1502_Workshop1_TangChiCuong]    Script Date: 24 Feb 2021 12:32:54 ******/
CREATE DATABASE [SE1502_Workshop1_TangChiCuong]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SE1502_Workshop1_TangChiCuong', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE1502_Workshop1_TangChiCuong.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SE1502_Workshop1_TangChiCuong_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE1502_Workshop1_TangChiCuong_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE1502_Workshop1_TangChiCuong].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET  MULTI_USER 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET QUERY_STORE = OFF
GO
USE [SE1502_Workshop1_TangChiCuong]
GO
/****** Object:  User [chicuong]    Script Date: 24 Feb 2021 12:32:54 ******/
CREATE USER [chicuong] FOR LOGIN [chicuong] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 24 Feb 2021 12:32:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](20) NOT NULL,
	[password] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 24 Feb 2021 12:32:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[BookID] [char](10) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Author] [nvarchar](50) NULL,
	[PublishYear] [int] NULL,
	[ShortDescription] [nvarchar](max) NULL,
	[Status] [nvarchar](10) NULL,
	[CategoryID] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 24 Feb 2021 12:32:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [nvarchar](20) NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
	[Description] [nvarchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password]) VALUES (N'abc', N'123456')
INSERT [dbo].[Account] ([username], [password]) VALUES (N'def', N'987654')
GO
INSERT [dbo].[Books] ([BookID], [Name], [Author], [PublishYear], [ShortDescription], [Status], [CategoryID]) VALUES (N'1         ', N'The Omerta', N'Mario Puzo', 2006, N'A Book about the strictest law in the Mafia World', N'Available', N'dtv')
INSERT [dbo].[Books] ([BookID], [Name], [Author], [PublishYear], [ShortDescription], [Status], [CategoryID]) VALUES (N'2         ', N'The Selfish Gene', N'Richard Darwin', 1976, N'The Selfish Gene is a 1976 book on evolution by the biologist Richard Dawkins, in which the author builds upon the principal theory of George C. Williams''s Adaptation and Natural Selection.', N'Available', N'sci')
INSERT [dbo].[Books] ([BookID], [Name], [Author], [PublishYear], [ShortDescription], [Status], [CategoryID]) VALUES (N'3         ', N'Viet-nam Su-luoc', N'Tran Trong-Kim', 1920, N'Viet-nam Su-luoc was the first history text published in the Vietnamese alphabet. It was compiled by Vietnamese historian Tran Trong-Kim. It covered the period from Hong-Bang dynasty to the time of French Indochina', N'Sold', N'his')
INSERT [dbo].[Books] ([BookID], [Name], [Author], [PublishYear], [ShortDescription], [Status], [CategoryID]) VALUES (N'4         ', N'The Godfather', N'Mario Puzo', 1982, N'The Godfather is a crime novel by American author Mario Puzo. Originally published in 1969 by G. P. Putnam''s Sons, the novel details the story of a fictional Mafia family in New York City (and Long Beach, New York), headed by Vito Corleone.', N'Available', N'dtv')
GO
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description]) VALUES (N'dtv', N'Detective', N'detective')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description]) VALUES (N'his', N'History', N'history')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description]) VALUES (N'mus', N'Music', N'music')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description]) VALUES (N'sci', N'Science', N'science')
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
USE [master]
GO
ALTER DATABASE [SE1502_Workshop1_TangChiCuong] SET  READ_WRITE 
GO
