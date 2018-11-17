package myAppFrame.AppFrame;

import java.util.*;

class csv
{  // comma separated values handling class
   public static String getField(String record, int n)
   {  int i = 0;
      int j = 0;
      StringBuilder fieldbuilder = new StringBuilder();

      while (i < record.length()) {
         // short circuiting is important here
         while (i < record.length() && record.charAt(i) != ',') {
            if (record.charAt(i) != '\"') {
               if (j + 1 == n) {
                  fieldbuilder.append(record.charAt(i++));
               } else {
                  i++;
               }
            } else {
               // if (j + 1 == n) fieldbuilder.append(record.charAt(i));
               i++;
               while (record.charAt(i) != '\"') {
                  if (j + 1 == n) {
                     fieldbuilder.append(record.charAt(i++));
                  } else {
                     i++;
                  }
               }
               // if (j + 1 == n) fieldbuilder.append(record.charAt(i));
               i++;
            }
         }
         if (j + 1 == n) return fieldbuilder.toString();
         i++;
         j++;
      }
      return fieldbuilder.toString();
   }

   public static void getFields(ArrayList<String> fields, String line) 
   {  int i = 0, n = line.length();
      while (i < n) 
      {  StringBuilder field = new StringBuilder();
         while (line.charAt(i) != ',') 
         {  if (line.charAt(i) != '\"')
               field.append(line.charAt(i++));
            else 
            {  // we're escaped right?
               ++i;    // step through the quote
               while (line.charAt(i) != '\"')
                  field.append(line.charAt(i++));
               ++i;    // step through the quote
            }
            if (i >= n) break;
         }
         fields.add(field.toString());
         ++i;
      }
   }  

  public static int countFields(String record)
  {   int n, reclen = record.length(), noflds = 0;
      
      n = 0;
      while ( n < reclen )
      {  // skip through double quote escaped characters
         if (record.charAt(n) == '"')
         {  ++n;
            while (record.charAt(n++) != '"')
            {  ;
            }
         }
         if (record.charAt(n) == ',')
            ++noflds;
         ++n;
      }
      return ++noflds;
  }
}
