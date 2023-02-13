using ConsoleApp1.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Xml;

namespace InfectionCard
{
    internal class InfectionCardUpload
    {
        public static void Main(string[] args)
        {
            InfectionCardInfo pInfo= new InfectionCardInfo();

            string nodeName1 = "PERSONINFO";
            string path_PersonInfo = GetFilePath("PersonInfo");
            string nodeName2 = "DISEASEINFO";
            string path_DiseaseInfo = GetFilePath("DiseaseInfo");
            string nodeName3 = "PATHOGENINFOLIST";
            string path_PathogenInfo = GetFilePath("PathogenInfo");

            //创建有根节点的xml文件;
            XmlDocument xmlDocument = new XmlDocument();
            XmlDeclaration xmlDeclaration = xmlDocument.CreateXmlDeclaration("1.0", "utf-8", null);
            xmlDocument.AppendChild(xmlDeclaration);
            XmlElement root = xmlDocument.CreateElement("REQUEST");
            xmlDocument.AppendChild(root);

            xmlDocument = CreateElements(GetFilePath("XmlRootNode"));

            XmlDocument xmlDocument2 = CreateElements(path_PersonInfo);
            xmlDocument=MergeXmlFile(xmlDocument, xmlDocument2,nodeName1);

            XmlDocument xmlDocument3 = CreateElements(path_DiseaseInfo);
            xmlDocument=MergeXmlFile(xmlDocument,xmlDocument3,nodeName2);

            XmlDocument xmlDocument4=CreateElements(path_PathogenInfo);
            xmlDocument=MergeXmlFile(xmlDocument,xmlDocument4,nodeName3);

            string xmlFile = ConvertXmlToString(xmlDocument);
            xmlDocument.Save("testXml.xml");
            Console.WriteLine(xmlFile);
        }
        /// <summary>
        /// 根据txt的节点名称文档创建xml结构的xmlDocument
        /// </summary>
        /// <param name="filePath">节点名称文档路径</param>
        /// <return>XmlDocument</return></returns>
        public static XmlDocument CreateElements(string filePath,InfectionCardInfo pInfo)
        {
            //打开节点文件
            List<String> demessionNameList = new List<String>();
            FileStream fileStream = new FileStream(filePath, FileMode.Open, FileAccess.Read);
            StreamReader streamReader = new StreamReader(fileStream);
            string xmlDemessionName;
            while ((xmlDemessionName = streamReader.ReadLine()) != null)
            {
                demessionNameList.Add(xmlDemessionName);
            }
            fileStream.Close();
            //d

            //创建xml节点
            Regex regex = new Regex("\\/([\\w]+)\\.");
            XmlDocument xmlDocument = new XmlDocument();

            string name = regex.Match(filePath).Groups[1].Value.ToString();
            try
            {
                XmlElement tempNode = xmlDocument.CreateElement(name.ToUpper());
                xmlDocument.AppendChild(tempNode);
                XmlElement xmlField;
                foreach (String xmlFieldName in demessionNameList)
                {
                    xmlField = xmlDocument.CreateElement(xmlFieldName);
                    
                    //xmlField.InnerText = xmlFieldName;
                    tempNode.AppendChild(xmlField);
                }

            }
            catch (System.ArgumentException) { Console.WriteLine("Error!"); }
            return xmlDocument;
        }
        /// <summary>
        /// 将xml文档转为string字符串
        /// </summary>
        /// <param name="xmlDocument"></param>
        /// <returns></returns>
        public static string ConvertXmlToString(XmlDocument xmlDocument)
        {
            MemoryStream stream = new MemoryStream();
            XmlTextWriter xmlTextWriter = new XmlTextWriter(stream, null);
            xmlTextWriter.Formatting = Formatting.Indented;
            xmlDocument.Save(xmlTextWriter);
            StreamReader streamReader = new StreamReader(stream, System.Text.Encoding.UTF8);
            stream.Position = 0;
            string xmlString = streamReader.ReadToEnd();
            streamReader.Close();
            stream.Close();
            return xmlString;
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="xml1"></param>
        /// <param name="xml2"></param>
        /// <param name="nodeName"></param>
        /// <returns></returns>
        public static XmlDocument MergeXmlFile(XmlDocument xml1,XmlDocument xml2,string nodeName)
        {
            XmlDocument xmlDocument = xml1;
            XmlDocument xmlDocument2 = xml2;
            foreach(XmlNode node in xmlDocument2.DocumentElement) {
                xmlDocument.DocumentElement.SelectSingleNode("//"+nodeName).AppendChild(xmlDocument.ImportNode(node, true));
            }
            return xmlDocument;
        }

        public static string GetFilePath(string fileName)
        {
            return "../../../Resources/"+fileName+ ".txt";
        }
    }
}
