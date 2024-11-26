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
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('7800X3D', 4, 'CPU', 500.00, 449.00, 5, 15, 6, 9, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('7900X3D', 4, 'CPU', 700.00, 599.00, 3, 7, 8, 0, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('BL770', 3, 'Blender', 249.99, 199.99, 3, 6, 4, 2, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('102B', 4, 'Hunting Knife', 74.99, 50.00, 3, 5, 3, 2, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('5800X', 4, 'CPU', 300.00, 329.00, 4, 10, 7, 5, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('i7-12700K', 5, 'CPU', 400.00, 419.00, 6, 12, 10, 3, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('GTX1660', 6, 'Graphics Card', 200.00, 249.00, 5, 10, 8, 4, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('RTX3080', 6, 'Graphics Card', 800.00, 699.00, 3, 5, 2, 3, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('NM800', 3, 'Mixer', 150.00, 129.00, 2, 6, 4, 2, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('SG200', 7, 'Vacuum Cleaner', 100.00, 149.00, 5, 10, 6, 3, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('XT500', 8, 'Drone', 250.00, 299.00, 4, 8, 3, 1, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('WT101', 2, 'Wrench Toolset', 49.99, 59.99, 6, 12, 10, 5, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('LT500', 5, 'Lawn Trimmer', 120.00, 139.99, 5, 10, 4, 6, null, null);
INSERT INTO Product (Id, VendorId, Name, PurchasePrice, MSRP, ReorderPoint, EconomicOrderQuantity, QuantityOnHand, QuantityOnOrder, QRCode, QRCodeText)
    VALUES ('BK202', 1, 'Book Shelf', 75.00, 99.99, 3, 8, 5, 2, null, null);
