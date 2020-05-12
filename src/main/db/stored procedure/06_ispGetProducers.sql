IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[ispGetProducers]'
)
    BEGIN
        DROP PROCEDURE [dbo].[ispGetProducers];
    END;
GO
CREATE PROCEDURE [dbo].[ispGetProducers]
AS
BEGIN
		SELECT TOP 5 producer FROM Item;
END