USE [master]
GO
/****** Object:  Database [Base_Comerciantes]    Script Date: 01/11/2020 22:05:01 ******/
CREATE DATABASE [Base_Comerciantes]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Base_Comerciantes', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\Base_Comerciantes.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Base_Comerciantes_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\Base_Comerciantes_log.ldf' , SIZE = 3136KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Base_Comerciantes] SET COMPATIBILITY_LEVEL = 90
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Base_Comerciantes].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Base_Comerciantes] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET ARITHABORT OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [Base_Comerciantes] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Base_Comerciantes] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Base_Comerciantes] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Base_Comerciantes] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Base_Comerciantes] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Base_Comerciantes] SET  MULTI_USER 
GO
ALTER DATABASE [Base_Comerciantes] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Base_Comerciantes] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Base_Comerciantes] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Base_Comerciantes] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [Base_Comerciantes]
GO
/****** Object:  StoredProcedure [dbo].[Validar_usuario]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[Validar_usuario](@nombre varchar(80),@pass varchar(80),@operacion varchar(100),@resultado int out)
as

declare @identificador uniqueidentifier
declare @numero_de_registros int

begin
set @identificador = newId()
if(@operacion='insertar')

begin
insert into Tabla_Login values(@identificador,@nombre,encryptbypassphrase('pass',@pass))
end

else
begin
select @numero_de_registros =count(nombre_usuario)
from Tabla_Login t where nombre_usuario=@nombre and decryptbypassphrase('pass',pass)=@pass
if @numero_de_registros>0
begin
set @resultado=@numero_de_registros
print cast(@resultado as varchar)
end

else
begin
set @resultado=0
print cast(@resultado as varchar)
end
end
end 

GO
/****** Object:  Table [dbo].[Comerciantes]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comerciantes](
	[id_comerciante] [int] NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Comerciantes] PRIMARY KEY CLUSTERED 
(
	[id_comerciante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[comerciantes_Empleos]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comerciantes_Empleos](
	[id_comerciante] [int] NULL,
	[id_empleo] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Empleos]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Empleos](
	[id_empleo] [int] NULL,
	[Nom_Empleo] [varchar](100) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Empleos_Funciones]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleos_Funciones](
	[id_empleo] [int] NULL,
	[funcion] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Funciones]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Funciones](
	[id_funcion] [int] NULL,
	[nombre] [nvarchar](30) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Funciones_Presupuesto]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Funciones_Presupuesto](
	[id_funcion] [int] NULL,
	[id_presupuesto] [nvarchar](30) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Presupuesto]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Presupuesto](
	[id_presupuesto] [int] NULL,
	[valor] [money] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Tabla_Login]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tabla_Login](
	[id_usuario] [uniqueidentifier] NULL,
	[nombre_usuario] [nvarchar](100) NULL,
	[pass] [nvarchar](100) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Total]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Total](
	[tot] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Valores]    Script Date: 01/11/2020 22:05:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Valores](
	[id_comerciante] [int] NULL,
	[valor] [bit] NULL
) ON [PRIMARY]

GO
INSERT [dbo].[Comerciantes] ([id_comerciante], [nombre]) VALUES (4, N'PEz')
INSERT [dbo].[Comerciantes] ([id_comerciante], [nombre]) VALUES (5, N'Pedro Cantu Quiroga')
INSERT [dbo].[Comerciantes] ([id_comerciante], [nombre]) VALUES (6, N'Esteban Garcia Fernandez')
INSERT [dbo].[Comerciantes] ([id_comerciante], [nombre]) VALUES (8, N'Adrian Perez')
INSERT [dbo].[Comerciantes] ([id_comerciante], [nombre]) VALUES (9, N'Cinthia Ocaños')
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (1, 1)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (2, 2)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (3, 3)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (4, 4)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (5, 5)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (6, 6)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (7, 7)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (8, 8)
INSERT [dbo].[comerciantes_Empleos] ([id_comerciante], [id_empleo]) VALUES (9, 9)
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (2, N'Zapatero')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (3, N'Vendedor de Frutas')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (4, N'Mecanico')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (5, N'Electrico')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (6, N'Plomero')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (8, N'Albañil')
INSERT [dbo].[Empleos] ([id_empleo], [Nom_Empleo]) VALUES (9, N'Rotulista')
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (1, 1)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (1, 2)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (4, 3)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (4, 4)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (4, 5)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (5, 7)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (5, 8)
INSERT [dbo].[Empleos_Funciones] ([id_empleo], [funcion]) VALUES (5, 9)
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (1, N'Reparacion de Calzado')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (2, N'Limpieza de Calzado')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (3, N'Reparacion de Motor Hidraulico')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (4, N'Reparacion Llantas')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (5, N'Mantenimiento Gral')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (6, N'Instalacion Electrica')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (7, N'Reperacion de Pastilla Elect')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (8, N'Mto Gral electrico')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (9, N'Instalacion de Tub de agua')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (10, N'Reparacion de Bombilla')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (11, N'Contrucciones Grals')
INSERT [dbo].[Funciones] ([id_funcion], [nombre]) VALUES (12, N'Dibujos a Comp Grals')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (1, N'1')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (2, N'2')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (3, N'3')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (4, N'4')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (5, N'5')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (6, N'6')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (7, N'7')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (8, N'8')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (9, N'9')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (10, N'10')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (11, N'11')
INSERT [dbo].[Funciones_Presupuesto] ([id_funcion], [id_presupuesto]) VALUES (12, N'12')
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (1, 100.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (2, 300.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (3, 400.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (4, 800.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (5, 750.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (6, 920.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (7, 630.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (8, 300.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (9, 850.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (10, 690.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (11, 361.0000)
INSERT [dbo].[Presupuesto] ([id_presupuesto], [valor]) VALUES (12, 1000.0000)
INSERT [dbo].[Tabla_Login] ([id_usuario], [nombre_usuario], [pass]) VALUES (N'19e40403-e41f-4f39-8d1e-e4f4b4d60773', N'xx                                                                              ', N' �㜅쵂ᇎ싣ά功諩覑푤峌ӟ떺䜕曉촔ᜨ澱習摖Ⳏ妨棏䜤语ጵ휑ꏍ輛�硨ﰏꝽ큕⩉┟᫨뜚ᮜ콞᱌䏵⿴ᔂ�ᗱ됴ᠫ珪ߟ')
INSERT [dbo].[Tabla_Login] ([id_usuario], [nombre_usuario], [pass]) VALUES (N'25a74e38-9e84-4df6-ab58-43b104a10bec', N'xx                                                                              ', N' 蟸錡溬⛽䞈䝖峛夺抉ꦇ멱ỉ㿐笺Ჳᶆ�豏ꗵ惊젿높嚽哱돵ܜ㑭땦Сꅼ洀최休큤爮⢲秮㔷냋쥔�魣ѵࡖꖶ꽯璘﫼')
INSERT [dbo].[Tabla_Login] ([id_usuario], [nombre_usuario], [pass]) VALUES (N'fff7d5b0-5d10-4e62-ad27-a38ed6bf47da', N'Luis', N' 瘺䭞셆꠮稔㯘谁╞쨸륖')
INSERT [dbo].[Tabla_Login] ([id_usuario], [nombre_usuario], [pass]) VALUES (N'967b672d-00e2-4550-8dc7-c3a46110e556', N'ggggggg                                                                         ', N' 鴊逳麧롛勬望횄ㆂ焟恞缛闍탐&鵀牕ꥱ沑棶ﯠ鼴ᓸ๱릚׮녜바╌㸗踉颵Ⳬ탧恫Ｍ陖艌퉞툏⒯১鳶쌩緕巠麀鍯孆⡵⬆')
INSERT [dbo].[Valores] ([id_comerciante], [valor]) VALUES (1, 0)
INSERT [dbo].[Valores] ([id_comerciante], [valor]) VALUES (2, 0)
INSERT [dbo].[Valores] ([id_comerciante], [valor]) VALUES (3, 0)
USE [master]
GO
ALTER DATABASE [Base_Comerciantes] SET  READ_WRITE 
GO
