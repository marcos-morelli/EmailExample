package br.com.morelli.emailExample;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Email {
	
	private static JUnitCore JUNIT = new JUnitCore();
	
	private String endereco;
	
	public Email(String email) {
		this.endereco = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String email) {
		this.endereco = email;
	}
	
	public boolean isValid() throws IOException {
		FileUtils.writeStringToFile(new File("f.txt"), endereco);
		Result result = JUNIT.run(EmailTeste.class);
		return result.wasSuccessful();
	}

}
