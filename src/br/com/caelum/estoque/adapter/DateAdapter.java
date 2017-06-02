package br.com.caelum.estoque.adapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	private String pattern = "dd/MM/yyyy";
	
	public Date unmarshal(String dateString) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public String marshal(Date date) throws Exception {
		return new SimpleDateFormat(pattern).format(date);
	}

	public DateAdapter() {
	}
}
