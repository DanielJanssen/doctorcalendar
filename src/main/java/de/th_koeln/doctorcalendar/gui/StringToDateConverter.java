package de.th_koeln.doctorcalendar.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringToDateConverter extends com.vaadin.data.util.converter.StringToDateConverter {
	@Override
	protected DateFormat getFormat(Locale aLocale) {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
}
