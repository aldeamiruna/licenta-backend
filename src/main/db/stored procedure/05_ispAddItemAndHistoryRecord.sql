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
    WHERE [routine_name] = '[ispAddItemAndHistoryRecord]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispAddItemAndHistoryRecord];
    END;
GO

CREATE PROCEDURE [dbo].[ispAddItemAndHistoryRecord]
    @ownerId int, 
    @roomId int,
    @typeId int,
    @seriaId varchar(50),
    @producer varchar(50),
    @model varchar(50),
    @inventoryNumber bigint,
    @statusId int,
    @comment varchar(255)
AS

declare @ItemID int

BEGIN
		INSERT INTO Item
               (owner_id,room_id,type_id,serial_id,producer,model,inventory_number,status_id,comment)
               VALUES 
                  (@ownerId,@roomId,@typeId,@seriaId,@producer,@model,@inventoryNumber,@statusId,@comment)

	set @ItemID = SCOPE_IDENTITY()

		INSERT INTO History_Item
			(item_id,status_item,date,comment)
			VALUES
			(@ItemID,@statusId,GETDATE(),@comment)

END