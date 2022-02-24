/**
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 * Utility Functions
 */
var toSrcImage = function (img) {
    return "data:image/png;base64," + img;
};

var toRawImage = function (img) {
    return img.split(",")[1];
};

var calculateModulus = function (code) {
    console.log("calculateModulus");
    var reformattedCode = code.substring(4) + code.substring(0, 4);
    reformattedCode = reformattedCode.replace(/[A-Z]/g, function (match) {
        return match.charCodeAt(0) - 55;
    });
    var total = 0;
    for (i = 0; i < reformattedCode.length; i++) {
        charValue = reformattedCode.charCodeAt(i) - 48;
        if (charValue < 0 || charValue > 35) {
            return 0;
        }
        total = (Number(charValue) > 9 ? total * 100 : total * 10) + charValue;
        if (total < AppConfig.MAX) {
            total = (total % AppConfig.MODULUS);
        }
    }
    return total % AppConfig.MODULUS;
};

var ajaxErrorHandler = function (jqXHR, error, errorThrown) {
    var errorMessage = JSON.parse(jqXHR.responseText);
    var listOfIds = errorMessage.i18nId.split("|");
    console.log(listOfIds);
    for (var i in listOfIds) {
        toastr.error(i18n.t(listOfIds[i]), "", AppConfig.TOASTR_CONFIG);
    }
};

var ShowMessage = function (level, message) {
    level(message, "", AppConfig.TOASTR_CONFIG);
};

var currencySymbols = {
    "usd": "$",
    "try": "₺",
    "eur": "€",
    "gbp": "£",
    "aud": "$",
    "cad": "$",
    "nok": "kr",
    "dkk": "kr",
    "jpy": "¥",
    "sar": "﷼",
    "cny": "¥",
    "sek": "kr",
};

ko.bindingHandlers.price = {
    update: function (element, valueAccessor) {
        var amount = valueAccessor();
        if (ko.isObservable(amount))
            amount = amount();
        var formatted = amount != null ?
            Number(amount).toFixed(3) : '';
        $(element).text(formatted);
    }
};

ko.bindingHandlers.currency = {
    update: function (element, valueAccessor) {
        var currency = valueAccessor();
        if (ko.isObservable(currency))
            currency = currency();
        var symbol = "";
        if (currency != null && currencySymbols.hasOwnProperty(currency)) ;
        symbol = currencySymbols[currency];
        var symbol = symbol == undefined ? '' : symbol;
        $(element).text(currency.toUpperCase() + " (" + symbol + ")");
    }
};

ko.extenders.trackChange = function (target, track) {
    if (track) {
        target.isDirty = ko.observable(false);
        target.originalValue = target();
        target.setOriginalValue = function (startingValue) {
            target.originalValue = startingValue;
        };
        target.subscribe(function (newValue) {
            // use != not !== so numbers will equate naturally
            target.isDirty(newValue != target.originalValue);
        });
    }
    return target;
};