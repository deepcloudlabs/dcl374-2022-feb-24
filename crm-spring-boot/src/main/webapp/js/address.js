/*
	Address
 */
class Address {
	constructor(address={
		type: "HOME",
		street: "Kuyu",
		city: "istanbul",
		country: "Turkey",
		zipCode: "34212",
	}) {
		// observables
		this.type = ko.observable(address.type);
		this.street = ko.observable(address.street);
		this.city = ko.observable(address.city);
		this.country = ko.observable(address.country);
		this.zipCode = ko.observable(address.zipCode);
	}
}
