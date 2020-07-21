using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class SaveData
    {
        public void save_data(String deaths,String infected)
        {
            var path = @"./tmp.txt";
            DateTime now = DateTime.Now;
            string text = deaths+","+ infected+","+ now.ToString()+"\n";
            File.AppendAllText(path, text);

      
        }
   
    }
}
