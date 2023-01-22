package com.tsystems.javaschool.tasks.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {

    public static String evaluar(String c, List<String> h){
        String res="";
        if(c=="" || c.matches("[0-9]+")){  //si la cadena vale "" o es un número
        	//System.out.println("valor " + c);
        	res=c;  //devuelvo el string tal cual
        }else if(c.charAt(0)==(char)39){   //si tengo un string que empieza por '
    	    //System.out.println(c.substring(1));
    	    res=c.substring(1); //devuelvo el string sin '
        }
        String [] j;
        if(!c.matches("[0-9]+") && c.length()==2){  //evaluo una casilla de forma individual
            j=h.get((int)c.charAt(0)-65).split(" ");
    	    res=j[Character.getNumericValue((int)c.charAt(1)-1)];
            //System.out.println("res vale " + res);
            res=evaluar(res,h);  //evaluo de nuevo
        }else if(c.charAt(0)=='=' && c.length()==3){  //evaluo una casilla que empieza por =
            c=c.substring(1);
            j=h.get((int)c.charAt(0)-65).split(" ");
    	    res=j[Character.getNumericValue((int)c.charAt(1)-1)];
            //System.out.println("res vale " + res);
            res=evaluar(res,h);  //evaluo de nuevo
        }if(c.charAt(0)=='=' && c.length()>3){  //evaluo una operación
            int pos=0;
            String izq, der, op="";
            String [] operadores={"+","-","*","/"};
            for(String k:operadores){
                if(c.indexOf(k,2)!=(-1)){
            	    pos=c.indexOf(k,2);
                    op=k;
                    //System.out.println("operador es " + k);  //operador
                }
            }
            izq=c.substring(1,pos);  //primer número o casilla
            //System.out.println("izq es " + izq);
            der=c.substring(pos+1,c.length());   //segundo número o casilla
            //System.out.println("der es " + der);
            if(!izq.matches("[0-9]+"))  izq=evaluar(izq,h);            
            if(!der.matches("[0-9]+"))  der=evaluar(der,h);
            //operacion con ambos digitos
            if(op=="+"){
        	    res=Integer.toString(Integer.parseInt(izq)+Integer.parseInt(der));
                //System.out.println(izq + " + " + der + " = " + res);
            }else if(op=="-"){
        	    res=Integer.toString(Integer.parseInt(izq)-Integer.parseInt(der));
                //System.out.println(izq + " - " + der + " = " + res);
            }else if(op=="*"){
        	    res=Integer.toString(Integer.parseInt(izq)*Integer.parseInt(der));
                //System.out.println(izq + " * " + der + " = " + res);
            }else if(op=="/"){
            	res=Integer.toString(Integer.parseInt(izq)/Integer.parseInt(der));
                //System.out.println(izq + " / " + der + " = " + res);
            }
        }
        return res;
    }

    /**
     * Process table cells
     *
     * @param inputData unprocessed table cells
     */
    public List<String> process(List<String> inputData) {
        ArrayList<String> total = new ArrayList<String>();
        String b, f="";
        String [] l;
        if(inputData.size()==1 && inputData.get(0)==""){
            total.add("");
            return total;
        }  
        for(int i=0;i<inputData.size();i++){
    	    l=inputData.get(i).split(" ");
    	    for(int j=0;j<l.length;j++){
        	    if(j!=0){
        	        f= f+" ";
    	        }
    	        b=evaluar(l[j],inputData);
    	        //System.out.println(b);
    	        f=f+b;
    	    }
    	    //System.out.println(f);
    	    total.add(f);
    	    f=""; //reseteo f tras añadir el elemento
        }
        return total;
    }
}
