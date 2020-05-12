
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetInventoryItemsOrderedDesc]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetInventoryItemsOrderedDesc];
    END;
GO

CREATE PROCEDURE [dbo].[ispGetInventoryItemsOrderedDesc]
AS
BEGIN
		SELECT i.id, i.owner_id, i.room_id, i.type_id, i.serial_id, i.producer, i.model, i.inventory_number, i.status_id, i.comment

FROM Item i ORDER BY id DESC

END
GO
