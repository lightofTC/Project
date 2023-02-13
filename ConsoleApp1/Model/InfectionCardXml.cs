using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace ConsoleApp1.Model
{
    internal class InfectionCardXml
    {
        XmlDocument InfectionCardXmlDoc { get; set; }
        public InfectionCardXml() { 

            XmlDocument xmlDocument= new XmlDocument();
            XmlDeclaration xmlDeclaration=xmlDocument.CreateXmlDeclaration(
                "1.0","utf-8",null);
            xmlDocument.AppendChild(xmlDeclaration);

            XmlDocument root_Request = xmlDocument.CreateElement("REQUEST");
            xmlDocument.AppendChild(root_Request);



        }
        /// <summary>
        /// 从文档中读取节点名到List中
        /// </summary>
        /// <param name="path"></param>
        /// <returns></returns>
        public List<String> ReadNodesNameFromFile(string path)
        {
            List<String> nameList = new List<String>();
            FileStream fileStream = new FileStream(
                path,FileMode.Open, FileAccess.Read);
            StreamReader streamReader= new StreamReader(fileStream);
            string nodeName;
            while ((nodeName = streamReader.ReadLine()) != null)
            {
                nameList.Add(nodeName);
            }
            fileStream.Close();
            streamReader.Close();
            return nameList;
        }

        public XmlDocument AddRootNodes(string path)
        {
            XmlDocument xmlDocument = new XmlDocument();
            List<String> nameList =ReadNodesNameFromFile(path);

            XmlElement root = xmlDocument.CreateElement("REQUEST");
            xmlDocument.AppendChild(root);

            XmlElement tempXmlElem;
            foreach(String nodeName in nameList)
            {
                tempXmlElem= xmlDocument.CreateElement(nodeName);
                root.AppendChild(tempXmlElem);
            }
            return xmlDocument;
        }

        public XmlDocument AddNodes(string path)
        {
            List<String> nameList=ReadNodesNameFromFile(path);
            XmlDocument xmlDocument = new XmlDocument();  
            
            

            return xmlDocument;
        }


    }
}
