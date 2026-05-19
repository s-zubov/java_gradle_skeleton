Feature: Cancel Order

	Scenario: Cancel an order releases stock holds and sets order status
		Given a product exists:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 7     | 2    |
				| 811 | Fender Deluxe Reverb | 2     | 1    |
		And an open order exists with the following items:
				| product | quantity |
				| 327     | 2        |
				| 811     | 1        |
		When the order is canceled
		Then the order status is "Canceled"
		And the product inventory becomes:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 7     | 0    |
				| 811 | Fender Deluxe Reverb | 2     | 0    |