using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class PathogenInfo
    {
        public string Card_ID { get; set; }
        public string DiseaseCode { get; set; }
        public string SampleNum { get; set; }
        public DateTime SampleDate { get; set; }
        public DateTime ExaminationDate { get; set; }
        public string SampleTypeCode { get; set; }
        public string SampleOrgCode { get; set; }
        public DateTime SampleSubmissionDate { get; set; }
        public DateTime SampleAcceptDate { get; set; }      
        public List<CheckInfo> CheckInfoList { get; set; }
        public string AuditDoctor { get; set; }
        public string RegistrationNumber { get; set; }
        public string SampleStateCode { get; set; }
        public string SpecimeniceTransport { get; set; }
        public string SpecimenAmount { get; set; }
        public string InspectSpecimenState { get; set; }
        public string DetectionPersonnel { get; set; }
        public string PathogenicSequenceCode { get; set; }
        public string ExaminationOrgCode { get; set; }
        public DateTime ExaminationReportDate { get; set; }

    }
}
