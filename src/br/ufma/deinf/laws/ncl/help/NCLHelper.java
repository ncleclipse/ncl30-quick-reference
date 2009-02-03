package br.ufma.deinf.laws.ncl.help;
import java.util.Map;


public class NCLHelper {

	private static Map<String, Map<String,String> > helpMap;
	private static NCLHelperFactory helpFactory;
	private static String helpFileName;
	private static NCLHelper instance;
	
	private NCLHelper(){
	}
	
	public static NCLHelper getNCLHelper(){
		if(instance == null)
			instance = new NCLHelper();
		return instance;
	}
	
	public static void buildHelp(){
		helpFactory = new NCLHelperFactory(helpFileName);
		helpFactory.buildHelp();
		helpMap = helpFactory.getHelp();
	}
	/**
	 * Pega uma descrição de help para um atributo de um elemento especifico, retorna NULL 
	 * caso não consiga encontrar nenhuma descrição para este atributo
	 * @param element - o elemento a ser procurado
	 * @param attribute - o atributo deste elemento
	 * @return description - a descrição de help do atributo especifico
	 */
	public String getHelpDescription(String element, String attribute){
		String description = "";
		Map<String,String> attMap;
		if(helpMap == null) return description;
		attMap = helpMap.get(element);
		if (attMap != null){
			description = attMap.get(attribute);
		}
		return description;
	}
	
	/**
	 * Pega uma descrição de help para um elemento especifico, retorna NULL 
	 * caso não consiga encontrar nenhuma descrição para este elemento
	 * @param element - o elemento a ser procurado
	 * @return description - a descrição de help do elemento especifico
	 */
	public static String getHelpDescription(String element){
		String attribute = "self";
		String description = "";
		Map<String,String> attMap;
		if(helpMap == null) return description;
		attMap = helpMap.get(element);
		if (attMap != null){
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
	
	public static void main(String []args){
		NCLHelper helper = NCLHelper.getNCLHelper();
		helper.setHelpFileName("resources/help.txt");
		helper.buildHelp();
		System.out.println("ncl = " +helper.getHelpDescription("ncl"));
		System.out.println("head = " +helper.getHelpDescription("head"));
		System.out.println("body = " +helper.getHelpDescription("body"));
		System.out.println("head, id = " +helper.getHelpDescription("head", "id"));
	}
}
