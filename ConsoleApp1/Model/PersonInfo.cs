using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class PersonInfo
    {
        string Patient_ID { get; set; }
        string Patient_Name { get; set;}
        string IDCardTypeCode { get; set; }
        string IDCard { get; set; }
        string Sex { get; set; }
        DateTime Date_Of_Birth { get; set; }
        string NationalityCode { get; set; }
        string NationCode { get; set; }
        string EducationCode { get; set; }
        string PermanentAddrCode { get; set; }
        string PermanentAddrTypeCode { get; set; }
        string PermanentAddrDetail { get; set; }
        string AreaType { get; set; }
        string CurrentAddrCode { get; set; }
        string CurrentAddrDetail { get; set; }
        string WorkUnit { get; set; }
        string Group_ID { get; set; }
        string Occupation { get; set; }
        string MaritalStatusCode { get; set; }
        string Contacts { get; set; }
        string ContactsTel { get; set; }
        string ContactsRelCode { get; set; }
        string ReportZoneCode { get; set; }
        string ReportOrgCode { get; set; }
        DateTime ReportDate { get; set; }
        string Status { get; set; }
        DateTime DeleteTime { get; set; }
        string DelType { get; set; }
        string DelMessage { get; set; }
        DateTime AuditinTime { get; set; }


    }
}
