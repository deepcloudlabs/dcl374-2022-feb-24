/*
	Customer Model
 */
class Customer {
	constructor() {
		// observables
		this.identity = ko.observable("11111111110")
			.extend({
				'required': true,
				'tcKimlikNo': true,
				'message': "You must enter a valid identity no."
			});
		this.email = ko.observable("")
			.extend({
				'required': true,
				'email': true,
				'message': "You must enter a valid email."
			});
		this.phone = ko.observable("")
			.extend({
				'required': true,
				'message': "You must enter a phone."
			});
		this.birthYear = ko.observable(1990).extend({
			'required': true,
			'max': 2004
		});
		this.fullname = ko.observable("jack bauer")
			.extend({
				'required': true,
				'minLength': 5
			});
		this.photo = ko.observable(AppConfig.NO_IMAGE);
		this.addresses = ko.observableArray([]);
	}

	valid() {
		for (let prop in this) {
			let o = this[prop];
			if (ko.isObservable(o) &&
				o.hasOwnProperty('rules')) {
				if (!o.isValid()) return false;
			}
		}
		return true;
	}

	validate() {
		for (let prop in this) {
			let o = this[prop];
			if (ko.isObservable(o) &&
				o.hasOwnProperty('rules')) {
				ko.validation.validateObservable(o);
			}
		}
	}
	
	refresh(customer) {
		this.addresses([]);
		for (let prop in customer) {
			if (this.hasOwnProperty(prop)) {
				let o = this[prop];
				if (prop == "addresses") {
					customer.addresses.forEach(addr => this.addresses.push(new Address(addr)));
					continue;
				}
				if (ko.isObservable(o)) {
					o(customer[prop]);
				} else {
					o = customer[prop];
				}
			}
		}
	}
}
