import java.util.Random;
import java.util.stream.DoubleStream;

import javax.swing.JOptionPane;

public class Balanpy {
    static int MAX=4;
    int tempSup=38;     
    int tempSup2=35;
    int[] almacenaArray()
    {
        int []arreglo=new int[MAX];
        String cadena;
         
        for(int i=0;i<MAX;i++)
        {   
            do{
            cadena=JOptionPane.showInputDialog("Ingrese Temperatura "+ (i+1)+" : ");
            arreglo[i]=Integer.parseInt(cadena);
                if((arreglo[i]<5)||(arreglo[i]>100))
                {
                    JOptionPane.showMessageDialog(null,"La temperatura debe ser de 5 y 100.");
                }
            }while((arreglo[i]<5)||(arreglo[i]>100));
        }
        return arreglo;
    }
   //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    int temperaturaAlta(int array[])
    {
         
        int contador=0;
        for(int i=0;i<array.length;i++)
        {
            if(array[i]>tempSup)
                contador=contador+1;
            
        }
        
          JOptionPane.showMessageDialog(null, "Las Temperaturas alarmantes mayores a "+tempSup+" son: "+contador);
                        return contador;
    }
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    int temperaturaBaja(int array[])
    {
         
        int contador2=0;
        for(int i=0;i<array.length;i++)
        {
            
            if(array[i]<=tempSup2)
                contador2=contador2+1;
        }
        
          
           JOptionPane.showMessageDialog(null, "Las Temperaturas alarmantes inferiores a "+tempSup2+" son: "+contador2);
                        return contador2;
    }
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    int temperaturaMedia(int array[])
    {
        /*int MAX=4;*/
        double media=0;
        double suma=0;
        double promedioTemp=0;
        for(int i=0;i<array.length;i++)
        {
           suma += array[i];
            media++;
        }
        promedioTemp= suma/media;
        JOptionPane.showMessageDialog(null, "El promedio de la temperaturas diarias es: "+promedioTemp);
    return 0;
    }
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    void temperaturaMaxima(int array[])
    {
        int tmp=0;
        String cad="";
        int array2[] = new int[MAX];
         
        for(int i=0;i<array.length;i++)
        {
            array2[i]=array[i];
        }
     
        int k=0,x=0,c=0;
        for(int i=1;i<array2.length;i++)
        {
            if(array2[i-1]<array2[i])
            {   x=i;c++;    }
            //else
            //  return 0;
             
            for(int j=0;j<array2.length-i;j++)
            {
 
                 
                if(array2[j]>array2[j+1])
                {
                    k=array2[j+1]; array2[j+1]=array2[j]; array2[j]=k;
                     
                }
                 
            }
        }
        JOptionPane.showMessageDialog(null,"La temperatura maxima fue: "+array2[MAX-1]);
         
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    //------------------------------------------------------------------------------------------------
    void conjuntoTemperaturas(){

        double valor_min = 32.0;
        double valor_max = 41.0;

        Random random = new Random(); 
        DoubleStream stream = random.doubles(100, valor_min, valor_max);
        //System.out.println("Lectura del conjunto de temperaturas");
        JOptionPane.showMessageDialog(null,"Lectura del conjunto de temperaturas" + stream);
        stream.forEach(System.out::println);
        
    }

    //-----------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
         
        int []array=new int[MAX];
        int temperaturaSuperiorA=0;
        int temperaturaInferiorA=0;
        double temperaturaMediaA=0.0;
        int opcion=0;
        int opcion2=0;
        String cadena;
        int sup=38,retro=0;
        int inf=35,retro2=0;
        Balanpy temperatura = new Balanpy();
        do{
           array=temperatura.almacenaArray();
           do
           {    retro=1;
            cadena=JOptionPane.showInputDialog("Seleccione una opción: \n 1) Temperatura Máxima del Dia. \n 2) Temperaturas Alarmantes < 36ºC. \n 3) Temperaturas Alarmantes > 38ºC. \n 4) Promedio de temperaturas. \n 5) Lectura temperatuars. \n 6) Reiniciar. \n 7) Salir.");
            opcion=Integer.parseInt(cadena);
                 
            switch(opcion)
            {
                case 1:temperatura.temperaturaMaxima(array);
                break;
                
                case 2:temperaturaSuperiorA=temperatura.temperaturaBaja(array);
                break;
                
                case 3:temperaturaInferiorA=temperatura.temperaturaAlta(array);
                break;
                
                case 4:temperaturaMediaA=temperatura.temperaturaMedia(array);
                break;
                
                case 5:temperatura.conjuntoTemperaturas();
                break;    
                
                case 6:retro=0;
                break;
                
                case 7:System.exit(0);
                break;
            }
           }while(retro==1);
        }while(retro==0);
                             
    }
}	



