/*******************************************************************************
 * This file is part of the authoring environment in Nested Context Language -
 * NCL Eclipse.
 * 
 * Copyright: 2007-2009 UFMA/LAWS (Laboratory of Advanced Web Systems), All Rights Reserved.
 * 
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU General Public License version 2 for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License version 2
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 * For further information contact:
 * 		ncleclipse@laws.deinf.ufma.br
 * 		http://www.laws.deinf.ufma.br/ncleclipse
 * 		http://www.laws.deinf.ufma.br
 ********************************************************************************/
package br.ufma.deinf.laws.ncl.help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class NCLHelperFactory {

	private String helpFileName;
	private String fileBuffer;
	private Map<String, Map<String, String>> help;

	public NCLHelperFactory(String Filename) {
		this.helpFileName = Filename;
	}

	/**
	 * Constroi o Help e coloca em um Map<String, Map<String,String>>
	 */
	public void buildHelp() {
		readHelperFileIntoString();
		parseHelperFileIntoMap();
	}

	/**
	 * 
	 * @param buffer
	 *            - Buffer representando o arquivo de help
	 * @return helpMap - O map que terá como chave o nome do elemento e como
	 *         valor outro Map que irá mapear <atributo, descrição>
	 */
	private void parseHelperFileIntoMap() {

		help = new HashMap<String, Map<String, String>>();
		Map<String, String> descriptionMap;
		StringTokenizer st = new StringTokenizer(fileBuffer, "|");
		String element, attribute, description;

		while (st.hasMoreTokens()) {
			/* pega o elemento atributo e sua descrição */
			element = st.nextToken().trim();
			System.out.println(element);
			attribute = st.nextToken().trim();
			System.out.println(attribute);
			description = st.nextToken().trim();
			/* tenta pegar o Map correspondente a esse elemento */
			descriptionMap = help.get(element);

			if (descriptionMap != null) {
				/* se existir adiciona a descrição para o novo atributo */
				descriptionMap.put(attribute, description);
			} else {
				/*
				 * se não existir cria o novo Map e adiciona a descrição
				 * desse atributo
				 */
				descriptionMap = new HashMap<String, String>();
				descriptionMap.put(attribute, description);
				help.put(element, descriptionMap);
			}
		}

	}

	/**
	 * Abre o arquivo de helper e coloca dentro de um BufferString
	 * 
	 * @return buffer - A string de buffer que conterá o arquivo de help
	 */
	private void readHelperFileIntoString() {

		try {

			File helperFile = new File(this.helpFileName);
			InputStreamReader input = new InputStreamReader(
					new FileInputStream(helperFile), "UTF-8");

			BufferedReader in = new BufferedReader(input);

			CharBuffer charBuffer = CharBuffer.allocate((int) helperFile
					.length());
			in.read(charBuffer);

			this.fileBuffer = new String(charBuffer.array());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setHelp(Map<String, Map<String, String>> help) {
		this.help = help;
	}

	public Map<String, Map<String, String>> getHelp() {
		return help;
	}

}
