
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispInsertItemType]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispInsertItemType];
    END;
GO

CREATE PROCEDURE [dbo].[ispInsertItemType]

    @itemType varchar(100)
    
AS
BEGIN
		INSERT INTO Item_Type (type) VALUES (@itemType)

END
