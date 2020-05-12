IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetInventoryItemStates]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetInventoryItemStates];
    END;
GO
CREATE PROCEDURE ispGetInventoryItemStates
AS
BEGIN
	SELECT DISTINCT State.id, State.type FROM State 
	JOIN Item 
	ON Item.status_id = State.id
END
GO
