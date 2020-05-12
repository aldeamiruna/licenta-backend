IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetRecentAssets]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetRecentAssets];
    END;
GO

CREATE PROCEDURE [dbo].[ispGetRecentAssets]
AS
BEGIN
		SELECT TOP 10 i.id, i.owner_id, i.room_id, i.type_id, i.serial_id, i.producer, i.model, i.inventory_number, i.status_id, i.comment

FROM Item i 
	JOIN History_Item h
	ON h.item_id = i.id
	ORDER BY h.date DESC

END
GO