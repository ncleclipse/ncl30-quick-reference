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

import java.util.Map;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class NCLHelper {

	private static Map<String, Map<String, String>> helpMap;
	private static NCLHelperFactory helpFactory;
	private static String helpFileName;
	private static NCLHelper instance;

	private NCLHelper() {
	}

	public static NCLHelper getNCLHelper() {
		if (instance == null)
			instance = new NCLHelper();
		return instance;
	}

	public static void buildHelp() {
		helpFactory = new NCLHelperFactory(helpFileName);
		helpFactory.buildHelp();
		helpMap = helpFactory.getHelp();
	}

	/**
	 * Pega uma descrição de help para um atributo de um elemento especifico,
	 * retorna NULL caso não consiga encontrar nenhuma descrição para este
	 * atributo
	 * 
	 * @param element
	 *            - o elemento a ser procurado
	 * @param attribute
	 *            - o atributo deste elemento
	 * @return description - a descrição de help do atributo especifico
	 */
	public String getHelpDescription(String element, String attribute) {
		String description = "";
		Map<String, String> attMap;
		if (helpMap == null)
			return description;
		attMap = helpMap.get(element);
		if (attMap != null) {
			description = attMap.get(attribute);
		}
		return description;
	}

	/**
	 * Pega uma descrição de help para um elemento especifico, retorna NULL
	 * caso não consiga encontrar nenhuma descrição para este elemento
	 * 
	 * @param element
	 *            - o elemento a ser procurado
	 * @return description - a descrição de help do elemento especifico
	 */
	public static String getHelpDescription(String element) {
		String attribute = "self";
		String description = "";
		Map<String, String> attMap;
		if (helpMap == null)
			return description;
		attMap = helpMap.get(element);
		if (attMap != null) {
			description = attMap.get(attribute);
		}
		return description;
	}

	public void setHelpFileName(String helpFileName) {
		this.helpFileName = helpFileName;
	}

	public String getHelpFileName() {
		return helpFileName;
	}

	public static void main(String[] args) {
		NCLHelper helper = NCLHelper.getNCLHelper();
		helper.setHelpFileName("resources/help.txt");
		helper.buildHelp();
		System.out.println("ncl = " + helper.getHelpDescription("ncl"));
		System.out.println("head = " + helper.getHelpDescription("head"));
		System.out.println("body = " + helper.getHelpDescription("body"));
		System.out.println("head, id = "
				+ helper.getHelpDescription("head", "id"));
	}
}
