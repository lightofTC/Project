using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class TreatmentInfo
    {
        public string Card_ID { get; set; }
        public string DiseaseCode { get; set; }
        public DateTime TreatmentDate { get; set; }
        public string TreatmentTypeCode { get; set; }
        public DateTime BeginTreatmentDate { get; set; }
        public DateTime AEFIOccurrenceDate { get; set; }
        public DateTime AEFIDiagnoseDate { get; set; }
        public string AEFIDiagnoseCode { get; set; }
        public string AEFIDrugCode { get; set; }
        public string TreatmentShcemeCode { get; set; }
        public DateTime StopTreatmentDate { get; set; }
        public string StopTreatmentReasonCode { get; set; }
        public string TreatmentResultCode { get; set; }
        public DateTime DischargeDate { get; set; }
        public DateTime AdmissionDate { get; set; }
        public string Registrationno { get; set; }
        public string PatmentTypeCode { get; set; }
        public string RegistrationTypeCode { get; set; }
        /// <summary>
        /// 未接受治疗原因代码  接口文档：NontreamentReasonCode
        /// </summary>
        public string NoTreatmentReasonCode { get; set; }
       
        public List<DrugInfo> DrugInfoList { get; set; }
        public string DiseaseHistoryCode { get; set; }
        public string AntiRetroviralDrugHisCode { get; set; }
        public DateTime PreventiveDrugCodeduration { get; set; }
        public string RegistantTypeCode { get; set; }
        public string RegistrationOrg { get; set; }
        public string SupervisionOrg { get; set; }
        public DateTime ReceiveICUStartDate { get; set; }
        public DateTime receiveICUendDate { get; set; }
        public string AdmissionDiagnosisCode { get; set; }
        public string DiagnosisCode { get; set; }
        public string TreatmentOrgCode { get; set; }
        public string TransinOrgCode { get; set; }
        public DateTime TransinDate { get; set; }
        public string NonTransinReasonCode { get; set; }
        public string BSTransmissionCode { get; set; }
        public string Height { get; set; }
        public string Weight { get; set; }
    }

}
