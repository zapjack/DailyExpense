/*File HTMLWriter.java
Copyright 2003 R.G.Baldwin

This class provides a utility method named
writeHTMLFile() that receives a DOM Document
object as a parameter and writes an output XML
file that matches the information contained in
the Document object.

The output file is created by recursively
traversing the Document object, identifying each
of the nodes in that object, and converting each
node to text in an XML format.

No effort is made to insert spaces and line
breaks to make the output cosmetically pleasing.
Also, nothing is done to eliminate cosmetic
whitespace that may exist in the Document object.

The name of the XML file is established as a
parameter to the constructor for the class.

A cosmetically pleasing view of the output file
can be obtained by opening the output file in
IE 5.0 or later.

Briefly tested using JDK 1.4.2 and WinXP.  Note
however that this class has not been thoroughly
tested. If you use it for a critical application,
test it thoroughly before using it.
************************************************/

package ques;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.*;

import org.w3c.dom.*;

public class HTMLWriter {
  // public static String DEFAULT_LOCAL_DIR = "c:\\aj\\assess\\java\\";

  private String imageDir = "?";

  private static String quesType = "?";
  private boolean insidePRE = false;
  private String pageTitle = "Questions";
  
  private PrintWriter out;

  //-------------------------------------------//

  public HTMLWriter(String xmlFile, String imageDir, String pageTitle) {
    this.imageDir = imageDir;
    this.pageTitle = pageTitle;
    
    try {
      out = new PrintWriter(
                  new FileOutputStream(xmlFile));
    }catch (Exception e) {
      e.printStackTrace(System.err);
    }//end catch
  }//end constructor
  //-------------------------------------------//

