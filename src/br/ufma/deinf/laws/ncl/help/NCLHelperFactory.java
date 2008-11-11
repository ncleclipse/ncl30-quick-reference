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

public class NCLHelperFactory {

	private String helpFileName;
	private String fileBuffer;
	private Map<String, Map<String,String>> help;

	public NCLHelperFactory(String Filename) {
		this.helpFileName = Filename;
	}
	
	/**
	 * Constroi o Help e coloca em um Map<String, Map<String,String>>
	 */
	public void buildHelp(){
		readHelperFileIntoString();
		parseHelperFileIntoMap();
	}
	
	/**
	 * 
	 * @param buffer - Buffer representando o arquivo de help
	 * @return helpMap - O map que terá como chave o nome do elemento e como valor outro Map que irá mapear <atributo, descrição>
	 */
	private void parseHelperFileIntoMap(){
		
		help = new HashMap<String, Map<String,String>>();
		Map<String, String> descriptionMap;
		StringTokenizer st = new StringTokenizer(fileBuffer,"|");
		String element,attribute,description;
		
		while(st.hasMoreTokens()) {
			/* pega o elemento atributo e sua descrição */
			element = st.nextToken().trim();			
			System.out.println(element);
			attribute = st.nextToken().trim();
			System.out.println(attribute);
			description = st.nextToken().trim();
			/* tenta pegar o Map correspondente a esse elemento */
			descriptionMap = help.get(element);
			
			if ( descriptionMap != null ){ 
				/* se existir adiciona a descrição para o novo atributo */
				descriptionMap.put(attribute, description);
			} else {
				/* se não existir cria o novo Map e adiciona a descrição desse atributo */
				descriptionMap = new HashMap<String,String>();
				descriptionMap.put(attribute, description);
				help.put(element, descriptionMap);
			}
		}
		
	}

	/**
	 * Abre o arquivo de helper e coloca dentro de um BufferString
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

	public void setHelp(Map<String, Map<String,String>> help) {
		this.help = help;
	}

	public Map<String, Map<String,String>> getHelp() {
		return help;
	}

}
