package br.com.morelli.emailExample;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		List<String> listaDeEmails = FileUtils.readLines(new File("emailsList.txt"));
		for (String s : listaDeEmails) {
			Email email = new Email(s);
			if (!email.isValid()) 
				System.out.println(email.getEndereco() + " not valid!");
		}
	}

}
