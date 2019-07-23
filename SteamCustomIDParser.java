//Allen Qiu
//Automatically parses all links between 2 indicated values

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileWriter;  
import javax.swing.*;
import java.util.*;

public class SteamCustomIDParser
{
  static final String url_str = "https://steamcommunity.com/id/";
  
  public static void main(String[] args) throws IOException
  {
    try {
      UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    } catch (Exception e) {
      e.printStackTrace();
    }
    do
    {
      
      String initial = JOptionPane.showInputDialog(null, "which do you want first");
      int end = Integer.parseInt(JOptionPane.showInputDialog(null, "how many to parse?"));
      for(int i = 0; i < end; i++)
      {
        switch(i % 36) // clamp
        {
          case 1:
            initial = initial.substring(0, initial.length()-1);
            initial += "a";
            break;
          case 2:
            initial = initial.substring(0, initial.length()-1);
            initial += "b";
            break;
          case 3:
            initial = initial.substring(0, initial.length()-1);
            initial += "c";
            break;
          case 4:
            initial = initial.substring(0, initial.length()-1);
            initial += "d";
            break;
          case 5:
            initial = initial.substring(0, initial.length()-1);
            initial += "e";
            break;
          case 6:
            initial = initial.substring(0, initial.length()-1);
            initial += "f";
            break;
          case 7:
            initial = initial.substring(0, initial.length()-1);
            initial += "g";
            break;
          case 8:
            initial = initial.substring(0, initial.length()-1);
            initial += "h";
            break;
          case 9:
            initial = initial.substring(0, initial.length()-1);
            initial += "i";
            break;
          case 10:
            initial = initial.substring(0, initial.length()-1);
            initial += "j";
            break;
          case 11:
            initial = initial.substring(0, initial.length()-1);
            initial += "k";
            break;
          case 12:
            initial = initial.substring(0, initial.length()-1);
            initial += "l";
            break;
          case 13:
            initial = initial.substring(0, initial.length()-1);
            initial += "m";
            break;
          case 14:
            initial = initial.substring(0, initial.length()-1);
            initial += "n";
            break;
          case 15:
            initial = initial.substring(0, initial.length()-1);
            initial += "o";
            break;
          case 16:
            initial = initial.substring(0, initial.length()-1);
            initial += "p";
            break;
          case 17:
            initial = initial.substring(0, initial.length()-1);
            initial += "q";
            break;
          case 18:
            initial = initial.substring(0, initial.length()-1);
            initial += "r";
            break;
          case 19:
            initial = initial.substring(0, initial.length()-1);
            initial += "s";
            break;
          case 20:
            initial = initial.substring(0, initial.length()-1);
            initial += "t";
            break;
          case 21:
            initial = initial.substring(0, initial.length()-1);
            initial += "u";
            break;
          case 22:
            initial = initial.substring(0, initial.length()-1);
            initial += "v";
            break;
          case 23:
            initial = initial.substring(0, initial.length()-1);
            initial += "w";
            break;
          case 24:
            initial = initial.substring(0, initial.length()-1);
            initial += "x";
            break;
          case 25:
            initial = initial.substring(0, initial.length()-1);
            initial += "y";
            break;
          case 26:
            initial = initial.substring(0, initial.length()-1);
            initial += "z";
            break;
          case 27:
            initial = initial.substring(0, initial.length()-1);
            initial += "1";
            break;
          case 28:
            initial = initial.substring(0, initial.length()-1);
            initial += "2";
            break;
          case 29:
            initial = initial.substring(0, initial.length()-1);
            initial += "3";
            break;
          case 30:
            initial = initial.substring(0, initial.length()-1);
            initial += "4";
            break;
          case 31:
            initial = initial.substring(0, initial.length()-1);
            initial += "5";
            break;
          case 32:
            initial = initial.substring(0, initial.length()-1);
            initial += "6";
            break;
          case 33:
            initial = initial.substring(0, initial.length()-1);
            initial += "7";
            break;
          case 34:
            initial = initial.substring(0, initial.length()-1);
            initial += "8";
            break;
          case 35:
            initial = initial.substring(0, initial.length()-1);
            initial += "9";
            break;
          case 0:
            char pos = initial.charAt(initial.length()-2);
            if((int)pos == 122)
              pos = (char)48;
            if((int)pos == 57)
            {
              initial = initial + "aa";
              break;
            }
            
            pos = (char)((int)pos + 1);
            initial = initial.substring(0, initial.length() -2 );
            initial += pos;
            initial += "a";
            break;
        }
        // Making Request, add in the starting country as well.
        URL url = null;
        try
        {
          url = new URL(url_str + initial);
        }
        catch(MalformedURLException e)
        {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "did you input currect curtom URL?");
        }
        
        HttpURLConnection request = null;
        //connect to the currency exchange website and parse its content
        try
        {
          request = (HttpURLConnection) url.openConnection();
          request.connect();
        }
        catch(IOException e)
        {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "did your internet block steamcommunity.com? Or did they block u");
        }
        
        //parse everything
        BufferedReader content = null;
        ArrayList<String> conversionString = new ArrayList<String>(0);
        try
        {
          content = new BufferedReader(new InputStreamReader((InputStream)request.getContent()));
          while(true)
          {
            String temp = content.readLine();
            if(temp == "" || temp == null)
              break;
            conversionString.add(temp);
          }
        }
        catch(IOException e)
        {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "steamcommunity.com glitch");
        }
        request.disconnect();
        for(int j = 276; j < 295; j++)
        {
          //<h3>The specified profile could not be found.</h3><br><br> 
          boolean viable = false;
          if(conversionString.get(j).contains("<h3>The specified profile could not be found.</h3><br><br>")) viable = true;
          if(viable) 
          {
            FileWriter fw = new FileWriter("out.txt", true);
            fw.append("\r" + initial, 0, initial.length() + 1);
            fw.close();
            System.out.println(initial);
            break;
          }
        }
        System.out.println("This is not viable: " + initial);
      }
    }
    //repeat process by user input.
    while(JOptionPane.showInputDialog(null, "Try again? (Y/N)").toUpperCase().equals("Y"));
    
  }
}