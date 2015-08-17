package br.com.morelli.emailExample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EmailTeste {

	private Email email;

	public EmailTeste(String email) {
		this.email = new Email(email);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() throws IOException {
		String email = FileUtils.readFileToString(new File("f.txt"));
		Collection<Object[]> parameters = new ArrayList<Object[]>();
		parameters.add(new Object[] { email });
		return parameters;
	}

	@Test
	public void deveriaConterArroba() {
		Assert.assertTrue(email.getEndereco().contains("@"));
	}

	@Test
	public void deveriaTerLocalPart() {
		Assert.assertFalse(getLocalPart().isEmpty());
	}

	@Test
	public void deveriaTerDomainPart() {
		Assert.assertFalse(getDomainPart().isEmpty());
	}

	@Test
	public void deveriaTerApenasUmArroba() {
		Assert.assertTrue(email.getEndereco().indexOf('@') == email.getEndereco().lastIndexOf('@'));
	}
	
	@Test
	public void deveriaNaoTerDoisPontosSeguidos() {
		String s = email.getEndereco();
		int index = 0;
		
		while ((index = s.indexOf('.')) > -1) {
			s = s.substring(index + 1);
			if (s.charAt(0) == '.')
				Assert.fail();
		}
	}
	
	@Test
	public void deveriaTerminarComAspasSeParteLocalComecouComAspas() {
		String s = email.getEndereco();
		if (s.charAt(0) == '"')
			Assert.assertTrue(getLocalPart().charAt(getLocalPart().length() - 1) == '"');
	}
	
	@Test
	public void deveriaFecharAspasAbertasNaParteLocal() {
		String s = getLocalPart();
		int index = 0;
		int count = 0;
		
		while ((index = s.indexOf('"')) > -1) {
			count++;
			s = s.substring(index + 1);
		}
		
		Assert.assertTrue(count % 2 == 0);
	}

	@Test
	public void deveriaNaoComecarComEspaco() {
		Assert.assertTrue(email.getEndereco().charAt(0) != ' ');
	}

	@Test
	public void deveriaNaoComecarComPonto() {
		Assert.assertTrue(email.getEndereco().charAt(0) != '.');
	}

	@Test
	public void deveriaNaoTerminarComEspaco() {
		Assert.assertTrue(email.getEndereco().charAt(email.getEndereco().length() - 1) != ' ');
	}

	@Test
	public void deveriaNaoTerminarComPonto() {
		Assert.assertTrue(email.getEndereco().charAt(email.getEndereco().length() - 1) != '.');
	}

	public String getLocalPart() {
		return email.getEndereco().substring(0, email.getEndereco().indexOf('@'));
	}

	public String getDomainPart() {
		return email.getEndereco().substring(email.getEndereco().indexOf('@') + 1);
	}

}
