package org.salondeventas.cliente.desktop.view.control;

import javafx.scene.control.TextField;

public class DecimalField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceSelection(text);
		}
	}
}
