Feature: Calculate Shipping Cost Based on Order Total and Customer Location

	Scenario: UK shipping cost when order total is under 100
		Given an order with a total excluding shipping of 99.99
		And the customer is located in country "United Kingdom"
		When the shipping cost is calculated
		Then the shipping cost is 5.99

	Scenario: UK shipping cost when order total is 100 or more
		Given an order with a total excluding shipping of 100.00
		And the customer is located in country "United Kingdom"
		When the shipping cost is calculated
		Then the shipping cost is 0.00

	Scenario: EU shipping cost when order total is under 100
		Given an order with a total excluding shipping of 99.99
		And the customer is located in country "Germany"
		When the shipping cost is calculated
		Then the shipping cost is 9.99

	Scenario: EU shipping cost when order total is 100 or more
		Given an order with a total excluding shipping of 100.00
		And the customer is located in country "France"
		When the shipping cost is calculated
		Then the shipping cost is 5.99

	Scenario: Other region shipping cost when order total is under 100
		Given an order with a total excluding shipping of 99.99
		And the customer is located in country "United States"
		When the shipping cost is calculated
		Then the shipping cost is 12.99

	Scenario: Other region shipping cost when order total is 100 or more
		Given an order with a total excluding shipping of 100.00
		And the customer is located in country "Japan"
		When the shipping cost is calculated
		Then the shipping cost is 9.99