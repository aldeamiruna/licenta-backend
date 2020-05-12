USE [InventoryDashboard]
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[hspItemHistory]'
)
    BEGIN
        DROP PROCEDURE [dbo].[hspItemHistory];
    END;
GO

CREATE PROCEDURE [dbo].[hspItemHistory]

    @itemId int

AS


BEGIN
		select h.id,h.item_id,h.comment,h.status_item,h.date from History_Item h where h.item_id=@itemId 
		ORDER BY h.id DESC; 

END
