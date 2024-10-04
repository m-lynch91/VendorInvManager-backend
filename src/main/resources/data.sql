-- initial vendors data
INSERT INTO Vendor (Address,City,Province,Postal_Code,Phone,Type,Name,Email) 
    VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address,City,Province,Postal_Code,Phone,Type,Name,Email) 
    VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills Depot','bb@depot.com');
INSERT INTO Vendor (Address,City,Province,Postal_Code,Phone,Type,Name,Email) 
    VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Untrusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address,City,Province,Postal_Code,Phone,Type,Name,Email) 
    VALUES ('123 First St','Toronto','On', 'N1N-1N2','(555)555-5551','Trusted','Mikes Mart','mm@vendor.com');

-- initial products data
INSERT INTO Product (Id,VendorId,Name,PurchasePrice,MSRP,ReorderPoint,EconomicOrderQuantity,QuantityOnHand,QuantityOnOrder,QRCode,QRCodeText)
    VALUES ('7800X3D', 4, 'CPU', 500.00, 449.00, 5, 15, 6, 9, null, null);
INSERT INTO Product (Id,VendorId,Name,PurchasePrice,MSRP,ReorderPoint,EconomicOrderQuantity,QuantityOnHand,QuantityOnOrder,QRCode,QRCodeText)
    VALUES ('7900X3D', 4, 'CPU', 700.00, 599.00, 3, 7, 8, 0, null, null);
INSERT INTO Product (Id,VendorId,Name,PurchasePrice,MSRP,ReorderPoint,EconomicOrderQuantity,QuantityOnHand,QuantityOnOrder,QRCode,QRCodeText)
    VALUES ('BL770', 3, 'Blender', 249.99, 199.99, 3, 6, 4, 2, null, null);
INSERT INTO Product (Id,VendorId,Name,PurchasePrice,MSRP,ReorderPoint,EconomicOrderQuantity,QuantityOnHand,QuantityOnOrder,QRCode,QRCodeText)
    VALUES ('102B', 4, 'Hunting Knife', 74.99, 50.00, 3, 5, 3, 2, null, null);