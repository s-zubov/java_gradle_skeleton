Feature: Confirm Order

	Scenario: Confirm an order adjusts stock, releases holds, and updates order status
		Given products exist:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 7     | 2    |
				| 811 | Fender Deluxe Reverb | 2     | 1    |
		And an open order exists with the following items:
				| product | quantity |
				| 327     | 2        |
				| 811     | 1        |
		When the order is confirmed
		Then the order status is "Confirmed"
		And the product inventory becomes:
				| id  | description          | stock | hold |
				| 327 | Ibanez Tube Screamer | 5     | 0    |
				| 811 | Fender Deluxe Reverb | 1     | 0    |