
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetInventoryItems]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetInventoryItems];
    END;
GO

CREATE PROCEDURE [dbo].[ispGetInventoryItems]
AS
BEGIN
		SELECT i.id, i.owner_id, i.room_id, i.type_id, i.serial_id, i.producer, i.model, i.inventory_number, i.status_id, i.comment

FROM Item i ORDER BY id ASC

END
GO
