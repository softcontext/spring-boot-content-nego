package com.example.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.web.model.User;
import com.example.web.model.Users;

public class CsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

		Users data = ((Users)model.get("users-info"));
		List<User> users = data.getUsers();
		
		String[] header = { "Id", "Name", "Company" };

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(header);

		for (User user : users) {
			csvWriter.write(user, header);
		}

		csvWriter.close();
	}
}
