Feature: Add Item To Order

	# When an item is added to an order, to prevent another customer buying
	# that stock before this order is completed, the sale quantity is put on
	# hold for the selected product

	Scenario: Add an item when sufficient stock is available
		Given a new order with no items
		And the customer selects the product:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 7     | 0    |
		And the customer specifies a sale quantity of 1
		When the customer adds the item to their order
		Then a temporary hold of 1 is placed on the product stock
		And the order contains an item for the selected product with quantity 1

	Scenario: Reject adding an item when insufficient stock is available and no stock is on hold
		Given a new order with no items
		And the customer selects the product:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 1     | 0    |
		And the customer specifies a sale quantity of 2
		When the customer requests to add the item to their order
		Then an "Insufficient stock" error is raised
		And the error message is:
"Insufficient stock of Ibanez Tube Screamer. Only 1 currently available."
		And the order contains no items

	Scenario: Reject adding an item when insufficient stock is available due to stock on hold
		Given a new order with no items
		And the customer selects the product:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 2     | 1    |
		And the customer specifies a sale quantity of 2
		When the customer requests to add the item to their order
		Then an "Insufficient stock" error is raised
		And the error message is:
"Insufficient stock of Ibanez Tube Screamer. Only 1 currently available."
		And the order contains no item