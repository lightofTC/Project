using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using ConsoleApp1.Model;

namespace InfectionCard
{
    internal class XMLDoc
    {
        string xmlFile { get; set; }
        public XMLDoc( ) {
            XmlDocument xmlDocument=new XmlDocument();

            XmlDeclaration xmlDeclaration = xmlDocument.CreateXmlDeclaration("1.0", "utf-8", "null");
            xmlDocument.AppendChild(xmlDeclaration);


            XmlElement root = xmlDocument.CreateElement("REQUEST");
            xmlDocument.AppendChild(root);

            XmlElement demession = xmlDocument.CreateElement("PERSONINFO");
            root.AppendChild(demession);
            demession = xmlDocument.CreateElement("DISEASEINFO");
            root.AppendChild(demession);
            

        }
       
    }
}
