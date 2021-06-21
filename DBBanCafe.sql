Create Database DBBanCafe
go
USE [DBBanCafe]
GO
/****** Object:  Table [dbo].[BAN]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BAN](
	[IdTable] [int] IDENTITY(1,1) NOT NULL,
	[TableNumber] [nvarchar](30) NULL,
	[TableStatus] [nvarchar](30) NULL,
 CONSTRAINT [PK_TABLE] PRIMARY KEY CLUSTERED 
(
	[IdTable] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BILL]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BILL](
	[IdBill] [int] IDENTITY(1,1) NOT NULL,
	[IdTable] [int] NOT NULL,
	[IdEmployee] [int] NULL,
	[TotalMoney] [int] NULL,
	[MoneyReduce] [int] NULL,
	[MoneyFinal] [int] NULL,
	[DateCreate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK__BILL__24A2D64DBB254C9F] PRIMARY KEY CLUSTERED 
(
	[IdBill] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BILL_INFO]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BILL_INFO](
	[IdBill] [int] NOT NULL,
	[IdProduct] [int] NOT NULL,
	[Quantity] [int] NULL,
	[Price] [int] NULL,
 CONSTRAINT [PK__BILL_INF__764A42208E9F5CC0] PRIMARY KEY CLUSTERED 
(
	[IdBill] ASC,
	[IdProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CUSTOMER]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CUSTOMER](
	[IdCustomer] [int] IDENTITY(1,1) NOT NULL,
	[FullnameCustomer] [nvarchar](100) NULL,
	[PhoneCustomer] [char](10) NULL,
	[NoteCustomer] [ntext] NULL,
 CONSTRAINT [PK__CUSTOMER__DB43864AEAE5D519] PRIMARY KEY CLUSTERED 
(
	[IdCustomer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DISCOUNT]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DISCOUNT](
	[IdDiscount] [int] IDENTITY(1,1) NOT NULL,
	[CodeDiscount] [nvarchar](20) NULL,
	[Value] [int] NULL,
 CONSTRAINT [PK__DISCOUNT__C6A0EA322994278F] PRIMARY KEY CLUSTERED 
(
	[IdDiscount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EMPLOYEE]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EMPLOYEE](
	[IdEmployee] [int] IDENTITY(1,1) NOT NULL,
	[Fullname] [nvarchar](100) NULL,
	[Username] [varchar](20) NULL,
	[Password] [varchar](32) NULL,
	[Phone] [char](10) NULL,
	[Role] [nvarchar](20) NULL,
	[Image] [varchar](50) NULL,
 CONSTRAINT [PK__EMPLOYEE__51C8DD7A6AC1872E] PRIMARY KEY CLUSTERED 
(
	[IdEmployee] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PRODUCT]    Script Date: 22/5/2021 11:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRODUCT](
	[IdProduct] [int] IDENTITY(1,1) NOT NULL,
	[NameProduct] [nvarchar](100) NULL,
	[PriceProduct] [nvarchar](20) NULL,
	[NoteProduct] [nvarchar](50) NULL,
 CONSTRAINT [PK__PRODUCT__2E8946D4D9D4FB93] PRIMARY KEY CLUSTERED 
(
	[IdProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BILL]  WITH CHECK ADD  CONSTRAINT [FK_BILL_BAN] FOREIGN KEY([IdTable])
REFERENCES [dbo].[BAN] ([IdTable])
GO
ALTER TABLE [dbo].[BILL] CHECK CONSTRAINT [FK_BILL_BAN]
GO
ALTER TABLE [dbo].[BILL]  WITH CHECK ADD  CONSTRAINT [FK_BILL_EMPLOYEE] FOREIGN KEY([IdEmployee])
REFERENCES [dbo].[EMPLOYEE] ([IdEmployee])
GO
ALTER TABLE [dbo].[BILL] CHECK CONSTRAINT [FK_BILL_EMPLOYEE]
GO
ALTER TABLE [dbo].[BILL_INFO]  WITH CHECK ADD  CONSTRAINT [FK_BILL_INFO_BILL] FOREIGN KEY([IdBill])
REFERENCES [dbo].[BILL] ([IdBill])
GO
ALTER TABLE [dbo].[BILL_INFO] CHECK CONSTRAINT [FK_BILL_INFO_BILL]
GO
ALTER TABLE [dbo].[BILL_INFO]  WITH CHECK ADD  CONSTRAINT [FK_BILL_INFO_PRODUCT] FOREIGN KEY([IdProduct])
REFERENCES [dbo].[PRODUCT] ([IdProduct])
GO
ALTER TABLE [dbo].[BILL_INFO] CHECK CONSTRAINT [FK_BILL_INFO_PRODUCT]
GO
USE [master]
GO
ALTER DATABASE [DBBanCafe] SET  READ_WRITE 
GO