  //This method converts an incoming Document
  // object to an output XML file
  public void writeHTMLFile(Document document){
    try {
      //Write the contents of the Document object
      // into an ontput file in XML file format.
      writeNode(document);
    }catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  //This method is used recursively to convert
  // node data to XML format and to write the XML
  // format data to the output file.

  public void writeNode(Node node) 
  {
    if (node == null) {
      System.err.println("Nothing to do, node is null");
      return;
    }

    int type = node.getNodeType();

    switch (type) {
      case Node.DOCUMENT_NODE: {
        out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");

        writeNode(
          ((Document)node).getDocumentElement());
        out.flush();
        break;
      }

      // Write an element with attributes
      case Node.ELEMENT_NODE: {
		  if (node.getNodeName().equals("questions"))
		    out.print("<html><head><title>" + pageTitle + 
                  "</title></head><body>" +
                  "<h2>" + pageTitle + "</h2><ol>");
        
		  else if (node.getNodeName().equals("question"))
		  {
		    out.print("<li>");

          ArrayList<String> attrNames = getAttrArrayList(
                             node.getAttributes());
			 int i = attrNames.indexOf("type");
		    if (i >= 0)
			 {
            Attr attrs[] = getAttrArray(
              node.getAttributes());
			   Attr attrCur = attrs[i];

            quesType = attrCur.getNodeValue();
			 }
			 else
			   quesType = "?";
		  }
		  else if (node.getNodeName().equals("title"))
		    out.print("<b>");
		  else if (node.getNodeName().equals("desc"))
		    ; // out.print("<i>");
		  else if (node.getNodeName().equals("code"))
        {
		    out.print("<div style=\"background-color:yellow;\"><pre><font face=\"Courier New\">");
          insidePRE = true;
        }
		  else if (node.getNodeName().equals("answers"))
		    out.print("Choose your answer from the following list:<ul>");
		  else if (node.getNodeName().equals("ans"))
		    out.print("<li>");
		  // answer with all "pre" formatting
		  else if (node.getNodeName().equals("ansp"))
		    out.print("<li><pre>");
		  else if (node.getNodeName().equals("pre"))
        {
		    out.print("<pre>");
          insidePRE = true;
        }
        else if (node.getNodeName().equals("solution"))
		    ; 
		  else if (node.getNodeName().equals("image"))
		  {
          ArrayList<String> attrList= getAttrArrayList(
                             node.getAttributes());
		    
			 int i = attrList.indexOf("src");
			 System.err.println("i: " + i);
			 if (i >= 0)
			 {
            Attr attrs[] = getAttrArray(
              node.getAttributes());
			   Attr attrCur = attrs[i];

            String name = attrCur.getNodeValue().trim();
				out.print("<img src=\"" + imageDir + name +
				  "\" />");
			 }
		  }
		  else
		  {
		    // pass along the tag "as is"
          out.print('<');//begin the start tag
          out.print(node.getNodeName());
  
          //Get and write the attributes belonging
          // to the element.  First get the
          // attributes in the form of an array.
          Attr attrs[] = getAttrArray(
                             node.getAttributes());
  
          //Now process all of the attributes in
          // the array.
          for (int i = 0; i < attrs.length; i++){
            Attr attr = attrs[i];
            out.print(' ');//write a space
            out.print(attr.getNodeName());
            out.print("=\"");//write ="
            //Convert <,>,&, and quotation char to
            // entities and write the text
            // containing the entities.
            out.print(
                    strToXML(attr.getNodeValue()));
            out.print('"');//write closing quote
          }//end for loop
          out.print('>');//write end of start tag
		  }

        NodeList children = node.getChildNodes();

        if (children != null && !node.getNodeName().equals("solution")) 
		  {
          int len = children.getLength();
  
          for (int i = 0; i < len; i++)
        	   writeNode(children.item(i));
        }
        break;
      }

      case Node.ENTITY_REFERENCE_NODE:{
        out.print('&');
        out.print(node.getNodeName());
        out.print(';');
        break;
      }

      //Handle text
      case Node.CDATA_SECTION_NODE:
      case Node.TEXT_NODE: {
        //Eliminate <,>,& and quotation marks and
        // write to output file.
        if (insidePRE)
           out.print(ExpandTabs.expand(node.getNodeValue(), Main.TABSTOP, false));
        else
           out.print(strToXML(node.getNodeValue()));
        break;
      }

      case Node.PROCESSING_INSTRUCTION_NODE:{
        out.print("<?");
        out.print(node.getNodeName());
        String data = node.getNodeValue();
        if (data != null && data.length() > 0){
          out.print(' ');//write space
          out.print(data);
        }
        out.print("?>");
        break;
      }
    }

    //Now write the end tag for element nodes
    if (type == Node.ELEMENT_NODE) 
	 {
	 	if (node.getNodeName().equals("questions"))
		  out.print("</ol></body></html>");
	 	else if (node.getNodeName().equals("question"))
		  out.print("</li>");
		else if (node.getNodeName().equals("title"))
		  out.print("</b><br />");
		else if (node.getNodeName().equals("desc"))
		  ; // out.print("</i>");
		else if (node.getNodeName().equals("code"))
      {
		  out.print("</font></pre></div>");
        insidePRE = false;
      }
		else if (node.getNodeName().equals("answers"))
		  out.print("</ul><br />");
		else if (node.getNodeName().equals("ans"))
		  out.print("</li>");
		else if (node.getNodeName().equals("ansp"))
		  out.print("</pre></li>");
		else if (node.getNodeName().equals("pre"))
      {
		  out.print("</pre>");
        insidePRE = false;
      }
		else if (node.getNodeName().equals("solution"))
		  ;
		else if (node.getNodeName().equals("image"))
		  ;
		else
		{
		  // end the "passed along" tag
        out.print("</");
        out.print(node.getNodeName());
        out.print('>');

		}
    }
  }


  //The following methods are utility methods

  //This method inserts entities in place
  // of <,>,&, and quotation mark

  private String strToXML(String s) {
    StringBuffer str = new StringBuffer();

    int len = (s != null) ? s.length() : 0;

    for (int i = 0; i < len; i++) {
      char ch = s.charAt(i);
      switch (ch) {
        case '<': {
          str.append("&lt;");
          break;
        }
        case '>': {
          str.append("&gt;");
          break;
        }
        case '&': {
          str.append("&amp;");
          break;
        }
        case '"': {
          str.append("&quot;");
          break;
        }
        default: {
          str.append(ch);
        }
      }
    }
    return str.toString();
  }

  //This method converts a NamedNodeMap into an
  // array of type Attr

  private Attr[] getAttrArray(
                             NamedNodeMap attrs){
    int len = (attrs != null) ?
                           attrs.getLength() : 0;
    Attr array[] = new Attr[len];
    for (int i = 0; i < len; i++) {
      array[i] = (Attr)attrs.item(i);
    }

    return array;
  }

  //This method converts a NamedNodeMap into an
  // ArrayList of Strings (of the names of the attributes)

  private ArrayList<String> getAttrArrayList(
                             NamedNodeMap attrs){
    int len = (attrs != null) ?
                           attrs.getLength() : 0;
    ArrayList<String> array = new ArrayList<String>();
    for (int i = 0; i < len; i++) {
      array.add(((Attr)attrs.item(i)).getNodeName());
    }

    return array;
  }


  private String convertName(String s)
  {
    if (s.equals("questions"))
	   return "body";


    return null;
  }
}
