Feature: Calculate Order Total Excluding Shipping

	Scenario: Calculate total for an order with no items
		Given an order with no items
		When the order total excluding shipping is calculated
		Then the total excluding shipping is 0.00

	Scenario: Calculate total for an order with one item
		Given a product exists with:
				| id  | description          | price  | stock | hold |
				| 327 | Ibanez Tube Screamer | 159.95 | 7     | 1    |
		And an order exists with the following items:
				| product | quantity |
				| 327     | 1        |
		When the order total excluding shipping is calculated
		Then the total excluding shipping is 159.95

	Scenario: Calculate total for an order with two items
		Given the following products exist:
				| id  | description          | price   | stock | hold |
				| 327 | Ibanez Tube Screamer | 159.95  | 7     | 1    |
				| 811 | Fender Deluxe Reverb | 1799.00 | 2     | 1    |
		And an order exists with the following items:
				| product | quantity |
				| 327     | 1        |
				| 811     | 1        |
		When the order total excluding shipping is calculated
		Then the total excluding shipping is 1958.95

	Scenario: Calculate total for an item with quantity greater than one
		Given a product exists with:
				| id  | description          | price  | stock | hold |
				| 327 | Ibanez Tube Screamer | 159.95 | 7     | 2    |
		And an order exists with the following items:
				| product | quantity |
				| 327     | 2        |
		When the order total excluding shipping is calculated
		Then the total excluding shipping is 319.90