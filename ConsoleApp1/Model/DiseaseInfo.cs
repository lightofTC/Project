using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class DiseaseInfo
    {

        public string Card_ID { get; set; }
        public DateTime OnsetDate { get; set; }
        public string SymptomsCode { get; set; }
        public DateTime DiagnoseTime { get; set; }
        public string DiagnoseOrgCode { get; set; }
        public string DiseaseCode { get; set; }
        public string Other_Disease { get; set; }
        public string ICD { get; set; }
        public string DiagnoseStateCode { get; set; }
        public string CaseTypeCode { get; set; }
        public DateTime DeadDate { get; set; }
        public string PatientResidenceType { get; set; }
        public string VenerealHistoryCode { get; set; }
        public DateTime PalsyDate { get; set; }
        public string PalsySymptom { get; set; }
        public DateTime TreatmentLandDate { get; set; }
        public string TreatmentLandCode { get; set; }
        public string TreatmentLandType { get; set; }
        public string TreatmentLand { get; set; }
        public string AFPFinalDiagnosisCode { get; set; }
        /// <summary>
        /// 最后诊断及分类日期   接口为DiagnosticLassDate
        /// </summary>
        public DateTime DiagnosticLastDate { get; set; }
        public string PatientRegistrationNumber { get; set; }
        public string ServerityCode { get; set; }
        public string LabOrTestResult { get; set; }
        public string LaboratoryDetectionVerdictCode { get; set; }
        public DateTime DetectionPositiveDate { get; set; }
        public string DetectionOrgCode { get; set; }
        public DateTime LabOrDiagnosisDate { get; set; }
        public string DiscoveryModeCode { get; set; }
        public string OtherDiscoveryModeCode { get; set; }
        public string BSTransmissionCode { get; set; }
        public string Other_infectionroute { get; set; }
        public string ContactTypeCode { get; set; }
        public string Other_ContactHistory { get; set; }
        public int InjectionTogetherNum { get; set; }
        public int NonmaritalsexNum { get; set; }
        public int HomosexsualSexNum { get; set; }
        public string ChlamydialTrachomatisCode { get; set; }
        public float ALT { get; set; }
        public string HBsAgDate { get; set; }
        public DateTime HepatitisbDate { get; set; }
        public string HBsAgAsymptomatic { get; set; }
        public string HBCResult { get; set; }
        public string PercutaneousResult { get; set; }
        public string Convalescence { get; set; }
        public string Close_Contacts { get; set; }
        public string MgmtStatusCode { get; set; }
        public string CurrMgmtOrgCode { get; set; }
        public string CardNotes { get; set; }
        public string ForeignTypeCode { get; set; }
        public string PlaceCode { get; set; }
        public string PlaceOther { get; set; }
        public DateTime OutHosDate { get; set; }
        public string SeriousCode { get; set; }
        public string InputDoctor { get; set; }
        public DateTime FillTime { get; set; }
        public string ElectronicSignature { get; set; }
        public string Timestamp
        {
            get; set;
        }

    }
}
