using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class DrugInfo
    {
        public string DrugCode { get; set; }
        public DateTime StartTakingDrugDate { get; set; }
        public string DrugDosageCode { get; set; }
        public string DrugSpecifications { get; set; }
        public string DrugDays { get; set; }
    }
}
