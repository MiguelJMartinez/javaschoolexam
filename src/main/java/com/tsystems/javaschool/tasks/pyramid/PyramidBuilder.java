package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.Collections;

public class PyramidBuilder {

    public int obtenerFilas(List<Integer> inputNumbers){
        if(inputNumbers.contains(null)) 
            throw new CannotBuildPyramidException("Un elemento es nulo");
        
        int tamanio = inputNumbers.size();
        int filas = 0;

        for(int i = 1; tamanio > 0; i++){
            tamanio -= i;
            filas = i;
        }

        if(tamanio < 0)
            throw new CannotBuildPyramidException("No es posible formar la pirámide");

        return filas;
    }
    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException{

        int filas = obtenerFilas(inputNumbers);
        int columnas= filas + (filas - 1);
        //System.out.println("Columnas " + columnas);
        int centro = (columnas - 1)/2;
        int inferior;  //límite inferior
        int superior;  //límite superior
        //System.out.println("Centro " + inferior);
        Collections.sort(inputNumbers);  //ordeno la lista de números de entrada
        int matriz[][] = new int[filas][columnas];  //se inicializa la matriz de ceros
        int contador=0;
        for(int i=0;i<filas;i++){
            inferior=centro-i;
            superior=centro+i;
            for(int j=0;j<columnas;j++){
               //System.out.println("i vale " + i);
               //System.out.println("j vale " + j);
               //System.out.println("k vale " + k);
               while(inferior<=superior){
                    matriz[i][inferior]=inputNumbers.get(contador);
                    inferior+=2;
                    contador++;
               }
            }
        }
        return matriz;
    }
}
