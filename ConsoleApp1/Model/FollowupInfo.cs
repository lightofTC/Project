using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class FollowUpInfo
    {
        public string Card_ID { get; set; }
        public string DiseaseCode { get; set; }
        public DateTime FollowUpdate { get; set; }
        public string DEFORMITYCode { get; set; }
        public DateTime AEFIOccurrenceDate { get; set; }
        public DateTime AEFIDiagnoseDate { get; set; }
        public string AEFIDiagnoseCode { get; set; }
        public string AEFIDrugCode { get; set; }
        public string DrugCode { get; set; }
        public string DrugdoseCode { get; set; }
        public string DrugdosageCode { get; set; }
        public string Drugfrequency { get; set; }
        public string FollowUpModeCode { get; set; }
        public DateTime DotDate { get; set; }
        public string NonDotReasonCode { get; set; }
        public string DotOrgCode { get; set; }
        public string DotModeCode { get; set; }
        public string PlannedMedDays { get; set; }
        public string ActualMedDays { get; set; }
        public string FollowUpStatusCode { get; set; }
        public string MotherIDCard { get; set; }
        public string SpouseStatusCode { get; set; }
        public string SpouseHIVCode { get; set; }
        public DateTime SpouseHIVDate { get; set; }
        public string SpouseIDCard { get; set; }
        public string LastCondomuseCode { get; set; }
        public string FollowUpContentCode { get; set; }
        public string WHOcTNMCode { get; set; }
        public string SymptomsCode { get; set; }
        public string Lasr7dDrugLeakages { get; set; }
        public string GestationalAge { get; set; }
        public string SMZ { get; set; }
        public string ClinicalMeasuresCode { get; set; }
        public string DrugChangeReasonCode { get; set; }
        public string DrugChangeSideEffectCode { get; set; }
        public string DrugStopReasonCode { get; set; }
        public string DrugStopSideEffectCode { get; set; }
        public DateTime NextFollowUpdate { get; set; }
        public string DrugGivenQuantity { get; set; }
        public string FollowUpPersonnel { get; set; }
        public string RegistrationNumber { get; set; }
        public DateTime TransOutDate { get; set; }
        public string TransinOrgCode { get; set; }
        public string TreatmentResultCode { get; set; }
    }
}
