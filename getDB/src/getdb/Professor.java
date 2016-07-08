package getdb;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	/**Classe que representa um
	 * Professor
	 */
	private static List<String[]> professors = new ArrayList<String[]>();
	//Checa a existencia e retorna ou o original ou o pré existente
	//recebe da forma String[0] = nome, [1] = indice
	public static String[] addProfessor(String[] proposto){
		String[] temp;
		if(!professors.isEmpty()){
			for(int i=0;i<professors.size();i++){
				temp = professors.get(i);
				if(temp[0].matches(proposto[0])){
					proposto = temp;
					return proposto;
				}
					
			}
		}
    	GetDB.Prof ++;
    	proposto[1] = Integer.toString(GetDB.Prof);
		professors.add(proposto);
		return proposto;
	}
	public static boolean exists(String[] proposto){
		String[] temp;
		for(int i=0;i<professors.size();i++){
			temp = professors.get(i);
			if(temp[0].matches(proposto[0])){
				return true;
			}
		}
		return false;
	}
}
