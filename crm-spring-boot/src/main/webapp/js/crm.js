/*
	CRM View Model
 */
class CrmViewModel {
	constructor() {
		// observables
		this.customer = new Customer();
		this.customers = ko.observableArray([]);
		this.fileData = ko.observable({
			dataUrl: ko.observable(AppConfig.NO_IMAGE)
		});
		this.addressTypes = ["HOME", "BUSINESS"]
	}

	changeLangToEn = () => {
		this.changeLang('en');
	}
	
	addEmptyAddress = () => {
		this.customer.addresses.push(new Address());
	}
	
	removeAddress = (addr) => {
		let clonedAddress = [...this.customer.addresses()];
		console.log(clonedAddress)
		clonedAddress.splice(clonedAddress.indexOf(addr),1)
		console.log(clonedAddress)
		this.customer.addresses(clonedAddress)
	}
	
	insertFile(e, data) {
		e.preventDefault();
		let files = e.target.files || e.originalEvent.dataTransfer.files;
		let reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = (event) => {
			this.fileData().dataUrl(event.target.result);
		};
	}

	dragover = (e) => {
		e.preventDefault();
	}

	changeLangToTr = () => {
		this.changeLang('tr');
	}

	i18n = () => {
		$(document).i18n();
	}

	changeLang = (lang) => {
		i18n.setLng(lang, () => {
			this.i18n();
			knockoutLocalize(lang);
			this.customer.validate();
		})
	}

	findCustomerByIdentity = () => {
		$.ajax({
			method: "GET",
			url: `${AppConfig.REST_API_BASE_URL}/customers/${this.customer.identity()}`,
			cache: false,
			success: (customer) => {
				this.populatePhotoIfNull(customer);
				this.fileData().dataUrl(customer.photo);
				this.customer.refresh(customer);
			},
			error: this.handleError
		})
	}

	copyCustomer = (cust) => {
		this.customer.refresh(cust);
		this.fileData().dataUrl(cust.photo);
	}

	removeCustomerByIdentity = (cust) => {
		let identity;
		if (cust.hasOwnProperty('customer'))
			identity = this.customer.identity();
		else {
			identity = cust.identity;
			this.customers.remove(cust);
		}

		$.ajax({
			method: "DELETE",
			url: `${AppConfig.REST_API_BASE_URL}/customers/${identity}`,
			success: (customer) => {
				this.populatePhotoIfNull(customer);
				this.fileData().dataUrl(customer.photo);
				this.customer.refresh(customer);
				ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
					"Customer is deleted!");
			},
			error: this.handleError
		})
	}

	populatePhotoIfNull = (customer) => {
		if (!customer.hasOwnProperty('photo'))
			customer.photo = AppConfig.NO_IMAGE;
		else if (customer.photo == null)
			customer.photo = AppConfig.NO_IMAGE;
		if (customer.photo.substr(0, 22) != "data:image/png;base64,")
			customer.photo = toSrcImage(customer.photo);
	}

	addCustomer = () =>  {
		let data = ko.toJS(this.customer);
		data.photo = toRawImage(this.fileData().dataUrl());
		let json = JSON.stringify(data);
		$.ajax({
			method: "POST",
			url: `${AppConfig.REST_API_BASE_URL}/customers`,
			contentType: "application/json",
			data: json,
			success: (customer) => {
				ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
					"Customer is added!");
			},
			error: this.handleError
		})
	}

	handleError = (xhr) => {
		let messages = JSON.parse(xhr.responseText).message.split('|');
		for (let i in messages)
			ShowMessage(AppConfig.MESSAGE_TYPE.ERROR, messages[i]);
	}

	updateCustomer = () => {
		let data = ko.toJS(this.customer);
		data.photo = toRawImage(this.fileData().dataUrl());
		let json = JSON.stringify(data);
		$.ajax({
			method: "PUT",
			url: `${AppConfig.REST_API_BASE_URL}/customers/${this.customer.identity()}`,
			contentType: "application/json",
			data: json,
			success: (customer) => {
				ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
					"Customer is updated!");
			},
			error: this.handleError
		})
	}

	findAllCustomers = () => {
		$.ajax({
			method: "GET",
			url: AppConfig.REST_API_BASE_URL
				+ "/customers?page=0&size=20",
			cache: false,
			success: (customers) => {
				for (let i in customers) {
					this.populatePhotoIfNull(customers[i]);
				}
				customers.sort(
					(c1, c2) => c1.fullname
						.localeCompare(c2.fullname)
				);
				this.customers(customers);
				this.i18n();
			},
			error: this.handleError
		})
	}
}