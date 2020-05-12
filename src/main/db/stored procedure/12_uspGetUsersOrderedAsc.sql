IF EXISTS
(
    SELECT 1
    FROM [information_schema].[routines]
    WHERE [routine_name] = '[uspGetUsers]'
)
    BEGIN
        DROP PROCEDURE [dbo].[uspGetUsers];
    END;
GO
CREATE PROCEDURE [dbo].[uspGetUsers]
AS
BEGIN
        SELECT u.id, u.company_id, u.first_name, u.last_name, u.email, u.status_id
FROM Users u ORDER BY id ASC
END
GO


