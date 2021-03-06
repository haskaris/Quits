package Global;

import java.io.*;

//À revoir
public class Properties {
    //MACROS
    final static String path = "default.cfg";


    public static void load()throws IOException{
        InputStream is = Configuration.charge(path);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader in_stream = new BufferedReader(isr);
        String S; 
        while ((S = in_stream.readLine()) != null) {
            if(!S.startsWith("#")){
                String[] prop = S.split("=");
                if(!Configuration.write(prop[0], prop[1]))
                    Tools.Print("Erreur dans le fichier de proprietes : champ inconnu " + prop[0]);
            }
        }
      in_stream.close();
    }

    public static void store()throws IOException{
        BufferedWriter out_stream = new BufferedWriter(new FileWriter(path));
        out_stream.write("# Choix du niveau des warnings\n");
        out_stream.write("LogLevel="+ Configuration.read("LogLevel").toString() +"\n");
        out_stream.write("# Choix du plein ecran\n");
        out_stream.write("Maximized="+ Configuration.read("Maximized").toString() +"\n");
        out_stream.write("# Animations active\n");
        out_stream.write("Animations="+ Configuration.read("Animations").toString() +"\n");
        out_stream.write("# Taille du terrain\n");
        out_stream.write("Taille="+ Configuration.read("Taille").toString() +"\n");
        out_stream.write("# Nombre de joueurs (2 ou 4)\n");
        out_stream.write("Joueurs="+ Configuration.read("Joueurs").toString() +"\n");

        out_stream.close();
    }

}
