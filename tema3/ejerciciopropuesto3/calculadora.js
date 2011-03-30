var result = 0;
var operation = 0;
var operator = 0;
var showingResult = true;

function isEmpty(v) {
    return v == null || v == "";
}

function getVal() {
    return document.datos.pantalla.value
}

function setVal(val) {
    document.datos.pantalla.value = val;
}

function addNumber(n) {
    if (showingResult) {
	setVal(n);
	showingResult = false;
	return;
    }

    if (parseInt(getVal()) == 0)
	setVal('');

    setVal (getVal() + n);
}

function showResult() {
    setVal(operator);
    operation = 0;
    showingResult = true;
}

function applyOperation() {
    value = parseInt(getVal());
    switch (operation) {
    case 0:
	operator = value;
	break;
    case 1:
	operator += value;
	break;
    case 2:
	operator -= value;
	break;
    case 3:
	operator *= value;
	break;
    case 4:
	operator /= value;
	break;
    }
    showResult();
}

function equal() {
    applyOperation();
    operation = 0;
    operator = 0;
}

function sum() {
    applyOperation();
    operation = 1;
}

function substract() {
    applyOperation();
    operation = 2;
}

function multiply() {
    applyOperation();
    operation = 3;
}

function divide () {
    applyOperation();
    operation = 4;
}
