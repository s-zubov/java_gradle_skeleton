Feature: Remove Item From Order

	Scenario: Remove an item from an order
		Given a product exists with:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 7     | 2    |
		And an order exists with the following items:
				| product | quantity |
				| 327     | 2        |
		And the customer selects the item for "Ibanez Tube Screamer"
		When the customer removes the item from the order
		Then the temporary hold of 2 is released from the product stock
		And the order contains no items