using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.Model
{
    internal class InfectionCardInfo
    {
        protected PersonInfo? PersonInfo { get; set; }

        DiseaseInfo DiseaseInfo { get; set; }

        List< PathogenInfo> PathogenInfo { get; set; } 

        List<TreatmentInfo> TreatmentInfoList { get; set;}

        List<FollowUpInfo> FollowUpInfoList { get; set;}


    }
}
