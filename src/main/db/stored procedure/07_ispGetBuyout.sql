USE [InventoryDashboard]
GO
/****** Object:  StoredProcedure [dbo].[ispGetBuyout]    Script Date: 10/10/2019 4:20:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ispGetBuyout]
AS
BEGIN
SET NOCOUNT ON
if object_id('tempdb..#Temp_Owner') is not null
    drop table #Temp_Owner;
if object_id('tempdb..#Buyout') is not null
    drop table #Buyout;

 

SELECT i.owner_id 
INTO #Temp_Owner
FROM Item i
 WHERE i.type_id = 1
GROUP BY i.owner_id
HAVING COUNT(i.owner_id )>1 

 

SELECT DISTINCT u.first_name as first_name,u.last_name as last_name, FIRST_VALUE(i.producer)
OVER(PARTITION BY i.owner_id ORDER BY h.date ASC) as producer, FIRST_VALUE(i.model) 
OVER(PARTITION BY i.owner_id ORDER BY h.date ASC) as model
INTO #Buyout
FROM #Temp_Owner temp 
JOIN [dbo].Item i 
ON i.owner_id = temp.owner_id
JOIN [dbo].History_Item h
ON h.item_id = i.id 
JOIN [dbo].Users u 
ON u.id = temp.owner_id
    WHERE u.first_name!='Cerner' 
END

 

SELECT * FROM #Buyout