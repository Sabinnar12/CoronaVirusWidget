using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.Text = "CoronaVirus Simple Numbers";
            var webGet = new HtmlWeb();
            var document = webGet.Load("https://www.worldometers.info/coronavirus/");
            var title = document.DocumentNode.SelectSingleNode("html/head/title").InnerText;
            String[] data = title.Split(' ');
            Console.WriteLine(data[3]);
            Console.WriteLine(data[6]);
            label1.Text = "Infected";
            label2.Text = "Deaths";
            label5.Text = data[3];
            label6.Text = data[6];

            Thread thread1 = new Thread(notification);
            thread1.Start();
            var path = @"./tmp.txt";
            if (File.Exists(path))
            {
                string[] file = File.ReadAllLines(path);
                new SaveData().save_data(data[6].Replace(',', ' '), data[3].Replace(',', ' '));
            }
        }
        void notification()
        {
            var webGet = new HtmlWeb();
            var document = webGet.Load("https://www.worldometers.info/coronavirus/");
            var title = document.DocumentNode.SelectSingleNode("html/head/title").InnerText;
            var notification = new System.Windows.Forms.NotifyIcon()
            {
                Visible = true,
                Icon = System.Drawing.SystemIcons.Warning,
                BalloonTipText = title,
            };
            notification.ShowBalloonTip(5000);
            notification.Dispose();        
    }   
    }
}
