-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[uspAddUser]
	@companyId varchar(50),
	@firstName varchar(50), 
    @lastName varchar(50),
    @email varchar(50),
    @statusId int
AS
BEGIN
		INSERT INTO Users
               (company_id,first_name,last_name,email,status_id)
               VALUES 
                  (@companyId,@firstName,@lastName,@email,@statusId)

END
