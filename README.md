# NUSTANA
[![NUSTANA](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/src/Images/anim_logo.gif "NUSTANA")](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/src/Images/anim_logo.gif "NUSTANA")

## User Side

### Login
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/login.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/login.png)
### Registration
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/registration.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/registration.png)
### Order History
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/ordersHistory.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/ordersHistory.png)
### Shops
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shops.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shops.png)
### Menu
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/menu.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/menu.png)
### Order Placement
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/orderPlacement.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/orderPlacement.png)
## Shop Side

### Shop Registration
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopRegistration.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopRegistration.png)
### Orders List
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/ordersList.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/ordersList.png)
### Shop Items
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopItems.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopItems.png)
### Shop Items Editor
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/itemEditor.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/itemEditor.png)
## Info Boxes

### Profile Info
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/profileInfo.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/profileInfo.png)
### Shop Info
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopInfo.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/shopInfo.png)
### Item Info
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/itemInfo.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/itemInfo.png)
### Order Info
[![](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/orderInfo.png)](https://raw.githubusercontent.com/ZdsAlpha/NUSTANA/master/NUSTANA/Screenshots/orderInfo.png)

# Working
## Successful Delivery
```seq
User->Database: Place Order (Pending)
Database->Shop: Orders
Shop->Database: Accept Order
Database->User: Order Accepted
Shop->Database: Order Delivered
Database->User: Order Delivered
User-->Database: Discard Order
```
## Order Canceled By User
```seq
User->Database: Place Order (Pending)
Database->Shop: Orders
User->Database: Cancel Order
Database->Shop: Update Orders
```
## Order Rejected By Shop
```seq
User->Database: Place Order (Pending)
Database->Shop: Orders
Shop->Database: Reject Order
Database->User: Order Rejected
User-->Database: Discard Order
```
