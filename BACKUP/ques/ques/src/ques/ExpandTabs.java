/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ques;

/**
 *
 * @author agjackso
 */
public class ExpandTabs {
    /** The addBR parameter adds a BR HTML tag at the end of each line */
   public static String expand(String in, int tabstop, boolean addBR) {
        return expand(in, tabstop, addBR, true);
   }
   
      public static String expand(String in, int tabstop, boolean addBR,
              boolean keepNewlineChars) {
      int pos = 0;
      StringBuffer sb = new StringBuffer("");

      for (int i = 0; i < in.length(); i++) {
         char ch = in.charAt(i);
         if (ch == '\t') {
            fillToTabstop(pos, tabstop, sb);
            pos = 0;
         }
         else {
            switch (ch) {
              case '<': {
                sb.append("&lt;");
                break;
              }
              case '>': {
                sb.append("&gt;");
                break;
              }
              case '&': {
                sb.append("&amp;");
                break;
              }
              case '"': {
                sb.append("&quot;");
                break;
              }
              default: {
                if(ch != '\n')
                    sb.append(ch);
                else
                {
                    if (keepNewlineChars)
                        sb.append(ch);
                }
              }
            }
            if (ch == '\n')
            {
               pos = 0;
               if (addBR)
                   sb.append("<br>");
            }
            else
               pos = (pos + 1) % tabstop;
         }
      }

      return sb.toString();
    }

   public static void fillToTabstop(int pos, int tabstop, StringBuffer sb)
   {
      for (int j = pos; j < tabstop; j++)
         sb.append(" ");
   }
}
