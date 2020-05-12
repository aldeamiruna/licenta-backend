IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetItemTypes]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetItemTypes];
    END;
GO
CREATE PROCEDURE [dbo].[ispGetItemTypes]
AS
BEGIN
		SELECT DISTINCT Item_Type.id, Item_Type.type 
		FROM Item_Type 
		JOIN Item 
		ON Item.type_id = Item_Type.id

END
