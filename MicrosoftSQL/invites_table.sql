USE [ShareablePlanner]
GO

/****** Object:  Table [dbo].[INVITES]    Script Date: 12/10/2022 9:23:18 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[INVITES](
	[INVITES_ID] [int] IDENTITY(1,1) NOT NULL,
	[INVITER_ID] [int] NOT NULL,
	[INVITEE_ID] [int] NOT NULL,
	[SCHEDULE_ID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[INVITES_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[INVITES]  WITH CHECK ADD FOREIGN KEY([INVITEE_ID])
REFERENCES [dbo].[PERSON] ([PERSON_ID])
GO

ALTER TABLE [dbo].[INVITES]  WITH CHECK ADD FOREIGN KEY([INVITER_ID])
REFERENCES [dbo].[PERSON] ([PERSON_ID])
GO

ALTER TABLE [dbo].[INVITES]  WITH CHECK ADD FOREIGN KEY([SCHEDULE_ID])
REFERENCES [dbo].[SCHEDULE] ([SCHEDULE_ID])
GO


